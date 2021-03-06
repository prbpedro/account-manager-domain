/*
 * This file is generated by jOOQ.
 */
package com.github.prbpedro.accountmanager.domain.tables;


import com.github.prbpedro.accountmanager.domain.AcDatabase;
import com.github.prbpedro.accountmanager.domain.Indexes;
import com.github.prbpedro.accountmanager.domain.Keys;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = 1037603340;

    /**
     * The reference instance of <code>AC_DATABASE.ACCOUNT</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>AC_DATABASE.ACCOUNT.ID</code>.
     */
    public final TableField<AccountRecord, String> ID = createField("ID", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>AC_DATABASE.ACCOUNT.ACTIVE</code>.
     */
    public final TableField<AccountRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>AC_DATABASE.ACCOUNT.BANK_ID</code>.
     */
    public final TableField<AccountRecord, Long> BANK_ID = createField("BANK_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>AC_DATABASE.ACCOUNT</code> table reference
     */
    public Account() {
        this(DSL.name("ACCOUNT"), null);
    }

    /**
     * Create an aliased <code>AC_DATABASE.ACCOUNT</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>AC_DATABASE.ACCOUNT</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
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
        return Arrays.<Index>asList(Indexes.FKAWL1LRPNGB7H5KTG79ODEIC5W_INDEX_E, Indexes.PRIMARY_KEY_E);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_E;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccountRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountRecord>>asList(Keys.CONSTRAINT_E);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AccountRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccountRecord, ?>>asList(Keys.FKAWL1LRPNGB7H5KTG79ODEIC5W);
    }

    public Bank bank() {
        return new Bank(this, Keys.FKAWL1LRPNGB7H5KTG79ODEIC5W);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }
}
