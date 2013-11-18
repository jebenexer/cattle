package io.github.ibuildthecloud.dstack.lock.impl;

import io.github.ibuildthecloud.dstack.lock.Lock;
import io.github.ibuildthecloud.dstack.lock.LockCallbackWithException;
import io.github.ibuildthecloud.dstack.lock.LockManager;
import io.github.ibuildthecloud.dstack.lock.definition.LockDefinition;
import io.github.ibuildthecloud.dstack.lock.definition.MultiLockDefinition;
import io.github.ibuildthecloud.dstack.lock.provider.LockProvider;
import io.github.ibuildthecloud.dstack.util.init.AfterExtensionInitialization;
import io.github.ibuildthecloud.dstack.util.init.InitializationUtils;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockManagerImpl extends AbstractLockManagerImpl implements LockManager {

    private static final Logger log = LoggerFactory.getLogger(LockManagerImpl.class);

    List<LockProvider> lockProviders;
    LockProvider lockProvider;

    @Override
    protected <T, E extends Throwable> T doLock(LockDefinition lockDef, LockCallbackWithException<T, E> callback,
            WithLock with) throws E {
        Lock lock = null;
        try {
            lock = getLock(lockDef);

            /* Important to lock in the try because the Lock may be a multi-lock
             * and if a multi-lock fails to lock it can be in a half locked state
             * so it must be unlocked
             */
            if ( ! with.withLock(lock) )
                return null;

            return callback.doWithLock();
        } finally {
            if ( lock != null ) {
                try {
                    lock.unlock();
                } catch (Throwable t) {
                    /* Should never happen, but I don't trust people */
                    log.error("Failed to unlock [{}], unlock() should never throw an exception", lock.getLockDefinition(), t); 
                }
                releaseLock(lock);
            }
        }
    }

    protected void releaseLock(Lock lock) {
        if ( lock instanceof MultiLock ) {
            for ( Lock lockPart : ((MultiLock)lock).getLocks() ) {
                try {
                    lockProvider.releaseLock(lockPart);
                } catch ( Throwable t ) {
                    /* Should never happen, but I don't trust people */
                    log.error("Failed to release lock [{}], releaseLock() should never throw an exception", lockPart.getLockDefinition(), t); 
                }
            }
        } else {
            lockProvider.releaseLock(lock);
        }
    }

    protected Lock getLock(LockDefinition def) {
        if ( def instanceof MultiLockDefinition ) {
            return new MultiLock((MultiLockDefinition)def, lockProvider);
        } else {
            return lockProvider.getLock(def);
        }
    }

    @Override
    public LockProvider getLockProvider() {
        return lockProvider;
    }

    @PostConstruct
    public void init() {
        InitializationUtils.onInitialization(this, lockProviders);
    }

    @AfterExtensionInitialization
    public void activateLockProvider() {
        if ( lockProviders.size() == 0 )
            throw new IllegalStateException("Failed to find lock provider");

        lockProvider = lockProviders.get(0);
        lockProvider.activate();        
    }

    public List<LockProvider> getLockProviders() {
        return lockProviders;
    }

    @Inject
    public void setLockProviders(List<LockProvider> lockProviders) {
        this.lockProviders = lockProviders;
    }

}
