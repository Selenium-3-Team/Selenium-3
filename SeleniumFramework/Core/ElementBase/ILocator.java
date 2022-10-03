package ElementBase;

import org.javatuples.Pair;
import org.openqa.selenium.By;

import ElementSetting.FindBy;
import Helper.LocatorHelper;


public interface ILocator {

	/**
	 * Element's locator type
	 */
	By getLocator();
	
	By getParentLocator();
	
	public default By getByLocator(FindBy by, String value) {
		switch (by) {
		case CSS_SELECTOR:
			return By.cssSelector(value);
		case ID:
			return By.id(value);
		case CLASS_NAME:
			return By.className(value);
		case LINK_TEXT:
			return By.linkText(value);
		case NAME:
			return By.name(value);
		case PARTIAL_LINK_TEXT:
			return By.partialLinkText(value);
		case TAG_NAME:
			return By.tagName(value);
		case XPATH:
			return By.xpath(value);
		default:
			return By.xpath(value);
		}
	}
	
	/**
	 * Get locator with given string
	 * @param locator - a string 
	 * @return use Locator Helper to separate and identify type of locator and return element following it's type
	 */
	public default By getByLocator(String locator) {
		Pair<FindBy, String> pairLocator = LocatorHelper.getPairLocator(locator);
		return getByLocator(pairLocator.getValue0(), pairLocator.getValue1());
	}
}
