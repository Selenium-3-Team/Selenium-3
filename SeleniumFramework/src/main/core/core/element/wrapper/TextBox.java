package core.element.wrapper;

import org.openqa.selenium.By;

import core.element.base.Element;
import core.element.setting.FindBy;

public class TextBox extends Element {

	/**
	 * Extends Element class
	 */

	/**
	 * Initialize text box control start with By (id, css, xpath,...)
	 * 
	 * @param locator - type find By of element
	 */
	public TextBox(By locator) {
		super(locator);
	}

	/**
	 * Initialize text box control start with given string of locator
	 * 
	 * @param locator - string of element
	 */
	public TextBox(String locator) {
		super(locator);
	}

	/**
	 * Initializes text box with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public TextBox(Element parentElement, String locator) {
		super(parentElement, locator);
	}

	/**
	 * Initializes text box with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public TextBox(String locator, Object... arguments) {
		super(locator, arguments);
	}

	/**
	 * Initializes text box via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public TextBox(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}

	/**
	 * Initializes text box with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public TextBox(FindBy by, String value) {
		super(by, value);
	}

	/**
	 * Initializes text box via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public TextBox(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}

	/**
	 * Initializes text box element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public TextBox(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}

	/**
	 * Initializes dynamic text box via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public TextBox(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}

	/**
	 * Generate dynamic text box
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public void generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
	}

	/**
	 * Sendkeys into text box
	 */
	public void sendKeys(CharSequence... keysToEnter) {
		super.sendKeys(keysToEnter);
	}

	/**
	 * Clear text of text box
	 */
	public void clear() {
		super.clear();
	}
}
