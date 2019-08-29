# account-manager-domain
![Travis](https://api.travis-ci.org/prbpedro/account-manager-domain.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/prbpedro/account-manager-domain/badge.svg)](https://coveralls.io/github/prbpedro/account-manager-domain)

## Technologies 

Used technologies:
* JAVA JDK 1.8
* Google Guice (Dependency injection framework)
* Database Access Framework (JOOQ)
* H2 Database
* SLF4J (Log framework)
* Maven (Compilation and automation tool)
* JUnit (Test framework)
* jacoco-maven-plugin (Test code coverege maven plugin)
* Github repository

## Project desciption

This is a JAVA Eclipse project built to demonstrate the use of the technologies described above by providing the <b>TransferTransactionService</b> service class capable of making a transfer transaction in a banking system.

The following database tables were defined:
  * Bank
    * ID
    * CODE
  * Currency
    * ID
    * CODE
  * Account
    * ID
    * BANK_ID
    * ACTIVE
  * AccountBalance
    * ID
    * ACCOUNT_ID
    * CURRENCY_ID
    * VALUE
  * AccountTransaction
    * ID
    * SENDER_ID
    * BENEFICIARY_ID
    * CURRENCY_ID
    * DATE_TIME
    * STATUS
    * VALUE

The <b>ConfigurationService</b> class is capable of reading properties files in the resources folder for the application's configuration. Files can be created by publishing environment through the JAVA_ENVIRONMENT environment variable. Example: JAVA_ENVIRONMENT=Production => configProduction.properties.

The <b>DatabaseService</b> class provides methods to create a database, clean it up, and make test inserts by setting the properties below in the resources/config.properties file.
* databaseConnectionString (Database connection string)
* databaseUserName (Database user name)
* databaseUserPassword (Database user password)
* resetDatabase (true or false - rebuilds the database from scratch)
* executeDefaultInserts (true or false - Run inserts for testing)
* cleanDatabase (true or false - Clears the database)
	
The <b>TransactionValidationService</b> service class is responsible for validating transfer transactions by the following rules:
* The accounts involved must exist, be active and belong to REVOLUT!!!!! bank 
* The sender's account must have funds to do the transfer
* The currency must exist
* Transferred value must be positive and scaled to 19 and precision to 2. (Database limitations)

H2 Database in memory is used in the unit tests.
The unit testing code coverage report is available in the $PROJECT_FOLDER/target/site/jacoco/index.html file generated through the Maven plugin jacoco-maven-plugin.
The project documentation is available in the $PROJECT_FOLDER/target/apidocs/index.html file generated by the Maven plugin maven-javadoc-plugin and is attached into the Maven-generated JAR.
The maven plugins maven-compiler-plugin, maven-jar-plugin, maven-install-plugin, maven-surefire-plugin, maven-javadoc-plugin, jacoco-maven-plugin, and jooq-codegen-maven are configured in the pom.xml file.

## DATA
The data entered by the test inserts are (The ID's are autogenerated, minus table ACCOUNT):

<table>
  <th>
    <td colspan="2">BANK</td>
  </th>
  <tr>
    <td>ID</td>
    <td>CODE</td>
  </tr>
  <tr>
    <td>856</td>
    <td>REVOLUT!!!!!</td>
  </tr>
  <tr>
    <td>857</td>
    <td>BORINGBANK</td>
  </tr>
</table>

<table>
  <th>
    <td colspan="2">CURRENCY</td>
  </th>
  <tr>
    <td>ID</td>
    <td>CODE</td>
  </tr>
  <tr>
    <td>1061</td>
    <td>GBP</td>
  </tr>
  <tr>
    <td>1062</td>
    <td>USD</td>
  </tr>
  <tr>
    <td>1063</td>
    <td>BRL</td>
  </tr>
</table>

<table>
  <th>
    <td colspan="3">ACCOUNT</td>
  </th>
  <tr>
    <td>ID</td>
    <td>ACTIVE</td>
    <td>BANK_ID</td>
  </tr>
  <tr>
    <td>ACCOUNT_0</td>
    <td>true</td>
    <td>856</td>
  </tr>
  <tr>
    <td>ACCOUNT_1</td>
    <td>true</td>
    <td>857</td>
  </tr>
  <tr>
    <td>ACCOUNT_2</td>
    <td>true</td>
    <td>856</td>
  </tr>
  <tr>
    <td>ACCOUNT_3</td>
    <td>true</td>
    <td>857</td>
  </tr>
  <tr>
    <td>ACCOUNT_4</td>
    <td>true</td>
    <td>856</td>
  </tr>
  <tr>
    <td>ACCOUNT_5</td>
    <td>true</td>
    <td>857</td>
  </tr>
  <tr>
    <td>ACCOUNT_6</td>
    <td>true</td>
    <td>856</td>
  </tr>
  <tr>
    <td>ACCOUNT_7</td>
    <td>true</td>
    <td>857</td>
  </tr>
  <tr>
    <td>ACCOUNT_8</td>
    <td>false</td>
    <td>856</td>
  </tr>
  <tr>
    <td>ACCOUNT_9</td>
    <td>false</td>
    <td>857</td>
  </tr>
</table>

<table>
  <th>
    <td colspan="4">ACCOUNT BALLANCE</td>
  </th>
  <tr>
    <td>ID</td>
    <td>VALUE</td>
    <td>ACCOUNT_ID</td>
    <td>CURRENCY_ID</td>
  </tr>
  <tr>
    <td>3835<t/td>
    <td>0.00</td>
    <td>ACCOUNT_0</td>
    <td>1061</td>
  </tr>
  <tr>
    <td>3836<t/td>
    <td>0.00</td>
    <td>ACCOUNT_0</td>
    <td>1063</td>
  </tr>
  <tr>
    <td>3837<t/td>
    <td>100.00</td>
    <td>ACCOUNT_1</td>
    <td>1062</td>
  </tr>
  <tr>
    <td>3838<t/td>
    <td>2000.00</td>
    <td>ACCOUNT_2</td>
    <td>1061</td>
  </tr>
  <tr>
    <td>3839<t/td>
    <td>300.00</td>
    <td>ACCOUNT_3</td>
    <td>1062</td>
  </tr>
  <tr>
    <td>3840<t/td>
    <td>3000000.00</td>
    <td>ACCOUNT_3</td>
    <td>1063</td>
  </tr>
  <tr>
    <td>3841<t/td>
    <td>4000.00</td>
    <td>ACCOUNT_4</td>
    <td>1061</td>
  </tr>
  <tr>
    <td>3842<t/td>
    <td>500.00</td>
    <td>ACCOUNT_5</td>
    <td>1062</td>
  </tr>
  <tr>
    <td>3843<t/td>
    <td>6000.00</td>
    <td>ACCOUNT_6</td>
    <td>1061</td>
  </tr>
  <tr>
    <td>3844<t/td>
    <td>6000000.00</td>
    <td>ACCOUNT_6</td>
    <td>1063</td>
  </tr>
  <tr>
    <td>3845<t/td>
    <td>700.00</td>
    <td>ACCOUNT_7</td>
    <td>1062</td>
  </tr>
  <tr>
    <td>3846<t/td>
    <td>8000.00</td>
    <td>ACCOUNT_8</td>
    <td>1061</td>
  </tr>
  <tr>
    <td>3847<t/td>
    <td>900.00</td>
    <td>ACCOUNT_9</td>
    <td>1062</td>
  </tr>
  <tr>
    <td>3848<t/td>
    <td>9000000.00</td>
    <td>ACCOUNT_9</td>
    <td>1063</td>
  </tr>
</table>
