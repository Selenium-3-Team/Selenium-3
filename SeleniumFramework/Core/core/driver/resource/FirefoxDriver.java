package core.driver.resource;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Firefox driver implementation, override methods from BaseDriver 
 */
public class FirefoxDriver extends BaseDriver{

	/**
	 * a concept in Selenium WebDriver for manipulation various properties of Firefox driver
	 */
	protected FirefoxOptions options;
	
	/**
	 * Initializes Firefox driver properties with given properties
	 * @param property - driver properties
	 */
	public FirefoxDriver(DriverProperty property) {
		super(property);
		loadOptions();
	}

	/**
	 * Set properties for Firefox to customize driver sessions 
	 */
	private void loadOptions()
	{
		options = new FirefoxOptions();
		if (driverProperty.getArguments() != null) {
			options.addArguments(driverProperty.getArguments());
		}
		if (driverProperty.getUserProfilePreference() != null)
		{
			FirefoxProfile profile = new FirefoxProfile();
			driverProperty.getUserProfilePreference().forEach((key, value) -> profile.setPreference(key, value.toString()));
			options.setProfile(profile);
		}
		options.merge(driverProperty.getCapabilities());
	}
	
	/**
	 * Create Firefox local driver with customized driver sessions
	 */
	@Override
	public void createLocalDriver() {
		if (StringUtils.isNotBlank(driverProperty.getDriverExecutable())) {
			System.setProperty("webdriver.gecko.driver", driverProperty.getDriverExecutable());
		}else {
			WebDriverManager.firefoxdriver().setup();
		}
		webDriver = new org.openqa.selenium.firefox.FirefoxDriver(options);
	}

	/**
	 * Create Firefox remote driver with remote url and customized driver sessions
	 */
	@Override
	public void createRemoteDriver() {
		webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
	}

}
