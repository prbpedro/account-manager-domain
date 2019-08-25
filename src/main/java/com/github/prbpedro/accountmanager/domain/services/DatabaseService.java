package com.github.prbpedro.accountmanager.domain.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.github.prbpedro.accountmanager.domain.DefaultCatalog;
import com.github.prbpedro.accountmanager.domain.services.interfaces.IConfigurationService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;
import com.github.prbpedro.accountmanager.domain.tables.Account;
import com.github.prbpedro.accountmanager.domain.tables.AccountBalance;
import com.github.prbpedro.accountmanager.domain.tables.AccountTransaction;
import com.github.prbpedro.accountmanager.domain.tables.Bank;
import com.github.prbpedro.accountmanager.domain.tables.Currency;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.google.inject.Inject;

/**
 * Database service class.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class DatabaseService implements IDatabaseService{
	/**
	 * configurationService attribute.
	 */
	@Inject
	private IConfigurationService configurationService;
	
	/**
	 * Setter method for configurationService for tests
	 * 
	 * @param configurationService
	 */
	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Method responsible for creating a Sql connection.
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection createConnection() throws SQLException {
		
		return DriverManager.getConnection(configurationService.getDatabaseConnectionString(),
				configurationService.getDatabaseUserName(), configurationService.getDatabaseUserPassword());
	}

	/**
	 * Method responsible for creating the database.
	 * 
	 * @throws SQLException
	 */
	public void createDatabase() throws SQLException {
		
		try (Connection conn = createConnection()) {
			
			DSLContext context = DSL.using(conn, SQLDialect.H2);
			
			if(configurationService.isResetDatabase()) {				
				String sql;
				for (Query query : context.ddl(DefaultCatalog.DEFAULT_CATALOG.AC_DATABASE)) {
					try (Statement statement = conn.createStatement()) {
						sql = query.getSQL();
						sql = sql.replace(Constants.CREATE_TABLE, Constants.CREATE_TABLE + Constants.IF_NOT_EXISTS)
								.replace(Constants.ADD_CONSTRAINT, Constants.ADD_CONSTRAINT + Constants.IF_NOT_EXISTS)
								.replace(Constants.CREATE_SCHEMA, Constants.CREATE_SCHEMA + Constants.IF_NOT_EXISTS);
						
						statement.execute(sql);
					}
				}
			}
			
			if(configurationService.isCleanDatabase() || configurationService.isResetDatabase()) {
				cleanDatabase(context);
			}
			
			if(configurationService.isExecuteDefaultInserts()) {				
				insertDefaultValues(context);
			}
		}
	}

	/**
	 * Method responsible for inserting the DEFAULT values in the database.
	 * 
	 * @param context
	 */
	private void insertDefaultValues(DSLContext context) {
		Record revolut = context.insertInto(Bank.BANK, Bank.BANK.CODE).values(Constants.REVOLUT_BANK_CODE).returning()
				.fetchOne();

		Record boringBank = context.insertInto(Bank.BANK, Bank.BANK.CODE).values(Constants.BORINGBANK).returning()
				.fetchOne();

		Record gbp = context.insertInto(Currency.CURRENCY, Currency.CURRENCY.CODE).values(Constants.GBP).returning()
				.fetchOne();

		Record usd = context.insertInto(Currency.CURRENCY, Currency.CURRENCY.CODE).values(Constants.USD).returning()
				.fetchOne();

		Record brl = context.insertInto(Currency.CURRENCY, Currency.CURRENCY.CODE).values(Constants.BRL).returning()
				.fetchOne();

		insertDefaultAccounts(context, revolut, boringBank, gbp, usd, brl);
	}

	/**
	 * Method responsible for inserting the accounts and the relations of these in the database.
	 * 
	 * @param context
	 * @param revolut
	 * @param boringBank
	 * @param gbp
	 * @param usd
	 * @param brl
	 */
	private void insertDefaultAccounts(DSLContext context, Record revolut, Record boringBank, Record gbp, Record usd, Record brl) {
		for (int i = 0; i < 10; i++) {
			String idAcc = String.format(Constants.ACCOUNT_ID_PATTERN, i);
			context
				.insertInto(Account.ACCOUNT, 
					Account.ACCOUNT.ID, 
					Account.ACCOUNT.BANK_ID,
					Account.ACCOUNT.ACTIVE)
				.values(idAcc,
						i % 2 == 0 ? revolut.get(Bank.BANK.ID) : boringBank.get(Bank.BANK.ID),
						i > 7 ? false : true)
				.execute();
			
			if (i % 2 == 0) {
				context
					.insertInto(AccountBalance.ACCOUNT_BALANCE, 
							AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID, 
						AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID, 
						AccountBalance.ACCOUNT_BALANCE.VALUE)
					.values(idAcc, gbp.get(Currency.CURRENCY.ID), BigDecimal.valueOf(i*1000))
					.execute();
			} else {
				context
				.insertInto(AccountBalance.ACCOUNT_BALANCE, 
						AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID, 
					AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID, 
					AccountBalance.ACCOUNT_BALANCE.VALUE)
				.values(idAcc,usd.get(Currency.CURRENCY.ID), BigDecimal.valueOf(i*100))
				.execute();
			}

			if (i % 3 == 0) {
				context
				.insertInto(AccountBalance.ACCOUNT_BALANCE, 
						AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID, 
					AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID, 
					AccountBalance.ACCOUNT_BALANCE.VALUE)
				.values(idAcc,brl.get(Currency.CURRENCY.ID), BigDecimal.valueOf(i*1000000))
				.execute();
			}
		}
	}

	/**
	 * Method responsible for cleaning the database.
	 * 
	 * @param context
	 */
	private void cleanDatabase(DSLContext context) {
		context.deleteFrom(AccountTransaction.ACCOUNT_TRANSACTION).execute();
		context.deleteFrom(AccountBalance.ACCOUNT_BALANCE).execute();
		context.deleteFrom(Account.ACCOUNT).execute();
		context.deleteFrom(Bank.BANK).execute();
		context.deleteFrom(Currency.CURRENCY).execute();
	}

	/**
	 * Method responsible for return the Database data
	 * 
	 * @return String
	 * @throws SQLException 
	 */
	public String getDatabaseData() throws SQLException {
		try (Connection conn = createConnection()) {
			DSLContext context = DSL.using(conn, SQLDialect.H2);
			StringBuilder sb = new StringBuilder();
			sb.append(context.selectFrom(Bank.BANK).fetch().toString() + Constants.BREAK);
			sb.append(context.selectFrom(Currency.CURRENCY).fetch().toString() + Constants.BREAK);
			sb.append(context.selectFrom(Account.ACCOUNT).fetch().toString() + Constants.BREAK);
			sb.append(context.selectFrom(AccountBalance.ACCOUNT_BALANCE).fetch().toString() + Constants.BREAK);
			sb.append(context.selectFrom(AccountTransaction.ACCOUNT_TRANSACTION).fetch().toString() + Constants.BREAK);
			return sb.toString();
		}
	}
}