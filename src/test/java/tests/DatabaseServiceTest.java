package tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.services.DatabaseService;
import com.github.prbpedro.accountmanager.domain.util.Startup;

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
		String s = Startup.getInjector().getInstance(DatabaseService.class).getDatabaseData();
		Assert.assertNotNull(s);
	}
}
