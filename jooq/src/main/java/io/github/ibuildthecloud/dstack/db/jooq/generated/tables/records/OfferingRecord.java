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
@javax.persistence.Table(name = "offering", schema = "dstack")
public class OfferingRecord extends org.jooq.impl.UpdatableRecordImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.OfferingRecord> implements org.jooq.Record8<java.lang.Long, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -645843414;

	/**
	 * Setter for <code>dstack.offering.id</code>. 
	 */
	public void setId(java.lang.Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>dstack.offering.id</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>dstack.offering.name</code>. 
	 */
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>dstack.offering.name</code>. 
	 */
	@javax.persistence.Column(name = "name", nullable = false, length = 255)
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>dstack.offering.kind</code>. 
	 */
	public void setKind(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>dstack.offering.kind</code>. 
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	public java.lang.String getKind() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>dstack.offering.created</code>. 
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>dstack.offering.created</code>. 
	 */
	@javax.persistence.Column(name = "created", nullable = false)
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(3);
	}

	/**
	 * Setter for <code>dstack.offering.removed</code>. 
	 */
	public void setRemoved(java.sql.Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>dstack.offering.removed</code>. 
	 */
	@javax.persistence.Column(name = "removed")
	public java.sql.Timestamp getRemoved() {
		return (java.sql.Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>dstack.offering.is_public</code>. 
	 */
	public void setIsPublic(java.lang.Boolean value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>dstack.offering.is_public</code>. 
	 */
	@javax.persistence.Column(name = "is_public", nullable = false, precision = 1)
	public java.lang.Boolean getIsPublic() {
		return (java.lang.Boolean) getValue(5);
	}

	/**
	 * Setter for <code>dstack.offering.description</code>. 
	 */
	public void setDescription(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>dstack.offering.description</code>. 
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>dstack.offering.uuid</code>. 
	 */
	public void setUuid(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>dstack.offering.uuid</code>. 
	 */
	@javax.persistence.Column(name = "uuid", nullable = false, length = 128)
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(7);
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
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Long, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Long, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.KIND;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field4() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field5() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field6() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.IS_PUBLIC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING.UUID;
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
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getKind();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value4() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value5() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value6() {
		return getIsPublic();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getUuid();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OfferingRecord
	 */
	public OfferingRecord() {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING);
	}

	/**
	 * Create a detached, initialised OfferingRecord
	 */
	public OfferingRecord(java.lang.Long id, java.lang.String name, java.lang.String kind, java.sql.Timestamp created, java.sql.Timestamp removed, java.lang.Boolean isPublic, java.lang.String description, java.lang.String uuid) {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Offering.OFFERING);

		setValue(0, id);
		setValue(1, name);
		setValue(2, kind);
		setValue(3, created);
		setValue(4, removed);
		setValue(5, isPublic);
		setValue(6, description);
		setValue(7, uuid);
	}
}
