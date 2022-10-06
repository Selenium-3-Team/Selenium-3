package ElementBase;

import org.openqa.selenium.By;

import ElementSetting.FindBy;
import Helper.LocatorHelper;
import Helper.Pair;

public interface ILocator {

	/**
	 * Create identity get element's locator methods
	 */

	/**
	 * Get element's By locator
	 * 
	 * @return By locator of the element
	 */
	By getLocator();

	/**
	 * Get parent element's By locator
	 * 
	 * @return By locator of the parent element
	 */
	By getParentLocator();

	/**
	 * Get Element from web page
	 * 
	 * @param by    - Locator type of element
	 * @param value - Locator value of element
	 * 
	 * @return - Find element By
	 */
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
	 * Get by locator with given pair of FindBy and Locator
	 * 
	 * @param locator - a new pair of element <FindBy, Locator>
	 * @return use getByLocator to identify type of locator and find element by
	 *         following it's type
	 */
	public default By getByLocator(Pair<FindBy, String> locator) {
		return getByLocator(locator.getKey(), locator.getValue());
	}

	/**
	 * Get by locator with given string
	 * 
	 * @param locator - a string
	 * @return use Locator Helper to separate, add to new pair of findBy and value
	 *         and pass this pair through getByLocator to identify type of locator
	 *         and return element following it's type
	 */
	public default By getByLocator(String locator) {
		Pair<FindBy, String> pairLocator = LocatorHelper.getPairLocator(locator);
		return getByLocator(pairLocator.getKey(), pairLocator.getValue());
	}
}
