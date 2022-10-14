package core.driver.manager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import core.common.Constant;
import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import core.helper.BrowserSettingHelper;

/**
 * Driver manager class to manage the WebDriver instanct alongside browsers. We
 * use ThreadLocal to make the WebDriver session exclusive to each thread when
 * we try to achive parallel execution of our tests. Every thread created to
 * parallelize tests tries to overwrite the WebDriver reference.
 */
public class DriverManager {

	/**
	 * Contains log of the class
	 */
	private static final Logger logger = Constant.createLogger(DriverManager.class.getName());

	/**
	 * A thread map contains webdriver each session when executing tests parallel
	 */
	private static ThreadLocal<Map<String, BaseDriver>> driverThreadMap = new ThreadLocal<Map<String, BaseDriver>>();

	/**
	 * List of keys for driver sessions when executing tests parallel
	 */
	private static ThreadLocal<List<String>> keys = new ThreadLocal<List<String>>();

	/**
	 * Default key for driver session
	 */
	private static ThreadLocal<String> defaultKey = new ThreadLocal<String>();

	/**
	 * Current key for driver session
	 */
	private static ThreadLocal<String> currentKey = new ThreadLocal<String>();

	/**
	 * Driver property map for each driver session
	 */
	private static ThreadLocal<DriverProperty> driverPropertyMap = new ThreadLocal<DriverProperty>();

	/**
	 * Get base driver where containing abstract methods for driver by checking
	 * current key existed
	 * 
	 * @return driver session of the current key
	 */
	private static BaseDriver getBaseDriver() {
		if (!driverThreadMap.get().containsKey(currentKey.get())) {
			logger.severe(String.format("Driver with key '%s' is not found", currentKey.get()));
			return null;
		}
		return driverThreadMap.get().get(currentKey.get());
	}

	/**
	 * Generate driver key which for identify driver session When executing test
	 * parallel, the number of key is increased based on number of driver session
	 * 
	 * @param prefix - a string of letter added to the beginning
	 * @return a string of key
	 */
	private static String generateDriverKey(String prefix) {
		String key;
		int number = 1;
		while (true) {
			key = prefix + "-" + number;
			if (!driverThreadMap.get().containsKey(key))
				return key;
			number++;
		}
	}

	/**
	 * Get driver map with each key and value
	 * 
	 * @return a map of driver
	 */
	protected static Map<String, BaseDriver> getdriverThreadMap() {
		return driverThreadMap.get();
	}

	/**
	 * Remove driver by remove current key
	 */
	protected static void removeDriver() {
		driverThreadMap.get().remove(currentKey.get());
		keys.get().remove(currentKey.get());
		int size = keys.get().size();
		if (size > 0) {
			currentKey.set(keys.get().get(size - 1));
			if (!driverThreadMap.get().containsKey(defaultKey.get())) {
				defaultKey.set(keys.get().get(0));
			}
		}
	}

	/**
	 * Get current driver session
	 * 
	 * @return a string of current key
	 */
	public static String getCurrentDriverKey() {
		return currentKey.get();
	}

	/**
	 * Get default driver session
	 * 
	 * @return a string of default key
	 */
	public static String getDefaultDriverKey() {
		return defaultKey.get();
	}

	/**
	 * Get driver session
	 * 
	 * @return web driver
	 */
	public static WebDriver getDriver() {
		return getBaseDriver().getWebDriver();
	}

	/**
	 * Get driver session properties
	 * 
	 * @return driver properties
	 */
	public static DriverProperty getDriverProperty() {
		return getBaseDriver().getDriverProperty();
	}

	/**
	 * Get driver session properties by key
	 * 
	 * @param key - a string of key for driver session
	 * @return driver properties
	 */
	public static DriverProperty getDriverProperty(String key) {
		return driverThreadMap.get().get(key).getDriverProperty();
	}

	/**
	 * Load of set identified of driver attributes
	 * 
	 * @param propertyFile - property file name
	 * @param platform     - a string of driver platform
	 * @param sectionName  - a string of section
	 * @throws Exception - if platform is not supported
	 */
	public static void loadDriverProperty(String propertyFile, String platform, String sectionName) throws Exception {
		DriverProperty driverProperty = BrowserSettingHelper.getDriverProperty(propertyFile, sectionName);
		driverProperty.setPlatform(platform);
		driverPropertyMap.set(driverProperty);
	}

	/**
	 * Initialize driver by new key is set
	 */
	public static void initDriver() {
		if (driverThreadMap.get() == null) {
			driverThreadMap.set(new HashMap<String, BaseDriver>());
			keys.set(new ArrayList<String>());
		}

		String key = generateDriverKey(driverPropertyMap.get().getDriverType().toString());
		createDriver(key, driverPropertyMap.get());
		defaultKey.set(key);
	}

	/**
	 * Create driver with generate key by driver type prefix from driver property
	 * map
	 */
	public static void createDriver() {
		String key = generateDriverKey(driverPropertyMap.get().getDriverType().toString());
		createDriver(key, driverPropertyMap.get());
	}

	/**
	 * Create driver with a key
	 * 
	 * @param key - a string of key
	 */
	public static void createDriver(String key) {
		createDriver(key, driverPropertyMap.get());
	}

	/**
	 * Create driver with generate key by driver type prefix from a driver property
	 * 
	 * @param property - a class of driver property
	 */
	public static void createDriver(DriverProperty property) {
		String key = generateDriverKey(property.getDriverType().toString());
		createDriver(key, property);
	}

	/**
	 * Create driver This will create a new instance (driver type, mode) of driver
	 * via driver property to add into thread for parallel execution
	 * 
	 * @param key      - a string of key
	 * @param property - a class of driver property
	 */
	public static void createDriver(String key, DriverProperty property) {
		BaseDriver baseDriver = DriverFactory.newInstance(property);
		baseDriver.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(property.getPageTimeout()));
		baseDriver.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(property.getElementTimeOut()));
		driverThreadMap.get().put(key, baseDriver);
		currentKey.set(key);
		keys.get().add(key);
	}

	/**
	 * Switch to another driver
	 * 
	 * @param key - a string of driver key
	 */
	public static void switchToDriver(String key) {
		currentKey.set(key);
	}

	/**
	 * Switch to default driver
	 */
	public static void switchToDefaultDriver() {
		currentKey.set(defaultKey.get());
	}

}
