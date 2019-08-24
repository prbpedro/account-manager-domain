package tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;
import com.github.prbpedro.accountmanager.domain.util.Startup;

public class DatabaseServiceTest {

	@BeforeClass
	public static void configure() throws SQLException
	{
		Startup.configure();
	}
	
	@Test
	public void test() throws SQLException
	{	
		Assert.assertTrue(true);
		
		String s = Startup.getContainer().select(IDatabaseService.class).get().getDatabaseData();
		Assert.assertNotNull(s);
	}
}
