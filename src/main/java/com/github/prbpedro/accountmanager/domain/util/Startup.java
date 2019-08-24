package com.github.prbpedro.accountmanager.domain.util;

import java.sql.SQLException;

import com.github.prbpedro.accountmanager.domain.guice.AccountManagerModule;
import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Class responsible for configuring this component CDI and database.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class Startup{
	
	private static Injector injector;
	
	/**
	 * Method responsible for configuring this component CDI and database.
	 * 
	 * @throws SQLException
	 */
	public static void configure() throws SQLException {
		if(injector==null) {
			injector = Guice.createInjector(new AccountManagerModule());
		}
		
		injector.getInstance(IDatabaseService.class).createDatabase();
	}
	
	/**
	 * Method responsible for return the Guice injector.
	 * 
	 * @return Injector
	 */
	public static Injector getInjector() {
		return injector;
	}
}
