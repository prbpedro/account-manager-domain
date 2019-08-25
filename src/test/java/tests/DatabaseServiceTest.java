package tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.services.DatabaseService;
import com.github.prbpedro.accountmanager.domain.util.Startup;

import tests.util.mock.ConfigurationServiceMock;
import tests.util.mock.ConfigurationServiceMock2;
import tests.util.mock.ConfigurationServiceMock3;

public class DatabaseServiceTest {

	@BeforeClass
	public static void configure() throws SQLException
	{
		try {
			
			Startup.configure();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws SQLException, IOException
	{	
		DatabaseService instance = Startup.getInjector().getInstance(DatabaseService.class);
		String s = instance.getDatabaseData();
		instance.setConfigurationService(new ConfigurationServiceMock());
		instance.createDatabase();
		instance.setConfigurationService(new ConfigurationServiceMock2());
		instance.createDatabase();
		instance.setConfigurationService(new ConfigurationServiceMock3());
		instance.createDatabase();
		Assert.assertNotNull(s);
	}
}
