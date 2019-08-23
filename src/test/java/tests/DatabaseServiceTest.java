package tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.util.Startup;

public class DatabaseServiceTest {

	@BeforeClass
	public static void configure() throws SQLException
	{
		Startup.configure();
	}
	
	@Test
	public void test()
	{	
		Assert.assertTrue(true);
	}
}
