package core.driver.manager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import core.common.Constant;
import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;
import core.helper.BrowserSettingHelper;

public class DriverManager {

	private static final Logger logger = Constant.createLogger(DriverManager.class.getName());
	private static ThreadLocal<ConcurrentHashMap<String, BaseDriver>> driverThreadMap = new ThreadLocal<ConcurrentHashMap<String, BaseDriver>>();
	private static ThreadLocal<List<String>> keys = new ThreadLocal<List<String>>();
	private static ThreadLocal<String> defaultKey = new ThreadLocal<String>();
	private static ThreadLocal<String> currentKey = new ThreadLocal<String>();
	private static ThreadLocal<DriverProperty> driverPropertyMap = new ThreadLocal<DriverProperty>();

	private static BaseDriver getBaseDriver() {
		if (!driverThreadMap.get().containsKey(currentKey.get())) {
			logger.severe(String.format("Driver with key '%s' is not found", currentKey.get()));
			return null;
		}
		return driverThreadMap.get().get(currentKey.get());
	}

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

	protected static ConcurrentHashMap<String, BaseDriver> getdriverThreadMap() {
		return driverThreadMap.get();
	}

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

	public static String getCurrentDriverKey() {
		return currentKey.get();
	}

	public static String getDefaultDriverKey() {
		return defaultKey.get();
	}

	public static WebDriver getDriver() {
		return getBaseDriver().getWebDriver();
	}

	public static DriverProperty getDriverProperty() {
		return getBaseDriver().getDriverProperty();
	}

	public static DriverProperty getDriverProperty(String key) {
		return driverThreadMap.get().get(key).getDriverProperty();
	}

	public static void loadDriverProperty(String propertyFile, String platform, String sectionName) throws Exception {
		DriverProperty driverProperty = BrowserSettingHelper.getDriverProperty(propertyFile, sectionName);
		driverProperty.setPlatform(platform);
		driverPropertyMap.set(driverProperty);
	}

	public static void initDriver() {
		if (driverThreadMap.get() == null) {
			driverThreadMap.set(new ConcurrentHashMap<String, BaseDriver>());
			keys.set(new ArrayList<String>());
		}

		String key = generateDriverKey(driverPropertyMap.get().getDriverType().toString());
		createDriver(key, driverPropertyMap.get());
		defaultKey.set(key);
	}

	public static void createDriver() {
		String key = generateDriverKey(driverPropertyMap.get().getDriverType().toString());
		createDriver(key, driverPropertyMap.get());
	}

	public static void createDriver(String key) {
		createDriver(key, driverPropertyMap.get());
	}

	public static void createDriver(DriverProperty property) {
		String key = generateDriverKey(property.getDriverType().toString());
		createDriver(key, property);
	}

	public static void createDriver(String key, DriverProperty property) {
		BaseDriver baseDriver = DriverFactory.newInstance(property);
		baseDriver.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(property.getPageTimeout()));
		baseDriver.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(property.getElementTimeOut()));
		driverThreadMap.get().put(key, baseDriver);
		currentKey.set(key);
		keys.get().add(key);
	}

	public static void switchToDriver(String key) {
		currentKey.set(key);
	}

	public static void switchToDefaultDriver() {
		currentKey.set(defaultKey.get());
	}

}
