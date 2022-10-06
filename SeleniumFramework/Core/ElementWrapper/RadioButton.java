package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class RadioButton extends Element{
	
	/**
	 * Extends Element class
	 */
	
	/**
	 * Initialize radio button control start with By (id, css, xpath,...)
	 * @param locator - type find By of element
	 */
	public RadioButton(By locator) {
		super(locator);
	}
	
	/**
	 * Initialize radio button control start with given string of locator
	 * @param locator - string of element
	 */
	public RadioButton(String locator) {
		super(locator);
	}
	
	/**
	 * Initializes radio button with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public RadioButton(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	/**
	 * Initializes radio button with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public RadioButton(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	/**
	 * Initializes radio button via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public RadioButton(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	/**
	 * Initializes radio button with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public RadioButton(FindBy by, String value) {
		super(by, value);
	}
	
	/**
	 * Initializes radio button via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public RadioButton(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	/**
	 * Initializes radio button element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public RadioButton(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	/**
	 * Initializes dynamic radio button via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public RadioButton(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	/**
	 * Generate dynamic radio button
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new radio button
	 */
	public RadioButton generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	/**
	 * Select on radio button
	 */
	public void select() {
		super.select();
	}
	
}
