package core.driver.base;

import org.openqa.selenium.WebDriver;

import core.driver.setting.DriverProperty;

/**
 * Create 'abstract' type specified basedriver 'BaseDriver' containing abstract
 * methods for driver
 */
public abstract class BaseDriver {

	/**
	 * a remote control, an interface that permits to execute tests over browsers
	 */
	protected WebDriver webDriver;

	/**
	 * Driver's properties
	 */
	protected DriverProperty driverProperty;

	/**
	 * Get web driver control
	 * 
	 * @return web driver
	 */
	public WebDriver getWebDriver() {
		return this.webDriver;
	}

	/**
	 * Get driver's properties setting
	 * 
	 * @return driver's properties (ex: name, value,...)
	 */
	public DriverProperty getDriverProperty() {
		return this.driverProperty;
	}

	/**
	 * Initializes driver with given driver properties
	 * 
	 * @param property - driver's properties setting
	 */
	public BaseDriver(DriverProperty property) {
		this.driverProperty = property;
	}

	/**
	 * Initializes driver to run a local web driver
	 */
	public abstract void createLocalDriver();

	/**
	 * Initializes driver to run a remote web driver
	 */
	public abstract void createRemoteDriver();
}
