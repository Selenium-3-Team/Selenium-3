package core.element.wrapper;

import org.openqa.selenium.By;

import core.element.base.Element;
import core.element.setting.FindBy;

public class Image extends Element{

	/**
	 * Extends Element class
	 */
	
	/**
	 * Initialize Image control start with By (id, css, xpath,...)
	 * @param locator - type find By of element
	 */
	public Image(By locator) {
		super(locator);
	}
	
	/**
	 * Initialize Image control start with given string of locator
	 * @param locator - string of element
	 */
	public Image(String locator) {
		super(locator);
	}
	
	/**
	 * Initializes Image with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Image(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	/**
	 * Initializes Image with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Image(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	/**
	 * Initializes Image via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Image(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	/**
	 * Initializes Image with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Image(FindBy by, String value) {
		super(by, value);
	}
	
	/**
	 * Initializes Image via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Image(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	/**
	 * Initializes Image element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Image(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	/**
	 * Initializes dynamic Image via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Image(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	/**
	 * Generate dynamic Image
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new Image
	 */
	public Image generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	/**
	 * Click on Image
	 */
	public void click() {
		super.click();
	}
	
	/**
	 * Get source of image
	 */
	public void getSource() {
		super.getAttribute("src");
	}
	
	/**
	 * Get alt of image
	 */
	public void getAlt() {
		super.getAttribute("alt");
	}
	
}
