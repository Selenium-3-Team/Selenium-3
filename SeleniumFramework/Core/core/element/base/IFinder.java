package core.element.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.element.setting.FindBy;

public interface IFinder extends ILocator {

	/**
	 * Create identity element's finder methods for class Element override
	 */

	/**
	 * Get web element from web page
	 */
	WebElement getElement();

	/**
	 * Get web element child from a element in web page
	 * 
	 * @param locator - Type of locator
	 */
	WebElement getChildElement(By locator);

	/**
	 * Get web element child from a element in web page
	 * 
	 * @param by    - locator type of element
	 * @param value - a string contains locator value of element
	 */
	WebElement getChildElement(FindBy by, String value);

	/**
	 * Get web element child from a element in web page
	 * 
	 * @param locator - a value of string
	 */
	WebElement getChildElement(String locator);

	/**
	 * Get list of web element from web page
	 */
	List<WebElement> getElements();

	/**
	 * Get list of web element child from a element in web page
	 * 
	 * @param locator - Type of locator
	 */
	List<WebElement> getChildElements(By locator);

	/**
	 * Get list of web element child from a element in web page
	 * 
	 * @param locator - a value of string
	 */
	List<WebElement> getChildElements(String locator);

	/**
	 * Get list of web element child from a element in web page. We use FindBy type
	 * and locator string for this. Where locator FindBy start it with type "id",
	 * "css", "xpath",... and value locator string of element
	 * 
	 * @param by    - locator type of element
	 * @param value - a string contains locator value of element
	 * 
	 * @return a list of elements
	 */
	List<WebElement> getChildElements(FindBy by, String value);
}
