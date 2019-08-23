package tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.services.ConfigurationService;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.github.prbpedro.accountmanager.domain.util.Startup;

import tests.util.EnvironmentUtil;

public class ConfigurationServiceTests {

	@Test
	public void configurationServiceTest() throws IOException, SQLException
	{	
		EnvironmentUtil.cleanEnvironment();
		Startup.configure();
		
		ConfigurationService configurationService = Startup.getContainer().select(ConfigurationService.class).get();
		
		Properties prop = new Properties();
		configurationService.readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, "TESTS"));
		try {
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
		EnvironmentUtil.setNewEnvironment(Constants.ENV_JAVA_ENVIRONMENT, "TESTS");
		Startup.configure();
		
		ConfigurationService configurationService = Startup.getContainer().select(ConfigurationService.class).get();
		
		Properties prop = new Properties();
		configurationService.readPropFile(prop, String.format(Constants.FILE_PROP_NAME_PATTERN, "TESTS"));
		try {
			configurationService.readPropFile(prop, "ERROR");
			Assert.assertTrue(false);
		}
		catch (Exception e) {
			Assert.assertTrue(true);
		}
		EnvironmentUtil.cleanEnvironment();
	}
}
