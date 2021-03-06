package tests.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.prbpedro.accountmanager.domain.services.ConfigurationService;
import com.github.prbpedro.accountmanager.domain.util.Constants;

public class ConfigurationServiceMock3 extends ConfigurationService {

	public ConfigurationServiceMock3() throws IOException {
		super();
	}

	@Override
	public void readPropFile(Properties prop, String propFileName) throws IOException {
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			prop.load(inputStream);
			if(prop.containsKey(Constants.CONFIG_resetDatabase)) {
				prop.setProperty(Constants.CONFIG_resetDatabase, "false");
			}
			if(prop.containsKey(Constants.CONFIG_executeDefaultInserts)) {
				prop.setProperty(Constants.CONFIG_executeDefaultInserts, "false");
			}	
		}
	}

}
