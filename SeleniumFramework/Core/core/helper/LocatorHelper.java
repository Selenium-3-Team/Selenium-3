package core.helper;

import org.javatuples.Pair;

import core.element.setting.FindBy;

public class LocatorHelper {

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
