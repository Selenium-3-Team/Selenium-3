package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class CheckBox extends Element{

	/**
	 * Extends Element class
	 */
	
	/**
	 * Initialize CheckBox control start with By (id, css, xpath,...)
	 * @param locator - type find By of element
	 */
	public CheckBox(By locator) {
		super(locator);
	}
	
	/**
	 * Initialize CheckBox control start with given string of locator
	 * @param locator - string of element
	 */
	public CheckBox(String locator) {
		super(locator);
	}
	
	/**
	 * Initializes CheckBox with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public CheckBox(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	/**
	 * Initializes CheckBox with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public CheckBox(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	/**
	 * Initializes CheckBox via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public CheckBox(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	/**
	 * Initializes CheckBox with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public CheckBox(FindBy by, String value) {
		super(by, value);
	}
	
	/**
	 * Initializes CheckBox via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public CheckBox(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	/**
	 * Initializes CheckBox element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public CheckBox(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	/**
	 * Initializes dynamic CheckBox via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public CheckBox(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	/**
	 * Generate dynamic CheckBox
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new Button
	 */
	public CheckBox generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	/**
	 * Check on button
	 */
	public void check() {
		super.check();
	}
	
	/**
	 * Uncheck on button
	 */
	public void uncheck() {
		super.uncheck();
	}
}
