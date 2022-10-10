package core.driver.resource;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Edge driver implementation, override methods from BaseDriver 
 */
public class EdgeDriver extends BaseDriver{

	/**
	 * a concept in Selenium WebDriver for manipulation various properties of Edge driver
	 */
	protected EdgeOptions options;
	
	/**
	 * Initializes Edge driver properties with given properties
	 * @param property - driver properties
	 */
	public EdgeDriver(DriverProperty property) {
		super(property);
		loadOptions(property);
	}
	
	/**
	 * Set properties for Edge to customize driver sessions 
	 */
	private void loadOptions(DriverProperty property) {
		options = new EdgeOptions();
		if (driverProperty.getArguments() != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("args", driverProperty.getArguments());
			options.setCapability("ms:edgeOptions", map);
		}
		if (driverProperty.getUserProfilePreference() != null) {
			options.setExperimentalOption("prefs", driverProperty.getUserProfilePreference());
		}
		options.merge(driverProperty.getCapabilities());
		options.setHeadless(property.getHeadless());
	}
	
	/**
	 * Create Edge local driver with customized driver sessions
	 */
	@Override
	public void createLocalDriver() {
		if (StringUtils.isNotBlank(driverProperty.getDriverExecutable())) {
			System.setProperty("webdriver.edge.driver", driverProperty.getDriverExecutable());
		}else {
			WebDriverManager.edgedriver().setup();
		}
		webDriver = new org.openqa.selenium.edge.EdgeDriver(options);
	}

	/**
	 * Create Edge remote driver with remote url and customized driver sessions
	 */
	@Override
	public void createRemoteDriver() {
		webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
	}

}
