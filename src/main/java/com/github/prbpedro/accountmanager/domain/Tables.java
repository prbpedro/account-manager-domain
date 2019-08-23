/*
 * This file is generated by jOOQ.
 */
package com.github.prbpedro.accountmanager.domain;


import com.github.prbpedro.accountmanager.domain.tables.Account;
import com.github.prbpedro.accountmanager.domain.tables.AccountBalance;
import com.github.prbpedro.accountmanager.domain.tables.AccountTransaction;
import com.github.prbpedro.accountmanager.domain.tables.Bank;
import com.github.prbpedro.accountmanager.domain.tables.Currency;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in AC_DATABASE
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>AC_DATABASE.ACCOUNT</code>.
     */
    public static final Account ACCOUNT = com.github.prbpedro.accountmanager.domain.tables.Account.ACCOUNT;

    /**
     * The table <code>AC_DATABASE.ACCOUNT_BALANCE</code>.
     */
    public static final AccountBalance ACCOUNT_BALANCE = com.github.prbpedro.accountmanager.domain.tables.AccountBalance.ACCOUNT_BALANCE;

    /**
     * The table <code>AC_DATABASE.ACCOUNT_TRANSACTION</code>.
     */
    public static final AccountTransaction ACCOUNT_TRANSACTION = com.github.prbpedro.accountmanager.domain.tables.AccountTransaction.ACCOUNT_TRANSACTION;

    /**
     * The table <code>AC_DATABASE.BANK</code>.
     */
    public static final Bank BANK = com.github.prbpedro.accountmanager.domain.tables.Bank.BANK;

    /**
     * The table <code>AC_DATABASE.CURRENCY</code>.
     */
    public static final Currency CURRENCY = com.github.prbpedro.accountmanager.domain.tables.Currency.CURRENCY;
}