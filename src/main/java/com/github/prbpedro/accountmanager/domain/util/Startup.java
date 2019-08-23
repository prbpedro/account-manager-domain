package com.github.prbpedro.accountmanager.domain.util;

import java.sql.SQLException;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import com.github.prbpedro.accountmanager.domain.cdi.producer.LoggerProducer;
import com.github.prbpedro.accountmanager.domain.services.ConfigurationService;
import com.github.prbpedro.accountmanager.domain.services.DatabaseService;
import com.github.prbpedro.accountmanager.domain.services.TransactionValidationService;
import com.github.prbpedro.accountmanager.domain.services.TransferTransactionService;

/**
 * Class responsible for configuring this component CDI and database.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class Startup {
	
	private static SeContainer container;

	/**
	 * Method responsible for configuring this component CDI and database.
	 * 
	 * @throws SQLException
	 */
	public static void configure() throws SQLException {
		if(container==null) {
			SeContainerInitializer init = SeContainerInitializer.newInstance();
			
			init.addBeanClasses(ConfigurationService.class);
			init.addBeanClasses(DatabaseService.class);
			init.addBeanClasses(TransactionValidationService.class);
			init.addBeanClasses(TransferTransactionService.class);
			init.addBeanClasses(LoggerProducer.class);
			
			container = init.initialize();
			
			container.select(DatabaseService.class).get().createDatabase();
		}
	}
	
	/**
	 * Method responsible for return the CDI container.
	 * 
	 * @returnSeContainer
	 */
	public static SeContainer getContainer() {
		return container;
	}
}
