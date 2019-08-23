/*
 * This file is generated by jOOQ.
 */
package com.github.prbpedro.accountmanager.domain.tables;


import java.math.BigDecimal;
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

import com.github.prbpedro.accountmanager.domain.AcDatabase;
import com.github.prbpedro.accountmanager.domain.Indexes;
import com.github.prbpedro.accountmanager.domain.Keys;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountBalanceRecord;


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
public class AccountBalance extends TableImpl<AccountBalanceRecord> {

    private static final long serialVersionUID = 1410625413;

    /**
     * The reference instance of <code>AC_DATABASE.ACCOUNT_BALANCE</code>
     */
    public static final AccountBalance ACCOUNT_BALANCE = new AccountBalance();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountBalanceRecord> getRecordType() {
        return AccountBalanceRecord.class;
    }

    /**
     * The column <code>AC_DATABASE.ACCOUNT_BALANCE.ID</code>.
     */
    public final TableField<AccountBalanceRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>AC_DATABASE.ACCOUNT_BALANCE.VALUE</code>.
     */
    public final TableField<AccountBalanceRecord, BigDecimal> VALUE = createField("VALUE", org.jooq.impl.SQLDataType.DECIMAL(19, 2).nullable(false), this, "");

    /**
     * The column <code>AC_DATABASE.ACCOUNT_BALANCE.ACCOUNT_ID</code>.
     */
    public final TableField<AccountBalanceRecord, String> ACCOUNT_ID = createField("ACCOUNT_ID", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>AC_DATABASE.ACCOUNT_BALANCE.CURRENCY_ID</code>.
     */
    public final TableField<AccountBalanceRecord, Long> CURRENCY_ID = createField("CURRENCY_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>AC_DATABASE.ACCOUNT_BALANCE</code> table reference
     */
    public AccountBalance() {
        this(DSL.name("ACCOUNT_BALANCE"), null);
    }

    /**
     * Create an aliased <code>AC_DATABASE.ACCOUNT_BALANCE</code> table reference
     */
    public AccountBalance(String alias) {
        this(DSL.name(alias), ACCOUNT_BALANCE);
    }

    /**
     * Create an aliased <code>AC_DATABASE.ACCOUNT_BALANCE</code> table reference
     */
    public AccountBalance(Name alias) {
        this(alias, ACCOUNT_BALANCE);
    }

    private AccountBalance(Name alias, Table<AccountBalanceRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccountBalance(Name alias, Table<AccountBalanceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AccountBalance(Table<O> child, ForeignKey<O, AccountBalanceRecord> key) {
        super(child, key, ACCOUNT_BALANCE);
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
        return Arrays.<Index>asList(Indexes.FKEA365BS21NVTP9ENUUYXIM4TI_INDEX_E, Indexes.FKLIXFD5D2QJUX0B3PEYPCPEOL1_INDEX_E, Indexes.PRIMARY_KEY_E1, Indexes.UNIQUE_ACCOUNT_CURRENCY_INDEX_5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AccountBalanceRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACCOUNT_BALANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccountBalanceRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_E1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccountBalanceRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountBalanceRecord>>asList(Keys.CONSTRAINT_E1, Keys.UNIQUE_ACCOUNT_CURRENCY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AccountBalanceRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccountBalanceRecord, ?>>asList(Keys.FKLIXFD5D2QJUX0B3PEYPCPEOL1, Keys.FKEA365BS21NVTP9ENUUYXIM4TI);
    }

    public Account account() {
        return new Account(this, Keys.FKLIXFD5D2QJUX0B3PEYPCPEOL1);
    }

    public Currency currency() {
        return new Currency(this, Keys.FKEA365BS21NVTP9ENUUYXIM4TI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountBalance as(String alias) {
        return new AccountBalance(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountBalance as(Name alias) {
        return new AccountBalance(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountBalance rename(String name) {
        return new AccountBalance(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountBalance rename(Name name) {
        return new AccountBalance(name, null);
    }
}
