package tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.services.ConfigurationService;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.github.prbpedro.accountmanager.domain.util.Startup;

public class ConfigurationServiceTests {
		
	@Test
	public void configurationServiceTest() throws IOException, SQLException
	{	
		Startup.configure();
		
		try {
			ConfigurationService configurationService = Startup.getInjector().getInstance(ConfigurationService.class);
			Properties prop = new Properties();
			configurationService.readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, "TESTS"));
			configurationService.readPropFile(prop, "ERROR");
			Assert.assertTrue(false);
		}
		catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void configurationServiceEnvironmentTest() throws IOException, SQLException
	{	
		Startup.configure();
		
		ConfigurationService configurationService = Startup.getInjector().getInstance(ConfigurationService.class);
		
		try {
			Properties prop = new Properties();
			configurationService.readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, "TESTS"));
			configurationService.getJavaEnvironment();
			configurationService.readPropFile(prop, "ERROR");
			Assert.assertTrue(false);
		}
		catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
}
