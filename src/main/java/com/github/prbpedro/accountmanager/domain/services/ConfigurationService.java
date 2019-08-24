package com.github.prbpedro.accountmanager.domain.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.prbpedro.accountmanager.domain.services.interfaces.IConfigurationService;
import com.github.prbpedro.accountmanager.domain.util.Constants;

/**
 * Configuration service class.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class ConfigurationService implements IConfigurationService{
	
	private String javaEnvironment;
	
	private String databaseConnectionString;
	
	private String databaseUserName;
	
	private String databaseUserPassword;
	
	private boolean resetDatabase;
	
	private boolean cleanDatabase;
	
	private boolean executeDefaultInserts;
	
	/**
	 * Contrutor that reads the configuration properties files.
	 * 
	 * @throws IOException
	 */
	public ConfigurationService() throws IOException
	{
		Properties prop = new Properties();
		readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, Constants.EMPTY));
		
		String value = System.getenv(Constants.ENV_JAVA_ENVIRONMENT);
		if(value != null) {
			javaEnvironment = value;
			readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, javaEnvironment));
		}else {
			javaEnvironment = "";
		}
		
		databaseConnectionString = prop.getProperty(Constants.CONFIG_databaseConnectionString);
		databaseUserName = prop.getProperty(Constants.CONFIG_databaseUserName);
		databaseUserPassword = prop.getProperty(Constants.CONFIG_databaseUserPassword);
		cleanDatabase = Boolean.parseBoolean(prop.getProperty(Constants.CONFIG_cleanDatabase).toLowerCase());
		resetDatabase = Boolean.parseBoolean(prop.getProperty(Constants.CONFIG_resetDatabase).toLowerCase());
		executeDefaultInserts = Boolean.parseBoolean(prop.getProperty(Constants.CONFIG_executeDefaultInserts).toLowerCase());
	}

	/**
	 * Method responsible for reading a file into an object of the class Properties.
	 * 
	 * @param prop
	 * @param propFileName
	 * @throws IOException
	 */
	public void readPropFile(Properties prop, String propFileName) throws IOException{
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			prop.load(inputStream);
		}
	}

	/**
	 * javaEnvironment getter.
	 * 
	 * @return String
	 */
	public String getJavaEnvironment() {
		return javaEnvironment;
	}

	/**
	 * databaseConnectionString getter.
	 * 
	 * @return String
	 */
	public String getDatabaseConnectionString() {
		return databaseConnectionString;
	}

	/**
	 * databaseUserName getter.
	 * 
	 * @return String
	 */
	public String getDatabaseUserName() {
		return databaseUserName;
	}

	/**
	 * databaseUserPassword getter.
	 * 
	 * @return String
	 */
	public String getDatabaseUserPassword() {
		return databaseUserPassword;
	}

	/**
	 * resetDatabase getter.
	 * 
	 * @return boolean
	 */
	public boolean isResetDatabase() {
		return resetDatabase;
	}

	/**
	 * cleanDatabase getter.
	 * 
	 * @return boolean
	 */
	public boolean isCleanDatabase() {
		return cleanDatabase;
	}

	/**
	 * executeDefaultInserts getter.
	 * 
	 * @return boolean
	 */
	public boolean isExecuteDefaultInserts() {
		return executeDefaultInserts;
	}
}