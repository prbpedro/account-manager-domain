/*
 * This file is generated by jOOQ.
 */
package com.github.prbpedro.accountmanager.domain.tables;


import com.github.prbpedro.accountmanager.domain.AcDatabase;
import com.github.prbpedro.accountmanager.domain.Indexes;
import com.github.prbpedro.accountmanager.domain.Keys;
import com.github.prbpedro.accountmanager.domain.tables.records.CurrencyRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Currency extends TableImpl<CurrencyRecord> {

    private static final long serialVersionUID = -360853122;

    /**
     * The reference instance of <code>AC_DATABASE.CURRENCY</code>
     */
    public static final Currency CURRENCY = new Currency();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CurrencyRecord> getRecordType() {
        return CurrencyRecord.class;
    }

    /**
     * The column <code>AC_DATABASE.CURRENCY.ID</code>.
     */
    public final TableField<CurrencyRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>AC_DATABASE.CURRENCY.CODE</code>.
     */
    public final TableField<CurrencyRecord, String> CODE = createField("CODE", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * Create a <code>AC_DATABASE.CURRENCY</code> table reference
     */
    public Currency() {
        this(DSL.name("CURRENCY"), null);
    }

    /**
     * Create an aliased <code>AC_DATABASE.CURRENCY</code> table reference
     */
    public Currency(String alias) {
        this(DSL.name(alias), CURRENCY);
    }

    /**
     * Create an aliased <code>AC_DATABASE.CURRENCY</code> table reference
     */
    public Currency(Name alias) {
        this(alias, CURRENCY);
    }

    private Currency(Name alias, Table<CurrencyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Currency(Name alias, Table<CurrencyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Currency(Table<O> child, ForeignKey<O, CurrencyRecord> key) {
        super(child, key, CURRENCY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return AcDatabase.AC_DATABASE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_5, Indexes.UNIQUE_CODE_CURRENCY_INDEX_5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CurrencyRecord, Long> getIdentity() {
        return Keys.IDENTITY_CURRENCY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CurrencyRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CurrencyRecord>> getKeys() {
        return Arrays.<UniqueKey<CurrencyRecord>>asList(Keys.CONSTRAINT_5, Keys.UNIQUE_CODE_CURRENCY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Currency as(String alias) {
        return new Currency(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Currency as(Name alias) {
        return new Currency(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(String name) {
        return new Currency(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(Name name) {
        return new Currency(name, null);
    }
}
