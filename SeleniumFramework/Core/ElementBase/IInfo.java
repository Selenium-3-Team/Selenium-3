package ElementBase;

import java.util.List;

import org.openqa.selenium.Dimension;

public interface IInfo {

	/**
	 * Create identity element's info methods for class Element override
	 */
	
	/**
	 * Check whether this element displayed or not
	 * 
	 * @return true or false
	 */
	boolean isDisplayed();
	
	/**
	 * Check whether this element displayed or not with wait time out
	 * @param timeOutInSeconds - seconds to wait until element become visible or undiscovered
	 * 
	 * @return true or false
	 */
	boolean isDisplayed(int timeOutInSeconds);
	
	/**
	 * Check whether this element enabled or not
	 * 
	 * @return true or false
	 */
	boolean isEnabled();
	
	/**
	 * Check whether this element enabled or not with wait time out
	 * @param timeOutInSeconds - seconds to wait until element become visible or undiscovered
	 * 
	 * @return true or false
	 */
	boolean isEnabled(int timeOutInSeconds);
	
	/**
	 * Check whether this element selected or not
	 * 
	 * @return true or false
	 */
	boolean isSelected();
	
	/**
	 * Check whether this element selected or not with wait time out
	 * @param timeOutInSeconds - seconds to wait until element become visible or undiscovered
	 * 
	 * @return true or false
	 */
	boolean isSelected(int timeOutInSeconds);
	
	/**
     * Get the value of a given CSS property
     * @param propertyName - css name
     * 
     * @return css value
     */
	String getCssValue(String propertyName);
	
	/**
	 * Get the value of a the given attribute of the element. Will return the current value, even if
	 * @param attributeName
	 * 
	 * @return attribute value
	 */
	String getAttribute(String attributeName);
	
	/**
     * Get the visible (i.e. not hidden by CSS) innerText of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The innerText of this element
     */
	String getText();
	
	/**
	 * Get texts from list of elements
	 * 
	 * @return list of texts
	 */
	List<String> getAllTexts();
	
	/**
     * Get value of the value attribute (i.e. not hidden by CSS) of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The value of this element
     */
	String getValue();
	
	/**
     * Get the tag name of this element
     *
     * @return The tag name of this element
     */
	String getTagName();
	
	/**
     * Get class name of the class attribute (i.e. not hidden by CSS) of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The class name of this element
     */
	String getClassName();
	
	/**
     * Get number of elements
     *
     * @return The number of the element on the page
     */
	int getNumber();
	
	/**
     * Get width and height of element
     * 
     * @return The size of the element on the page
     */
	Dimension getSize();
	
}
