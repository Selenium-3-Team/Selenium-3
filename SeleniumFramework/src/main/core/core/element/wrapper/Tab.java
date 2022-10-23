package core.element.wrapper;

import org.openqa.selenium.By;

import core.element.base.Element;
import core.element.setting.FindBy;

public class Tab extends Element{

	/**
	 * Extends Element class
	 */

	/**
	 * Initialize tab control start with By (id, css, xpath,...)
	 * 
	 * @param locator - type find By of element
	 */
	public Tab(By locator) {
		super(locator);
	}

	/**
	 * Initialize tab control start with given string of locator
	 * 
	 * @param locator - string of element
	 */
	public Tab(String locator) {
		super(locator);
	}

	/**
	 * Initializes tab with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Tab(Element parentElement, String locator) {
		super(parentElement, locator);
	}

	/**
	 * Initializes tab with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Tab(String locator, Object... arguments) {
		super(locator, arguments);
	}

	/**
	 * Initializes tab via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Tab(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}

	/**
	 * Initializes tab with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Tab(FindBy by, String value) {
		super(by, value);
	}

	/**
	 * Initializes tab via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Tab(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}

	/**
	 * Initializes tab element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Tab(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}

	/**
	 * Initializes dynamic tab via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Tab(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}

	/**
	 * Generate dynamic tab
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public void generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
	}

	/**
	 * Click on tab
	 */
	public void click() {
		super.click();
	}

	/**
	 * Click by JS on tab
	 */
	public void clickByJS() {
		super.clickByJS();
	}
	
}
