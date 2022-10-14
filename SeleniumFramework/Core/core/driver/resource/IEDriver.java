package core.driver.resource;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * IE driver implementation, override methods from BaseDriver
 */
public class IEDriver extends BaseDriver {

	/**
	 * a concept in Selenium WebDriver for manipulation various properties of IE
	 * driver
	 */
	protected InternetExplorerOptions options;

	/**
	 * Initializes IE driver properties with given properties
	 * 
	 * @param property - driver properties
	 */
	public IEDriver(DriverProperty property) {
		super(property);
		loadOptions();
	}

	/**
	 * Set properties for IE to customize driver sessions
	 */
	private void loadOptions() {
		options = new InternetExplorerOptions();
		if (driverProperty.getArguments() != null)
			options.addCommandSwitches(driverProperty.getArgumentsAsArray());
	}

	/**
	 * Create IE local driver with customized driver sessions
	 */
	@Override
	public void createLocalDriver() {
		if (StringUtils.isNotBlank(driverProperty.getDriverExecutable()))
			System.setProperty("webdriver.ie.driver", driverProperty.getDriverExecutable());
		else
			WebDriverManager.iedriver().arch32().setup();

		webDriver = new InternetExplorerDriver(options);
	}

	/**
	 * Create IE remote driver with remote url and customized driver sessions
	 */
	@Override
	public void createRemoteDriver() {
		webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
	}

}
