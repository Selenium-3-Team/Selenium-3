package core.element.wrapper;

import org.openqa.selenium.By;

import core.element.base.Element;
import core.element.setting.FindBy;

public class Link extends Element {

	/**
	 * Extends Element class
	 */

	/**
	 * Initialize link control start with By (id, css, xpath,...)
	 * 
	 * @param locator - type find By of element
	 */
	public Link(By locator) {
		super(locator);
	}

	/**
	 * Initialize link control start with given string of locator
	 * 
	 * @param locator - string of element
	 */
	public Link(String locator) {
		super(locator);
	}

	/**
	 * Initializes link with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Link(Element parentElement, String locator) {
		super(parentElement, locator);
	}

	/**
	 * Initializes link with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Link(String locator, Object... arguments) {
		super(locator, arguments);
	}

	/**
	 * Initializes link via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Link(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}

	/**
	 * Initializes link with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Link(FindBy by, String value) {
		super(by, value);
	}

	/**
	 * Initializes link via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Link(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}

	/**
	 * Initializes link element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Link(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}

	/**
	 * Initializes dynamic link via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Link(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}

	/**
	 * Generate dynamic link
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new link
	 */
	public Link generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}

	/**
	 * Click on link
	 */
	public void click() {
		super.click();
	}

	/**
	 * Click by JS on link
	 */
	public void clickByJS() {
		super.clickByJS();
	}

	/**
	 * Get href of link
	 */
	public void getReference() {
		super.getAttribute("href");
	}

}
