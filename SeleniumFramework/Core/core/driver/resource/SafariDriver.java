package core.driver.resource;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Safari driver implementation, override methods from BaseDriver 
 */
public class SafariDriver extends BaseDriver{

	/**
	 * a concept in Selenium WebDriver for manipulation various properties of Safari driver
	 */
	protected SafariOptions options;
	
	/**
	 * Initializes Safari driver properties with given properties
	 * @param property - driver properties
	 */
	public SafariDriver(DriverProperty property) {
		super(property);
		loadOptions();
	}

	/**
	 * Set properties for Safari to customize driver sessions 
	 */
	private void loadOptions()
	{
		options = new SafariOptions();
		options.setUseTechnologyPreview(true);
		options.merge(driverProperty.getCapabilities());
	}
	
	/**
	 * Create Safari local driver with customized driver sessions
	 */
	@Override
	public void createLocalDriver() {
		if(StringUtils.isNotBlank(driverProperty.getDriverExecutable())) {
			System.setProperty("webdriver.safari.driver", driverProperty.getDriverExecutable());
		}else {
			WebDriverManager.safaridriver().setup();
		}
		webDriver = new org.openqa.selenium.safari.SafariDriver(options);
	}

	/**
	 * Create Safari remote driver with remote url and customized driver sessions
	 */
	@Override
	public void createRemoteDriver() {
		webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
	}

}
