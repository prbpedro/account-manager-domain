/*
 * This file is generated by jOOQ.
 */
package com.github.prbpedro.accountmanager.domain.tables.records;


import com.github.prbpedro.accountmanager.domain.tables.Currency;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrencyRecord extends UpdatableRecordImpl<CurrencyRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 137054486;

    /**
     * Setter for <code>AC_DATABASE.CURRENCY.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>AC_DATABASE.CURRENCY.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>AC_DATABASE.CURRENCY.CODE</code>.
     */
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>AC_DATABASE.CURRENCY.CODE</code>.
     */
    public String getCode() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Currency.CURRENCY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Currency.CURRENCY.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrencyRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrencyRecord value2(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrencyRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CurrencyRecord
     */
    public CurrencyRecord() {
        super(Currency.CURRENCY);
    }

    /**
     * Create a detached, initialised CurrencyRecord
     */
    public CurrencyRecord(Long id, String code) {
        super(Currency.CURRENCY);

        set(0, id);
        set(1, code);
    }
}
