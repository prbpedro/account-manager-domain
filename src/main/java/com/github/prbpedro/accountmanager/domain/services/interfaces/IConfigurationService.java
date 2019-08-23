package com.github.prbpedro.accountmanager.domain.services.interfaces;

import java.io.IOException;
import java.util.Properties;

/**
 * ConfigurationService interface.
 * 
 * @author Pedro Ribeiro Baptista
 */
public interface IConfigurationService {
	
	/**
	 * Method responsible for reading a file into an object of the class Properties.
	 * 
	 * @param prop
	 * @param propFileName
	 * @throws IOException
	 */
	void readPropFile(Properties prop, String propFileName) throws IOException;
	
	/**
	 * javaEnvironment getter.
	 * 
	 * @return String
	 */
	String getJavaEnvironment();

	/**
	 * databaseConnectionString getter.
	 * 
	 * @return String
	 */
	String getDatabaseConnectionString();

	/**
	 * databaseUserName getter.
	 * 
	 * @return String
	 */
	String getDatabaseUserName();

	/**
	 * databaseUserPassword getter.
	 * 
	 * @return String
	 */
	String getDatabaseUserPassword();

	/**
	 * resetDatabase getter.
	 * 
	 * @return boolean
	 */
	boolean isResetDatabase();

	/**
	 * cleanDatabase getter.
	 * 
	 * @return boolean
	 */
	boolean isCleanDatabase();

	/**
	 * executeDefaultInserts getter.
	 * 
	 * @return boolean
	 */
	boolean isExecuteDefaultInserts();
}
