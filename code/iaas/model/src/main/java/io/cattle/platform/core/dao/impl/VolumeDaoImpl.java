package io.cattle.platform.core.dao.impl;

import static io.cattle.platform.core.model.tables.InstanceTable.*;
import static io.cattle.platform.core.model.tables.MountTable.*;
import static io.cattle.platform.core.model.tables.StorageDriverTable.*;
import static io.cattle.platform.core.model.tables.StoragePoolTable.*;
import static io.cattle.platform.core.model.tables.VolumeStoragePoolMapTable.*;
import static io.cattle.platform.core.model.tables.VolumeTable.*;

import io.cattle.platform.core.addon.MountEntry;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.VolumeConstants;
import io.cattle.platform.core.dao.GenericResourceDao;
import io.cattle.platform.core.dao.VolumeDao;
import io.cattle.platform.core.model.StorageDriver;
import io.cattle.platform.core.model.StoragePool;
import io.cattle.platform.core.model.Volume;
import io.cattle.platform.core.model.VolumeStoragePoolMap;
import io.cattle.platform.core.model.tables.records.VolumeRecord;
import io.cattle.platform.db.jooq.dao.impl.AbstractJooqDao;
import io.cattle.platform.deferred.util.DeferredUtils;
import io.cattle.platform.object.ObjectManager;
import io.cattle.platform.object.process.ObjectProcessManager;
import io.github.ibuildthecloud.gdapi.id.IdFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.RecordHandler;
import org.jooq.Result;

public class VolumeDaoImpl extends AbstractJooqDao implements VolumeDao {
    private static final Set<String> LOCAL_POOL_KINDS = new HashSet<String>(Arrays.asList(new String[]{"docker", "sim"}));

    @Inject
    GenericResourceDao resourceDao;

    @Inject
    ObjectManager objectManager;

    @Inject
    ObjectProcessManager objectProcessManager;

    @Override
    public Volume findVolumeByExternalId(Long storagePoolId, String externalId) {
        Record record = create()
            .select(VOLUME.fields())
            .from(VOLUME)
            .join(VOLUME_STORAGE_POOL_MAP)
            .on(VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID.eq(storagePoolId))
            .where(VOLUME.EXTERNAL_ID.eq(externalId)
            .and((VOLUME.REMOVED.isNull().or(VOLUME.STATE.eq(CommonStatesConstants.REMOVING)))))
            .fetchAny();

        return record == null ? null : record.into(VolumeRecord.class);
    }

    @Override
    public void createVolumeInStoragePool(Map<String, Object> volumeData, String volumeName, StoragePool storagePool) {
        Record record = create()
                .select(VOLUME.fields())
                .from(VOLUME)
                .join(VOLUME_STORAGE_POOL_MAP)
                    .on(VOLUME_STORAGE_POOL_MAP.VOLUME_ID.eq(VOLUME.ID)
                    .and(VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID.eq(storagePool.getId())))
                .join(STORAGE_POOL)
                    .on(VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID.eq(STORAGE_POOL.ID))
                    .and(STORAGE_POOL.REMOVED.isNull())
                .where(VOLUME.NAME.eq(volumeName)
                    .and((VOLUME.REMOVED.isNull().or(VOLUME.STATE.eq(CommonStatesConstants.REMOVING)))))
                    .and(VOLUME.ACCOUNT_ID.eq(storagePool.getAccountId()))
                .fetchAny();
        if (record != null) {
            return;
        }

        Volume volume = resourceDao.createAndSchedule(Volume.class, volumeData);
        Map<String, Object> vspm = new HashMap<String, Object>();
        vspm.put("volumeId", volume.getId());
        vspm.put("storagePoolId", storagePool.getId());
        resourceDao.createAndSchedule(VolumeStoragePoolMap.class, vspm);
    }

