/**
 * 
 */
package com.github.prbpedro.accountmanager.domain.services.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database service interface
 * 
 * @author Pedro Ribeiro Baptista
 */
public interface IDatabaseService {

	/**
	 * Method responsible for creating a Sql connection.
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	Connection createConnection() throws SQLException;
	
	/**
	 * Method responsible for creating the database.
	 * 
	 * @throws SQLException
	 */
	void createDatabase() throws SQLException;
	
	/**
	 * Method responsible for return the Database data
	 * 
	 * @return String
	 * @throws SQLException 
	 */
	String getDatabaseData() throws SQLException;
}
