/**
 * This class is generated by jOOQ
 */
package io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "ip_address", schema = "dstack")
public class IpAddressRecord extends org.jooq.impl.UpdatableRecordImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.IpAddressRecord> implements org.jooq.Record14<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 278054156;

	/**
	 * Setter for <code>dstack.ip_address.id</code>. 
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.id</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>dstack.ip_address.address</code>. 
	 */
	public void setAddress(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.address</code>. 
	 */
	@javax.persistence.Column(name = "address", nullable = false, length = 128)
	public java.lang.String getAddress() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>dstack.ip_address.account_id</code>. 
	 */
	public void setAccountId(java.lang.Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.account_id</code>. 
	 */
	@javax.persistence.Column(name = "account_id", nullable = false, precision = 19)
	public java.lang.Long getAccountId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>dstack.ip_address.netmask</code>. 
	 */
	public void setNetmask(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.netmask</code>. 
	 */
	@javax.persistence.Column(name = "netmask", length = 128)
	public java.lang.String getNetmask() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>dstack.ip_address.gateway</code>. 
	 */
	public void setGateway(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.gateway</code>. 
	 */
	@javax.persistence.Column(name = "gateway", length = 128)
	public java.lang.String getGateway() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>dstack.ip_address.version</code>. 
	 */
	public void setVersion(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.version</code>. 
	 */
	@javax.persistence.Column(name = "version", nullable = false, length = 255)
	public java.lang.String getVersion() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>dstack.ip_address.vnet_id</code>. 
	 */
	public void setVnetId(java.lang.Long value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.vnet_id</code>. 
	 */
	@javax.persistence.Column(name = "vnet_id", precision = 19)
	public java.lang.Long getVnetId() {
		return (java.lang.Long) getValue(6);
	}

	/**
	 * Setter for <code>dstack.ip_address.ip_assignment_type</code>. 
	 */
	public void setIpAssignmentType(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.ip_assignment_type</code>. 
	 */
	@javax.persistence.Column(name = "ip_assignment_type", length = 255)
	public java.lang.String getIpAssignmentType() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>dstack.ip_address.item_pool_id</code>. 
	 */
	public void setItemPoolId(java.lang.Long value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.item_pool_id</code>. 
	 */
	@javax.persistence.Column(name = "item_pool_id", precision = 19)
	public java.lang.Long getItemPoolId() {
		return (java.lang.Long) getValue(8);
	}

	/**
	 * Setter for <code>dstack.ip_address.created</code>. 
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.created</code>. 
	 */
	@javax.persistence.Column(name = "created")
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(9);
	}

	/**
	 * Setter for <code>dstack.ip_address.removed</code>. 
	 */
	public void setRemoved(java.sql.Timestamp value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.removed</code>. 
	 */
	@javax.persistence.Column(name = "removed")
	public java.sql.Timestamp getRemoved() {
		return (java.sql.Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>dstack.ip_address.remove_time</code>. 
	 */
	public void setRemoveTime(java.sql.Timestamp value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.remove_time</code>. 
	 */
	@javax.persistence.Column(name = "remove_time")
	public java.sql.Timestamp getRemoveTime() {
		return (java.sql.Timestamp) getValue(11);
	}

	/**
	 * Setter for <code>dstack.ip_address.name</code>. 
	 */
	public void setName(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.name</code>. 
	 */
	@javax.persistence.Column(name = "name", length = 255)
	public java.lang.String getName() {
		return (java.lang.String) getValue(12);
	}

	/**
	 * Setter for <code>dstack.ip_address.description</code>. 
	 */
	public void setDescription(java.lang.String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>dstack.ip_address.description</code>. 
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(13);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record14 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row14<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row14) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row14<java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row14) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.ADDRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field3() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.ACCOUNT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.NETMASK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.GATEWAY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field7() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.VNET_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.IP_ASSIGNMENT_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field9() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.ITEM_POOL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field10() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field11() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field12() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.REMOVE_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field14() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value3() {
		return getAccountId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getNetmask();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getGateway();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value7() {
		return getVnetId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getIpAssignmentType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value9() {
		return getItemPoolId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value10() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value11() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value12() {
		return getRemoveTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value14() {
		return getDescription();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached IpAddressRecord
	 */
	public IpAddressRecord() {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS);
	}

	/**
	 * Create a detached, initialised IpAddressRecord
	 */
	public IpAddressRecord(java.lang.Long id, java.lang.String address, java.lang.Long accountId, java.lang.String netmask, java.lang.String gateway, java.lang.String version, java.lang.Long vnetId, java.lang.String ipAssignmentType, java.lang.Long itemPoolId, java.sql.Timestamp created, java.sql.Timestamp removed, java.sql.Timestamp removeTime, java.lang.String name, java.lang.String description) {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.IpAddress.IP_ADDRESS);

		setValue(0, id);
		setValue(1, address);
		setValue(2, accountId);
		setValue(3, netmask);
		setValue(4, gateway);
		setValue(5, version);
		setValue(6, vnetId);
		setValue(7, ipAssignmentType);
		setValue(8, itemPoolId);
		setValue(9, created);
		setValue(10, removed);
		setValue(11, removeTime);
		setValue(12, name);
		setValue(13, description);
	}
}