    @Override
    public List<? extends Volume> findSharedOrUnmappedVolumes(long accountId, String volumeName) {
        List<VolumeRecord> volumes = create()
            .selectDistinct(VOLUME.fields())
            .from(VOLUME)
            .leftOuterJoin(VOLUME_STORAGE_POOL_MAP)
                .on(VOLUME_STORAGE_POOL_MAP.VOLUME_ID.eq(VOLUME.ID))
            .leftOuterJoin(STORAGE_POOL)
                .on(VOLUME_STORAGE_POOL_MAP.STORAGE_POOL_ID.eq(STORAGE_POOL.ID))
            .where(VOLUME.NAME.eq(volumeName)
                .and((VOLUME.REMOVED.isNull())))
                .and(VOLUME.ACCOUNT_ID.eq(accountId))
                .and(STORAGE_POOL.KIND.notIn(LOCAL_POOL_KINDS).or(STORAGE_POOL.KIND.isNull()))
                .and(STORAGE_POOL.REMOVED.isNull())
            .fetchInto(VolumeRecord.class);

        return volumes;
    }

    @Override
    public List<? extends Volume> identifyUnmappedVolumes(long accountId, Set<Long> volumeIds) {
        List<VolumeRecord> volumes = create()
            .selectDistinct(VOLUME.fields())
            .from(VOLUME)
            .leftOuterJoin(VOLUME_STORAGE_POOL_MAP)
                .on(VOLUME_STORAGE_POOL_MAP.VOLUME_ID.eq(VOLUME.ID))
            .where(VOLUME.ID.in(volumeIds)
                .and((VOLUME.REMOVED.isNull())))
                .and(VOLUME.ACCOUNT_ID.eq(accountId))
                .and(VOLUME_STORAGE_POOL_MAP.ID.isNull())
            .fetchInto(VolumeRecord.class);
        return volumes;
    }

    public static final List<String> INELLIGIBLE_STATES = Arrays.asList(CommonStatesConstants.ACTIVE, CommonStatesConstants.ACTIVATING,
            CommonStatesConstants.REQUESTED);

    @Override
    public Set<? extends Volume> findNonremovedVolumesWithNoOtherMounts(long instanceId) {
        List<Long> instanceVolumeIds = create()
                .select(MOUNT.VOLUME_ID)
                .from(MOUNT)
                .where(MOUNT.INSTANCE_ID.eq(instanceId))
                .fetchInto(Long.class);

        List<Long> inelligibleVolumeIds = create()
                .select(MOUNT.VOLUME_ID)
                .from(MOUNT)
                .where(MOUNT.VOLUME_ID.in(instanceVolumeIds)
                .and(MOUNT.INSTANCE_ID.ne(instanceId))
                .and(MOUNT.STATE.in(INELLIGIBLE_STATES)))
                .fetchInto(Long.class);

        Set<Long> volumeIds = new HashSet<Long>(instanceVolumeIds);
        volumeIds.removeAll(inelligibleVolumeIds);

        List<VolumeRecord> vols = create()
                .select(VOLUME.fields())
                .from(VOLUME)
                .where(VOLUME.ID.in(volumeIds))
                .and(VOLUME.REMOVED.isNull())
                .fetchInto(VolumeRecord.class);

        Set<? extends Volume> volumes = new HashSet<Volume>(vols);
        return volumes;
    }

    private static final Set<String> INSTANCE_STATES = new HashSet<String>(Arrays.asList(new String[] { InstanceConstants.STATE_STOPPED,
            InstanceConstants.STATE_CREATED }));

    @Override
    public boolean isVolumeInUseByRunningInstance(long volumeId) {
        Result<Record1<Integer>> result = create()
                .selectCount()
                .from(INSTANCE)
                .join(MOUNT)
                .on(MOUNT.INSTANCE_ID.eq(INSTANCE.ID))
                .where(INSTANCE.STATE.notIn(INSTANCE_STATES))
                .and(INSTANCE.REMOVED.isNull())
                .and(MOUNT.REMOVED.isNull())
                .and(MOUNT.VOLUME_ID.eq(volumeId))
                .fetch();

        Integer inUseCount = (Integer)result.getValue(0, 0);

        return inUseCount > 0;
    }

