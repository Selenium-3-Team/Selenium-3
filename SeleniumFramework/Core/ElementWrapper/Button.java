package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Button extends Element{

	/**
	 * Extends Element class
	 */
	
	/**
	 * Initialize button control start with By (id, css, xpath,...)
	 * @param locator - type find By of element
	 */
	public Button(By locator) {
		super(locator);
	}
	
	/**
	 * Initialize button control start with given string of locator
	 * @param locator - string of element
	 */
	public Button(String locator) {
		super(locator);
	}
	
	/**
	 * Initializes button with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Button(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	/**
	 * Initializes button with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Button(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	/**
	 * Initializes button via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Button(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	/**
	 * Initializes Button with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Button(FindBy by, String value) {
		super(by, value);
	}
	
	/**
	 * Initializes Button via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Button(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	/**
	 * Initializes Button element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Button(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	/**
	 * Initializes dynamic Button via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Button(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	/**
	 * Generate dynamic Button
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new Button
	 */
	public Button generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	/**
	 * Click on button
	 */
	public void click() {
		super.click();
	}
	
	/**
	 * Click by JS on button
	 */
	public void clickByJS() {
		super.clickByJS();
	}
	
	/**
	 * Double click on button
	 */
	public void doubleClick() {
		super.doubleClick();
	}
	
	/**
	 * Submit by button
	 */
	public void submit() {
		super.submit();
	}
	
}
