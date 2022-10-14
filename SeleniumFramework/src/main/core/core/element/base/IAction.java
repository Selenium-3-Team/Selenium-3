package core.element.base;

public interface IAction {

	/**
	 * Create identity element's action methods for class Element override
	 */

	/**
	 * Performs a click on the element
	 */
	void click();

	/**
	 * Click on the Element by JS
	 */
	void clickByJS();

	/**
	 * Performs a double-click on the Element
	 */
	void doubleClick();

	/**
	 * Use this method to simulate typing into an element
	 * 
	 * @param keysToSend - CharSequence to send
	 */
	void sendKeys(CharSequence... keysToEnter);

	/**
	 * Use this method to simulate clear text value for an element
	 */
	void clear();

	/**
	 * Use this method for an element to submit
	 */
	void submit();

	/**
	 * Performs focus to an element
	 */
	void focus();

	/**
	 * Performs a mover at the current mouse location
	 */
	void hover();

	/**
	 * Mouse Over on the the given element
	 */
	void moveToElement();

	/**
	 * Scroll to an element
	 */
	void scrollIntoView();

	/**
	 * Select an Element
	 */
	void select();

	/**
	 * Check on an element
	 */
	void check();

	/**
	 * Uncheck on an element
	 */
	void uncheck();

}