    @Override
    public Volume createVolumeForDriver(final long accountId, final String volumeName, final String driverName) {
        StorageDriver driver = objectManager.findAny(StorageDriver.class,
                STORAGE_DRIVER.NAME, driverName,
                STORAGE_DRIVER.ACCOUNT_ID, accountId,
                STORAGE_DRIVER.REMOVED, null);
        final Long driverId = driver == null ? null : driver.getId();
        return DeferredUtils.nest(new Callable<Volume>() {
            @Override
            public Volume call() throws Exception {
                return resourceDao.createAndSchedule(Volume.class,
                        VOLUME.NAME, volumeName,
                        VOLUME.ACCOUNT_ID, accountId,
                        VOLUME.STORAGE_DRIVER_ID, driverId,
                        VolumeConstants.FIELD_VOLUME_DRIVER, driverName);
            }
        });
    }

    @Override
    public Map<Long, List<MountEntry>> getMountsForInstances(List<Long> ids, final IdFormatter idF) {
        final Map<Long, List<MountEntry>> result = new HashMap<>();
        create().select(INSTANCE.NAME, VOLUME.NAME, VOLUME.ID, MOUNT.PERMISSIONS, MOUNT.PATH, MOUNT.INSTANCE_ID)
            .from(MOUNT)
            .join(VOLUME)
                .on(VOLUME.ID.eq(MOUNT.VOLUME_ID))
            .join(INSTANCE)
                .on(INSTANCE.ID.eq(MOUNT.INSTANCE_ID))
            .where(MOUNT.REMOVED.isNull()
                    .and(VOLUME.REMOVED.isNull())
                    .and(MOUNT.INSTANCE_ID.in(ids)))
            .fetchInto(new RecordHandler<Record6<String, String, Long, String, String, Long>>() {
                @Override
                public void next(Record6<String, String, Long, String, String, Long> record) {
                    Long instanceId = record.getValue(MOUNT.INSTANCE_ID);
                    List<MountEntry> entries = result.get(instanceId);
                    if (entries == null) {
                        entries = new ArrayList<>();
                        result.put(instanceId, entries);
                    }

                    MountEntry mount = new MountEntry();
                    mount.setInstanceName(record.getValue(INSTANCE.NAME));
                    mount.setInstanceId(idF.formatId(InstanceConstants.TYPE, instanceId));
                    mount.setPath(record.getValue(MOUNT.PATH));
                    mount.setPermission(record.getValue(MOUNT.PERMISSIONS));
                    mount.setVolumeId(idF.formatId(VolumeConstants.TYPE, record.getValue(VOLUME.ID)));
                    mount.setVolumeName(record.getValue(VOLUME.NAME));
                    entries.add(mount);
                }
            });

        return result;
    }

    @Override
    public Map<Long, List<MountEntry>> getMountsForVolumes(List<Long> ids, final IdFormatter idF) {
        final Map<Long, List<MountEntry>> result = new HashMap<>();
        create().select(VOLUME.NAME, MOUNT.PERMISSIONS, MOUNT.PATH, MOUNT.INSTANCE_ID, MOUNT.VOLUME_ID, INSTANCE.NAME)
            .from(MOUNT)
            .join(INSTANCE)
                .on(INSTANCE.ID.eq(MOUNT.INSTANCE_ID))
            .join(VOLUME)
                .on(VOLUME.ID.eq(MOUNT.VOLUME_ID))
            .where(INSTANCE.REMOVED.isNull()
                    .and(VOLUME.REMOVED.isNull())
                    .and(MOUNT.VOLUME_ID.in(ids)))
            .fetchInto(new RecordHandler<Record6<String, String, String, Long, Long, String>>() {
                @Override
                public void next(Record6<String, String, String, Long, Long, String> record) {
                    Long volumeId = record.getValue(MOUNT.VOLUME_ID);
                    List<MountEntry> entries = result.get(volumeId);
                    if (entries == null) {
                        entries = new ArrayList<>();
                        result.put(volumeId, entries);
                    }

                    MountEntry mount = new MountEntry();
                    mount.setInstanceName(record.getValue(INSTANCE.NAME));
                    mount.setInstanceId(idF.formatId(InstanceConstants.TYPE, record.getValue(MOUNT.INSTANCE_ID)));
                    mount.setPath(record.getValue(MOUNT.PATH));
                    mount.setPermission(record.getValue(MOUNT.PERMISSIONS));
                    mount.setVolumeId(idF.formatId(VolumeConstants.TYPE, volumeId));
                    mount.setVolumeName(record.getValue(VOLUME.NAME));

                    entries.add(mount);
                }
            });

        return result;
    }
}
