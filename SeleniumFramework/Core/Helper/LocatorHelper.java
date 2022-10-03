package Helper;

import org.javatuples.Pair;

import ElementSetting.FindBy;

public class LocatorHelper {
	
	public static String getLocatorType(String locator) {
		return locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
	}
	
	public static String getLocatorValue(String locator) {
		return locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
	}

	public static Pair<FindBy, String> getPairLocator(String locator) {
		FindBy locatorType = FindBy.getByLocator(getLocatorType(locator));
		String locatorValue = getLocatorValue(locator);
		return new Pair<FindBy, String>(locatorType, locatorValue);
	}
}
