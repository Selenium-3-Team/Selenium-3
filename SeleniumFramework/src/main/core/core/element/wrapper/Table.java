package core.element.wrapper;

import org.openqa.selenium.By;

import core.element.base.Element;
import core.element.setting.FindBy;

public class Table extends Element {

	/**
	 * Extends Element class
	 */

	/**
	 * Initialize table control start with By (id, css, xpath,...)
	 * 
	 * @param locator - type find By of element
	 */
	public Table(By locator) {
		super(locator);
	}

	/**
	 * Initialize table control start with given string of locator
	 * 
	 * @param locator - string of element
	 */
	public Table(String locator) {
		super(locator);
	}

	/**
	 * Initializes table with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Table(Element parentElement, String locator) {
		super(parentElement, locator);
	}

	/**
	 * Initializes table with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Table(String locator, Object... arguments) {
		super(locator, arguments);
	}

	/**
	 * Initializes table via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Table(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}

	/**
	 * Initializes table with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Table(FindBy by, String value) {
		super(by, value);
	}

	/**
	 * Initializes table via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Table(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}

	/**
	 * Initializes table element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Table(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}

	/**
	 * Initializes dynamic table via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Table(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}

	/**
	 * Generate dynamic table
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public void generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
	}

}
