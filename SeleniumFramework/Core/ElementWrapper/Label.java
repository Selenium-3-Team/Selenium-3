package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Label extends Element{

	/**
	 * Extends Element class
	 */
	
	/**
	 * Initialize label control start with By (id, css, xpath,...)
	 * @param locator - type find By of element
	 */
	public Label(By locator) {
		super(locator);
	}
	
	/**
	 * Initialize label control start with given string of locator
	 * @param locator - string of element
	 */
	public Label(String locator) {
		super(locator);
	}
	
	/**
	 * Initializes label with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Label(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	/**
	 * Initializes label with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Label(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	/**
	 * Initializes label via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Label(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	/**
	 * Initializes label with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Label(FindBy by, String value) {
		super(by, value);
	}
	
	/**
	 * Initializes label via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Label(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	/**
	 * Initializes label element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Label(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	/**
	 * Initializes dynamic label via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Label(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	/**
	 * Generate dynamic label
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return new label
	 */
	public Label generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
}
