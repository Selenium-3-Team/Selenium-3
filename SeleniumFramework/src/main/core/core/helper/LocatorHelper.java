package core.helper;

import org.javatuples.Pair;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import core.common.Constant;
import core.driver.manager.DriverManager;
import core.element.setting.FindBy;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class LocatorHelper {

	private static Logger logger = Constant.createLogger(LocatorHelper.class.getName());

	private JsonObject jsonObject = new JsonObject();
	private final String fileExtension = ".json";

	/**
	 * Initialize Locator helper to get locator data file
	 * 
	 * @param locatorFolderPath - location of locators file
	 * @param clazz             - class object
	 */
	@SuppressWarnings("rawtypes")
	public LocatorHelper(String locatorFolderPath, Class clazz) {
		loadLocators(Paths.get(locatorFolderPath, clazz.getSimpleName() + fileExtension).normalize().toString());
	}

	/**
	 * Read locator json object from file path
	 * 
	 * @param filePath - location of locators file
	 */
	private void loadLocators(String filePath) {
		try {
			jsonObject = JsonHelper.getJsonObject(filePath);
		} catch (Exception e) {
			logger.severe(
					String.format("Exception! - Error when load locators from path %s", filePath, e.getMessage()));
		}
	}

	/**
	 * Identify key of locator json file by platform.driver
	 * 
	 * @param platform - browser platform
	 * @param driver   - driver type
	 * @return String key
	 */
	private String getKey(String platform, String driver) {
		return String.format("%s.%s", platform, driver);
	}

	/**
	 * Get value based on json element object key
	 * 
	 * @param elementName - json element object key
	 * @return - locator value of element
	 */
	public String getLocator(String elementName) {
		String currentPlatform;
		String currentDriver;
		try {
			currentPlatform = DriverManager.getDriverProperty().getPlatform().toString().toLowerCase();
			currentDriver = DriverManager.getDriverProperty().getDriverType().toString().toLowerCase();
			
			// Get locator from platform-driver key
			JsonElement content = jsonObject.get(getKey(currentPlatform, currentDriver));
			if (content != null) {
				return content.getAsJsonObject().get(elementName).getAsString();
			} else {
				throw new Exception(String.format("'%s' key was not found for '%s'", elementName,
						getKey(currentPlatform, currentDriver)));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	/**
	 * Separate a string to get find type locator
	 * 
	 * @param locator - a string locator of element
	 * @return type of locator
	 */
	public static String getLocatorType(String locator) {
		return locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
	}

	/**
	 * Separate a string to get value of locator
	 * 
	 * @param locator - a string locator of element
	 * @return value of locator
	 */
	public static String getLocatorValue(String locator) {
		return locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
	}

	/**
	 * Identify type and value of a string locator of an element, locator without
	 * type is assigned to xpath
	 * 
	 * @param locator - a string locator of element
	 * @return a pair of type and value of locator
	 */
	public static Pair<FindBy, String> getPairLocator(String locator) {
		FindBy locatorType = FindBy.getByLocator(getLocatorType(locator));
		String locatorValue = "";

		if (locatorType == FindBy.XPATH && !locator.contains(FindBy.XPATH.getValue()))
			locatorValue = getLocatorValue(String.format("%s=%s", FindBy.XPATH.getValue(), locator));
		else {
			locatorValue = getLocatorValue(locator);
		}
		return new Pair<FindBy, String>(locatorType, locatorValue);
	}
}
