package core.element.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.google.common.base.Stopwatch;

import core.common.Constant;
import core.driver.manager.Driver;
import core.element.setting.FindBy;
import core.element.setting.Status;
import core.helper.LocatorHelper;
import core.utilities.Pair;

/**
 * Element control implementation
 */
public class Element implements IWaiter, IAction, IInfo {

	/**
	 * Contains log of the element used
	 */
	private static final Logger logger = Constant.createLogger(Element.class.getName());

	/**
	 * Locator of the element
	 */
	private By byLocator;

	/**
	 * Pair different types of locators
	 */
	private Pair<FindBy, String> pairLocator;

	/**
	 * Parent element instance
	 */
	private Element parentElement;

	// Constructors
	/**
	 * Initializes element with given locator type
	 * 
	 * @param locator - start it with locator type: id, xpath, css,...
	 */
	public Element(By locator) {
		this.byLocator = locator;
	}

	/**
	 * Initializes element with given string of locator
	 * 
	 * @param locator - start it with a string, takes given string to identify type
	 *                of locator and tries to initialize, locator without type is assigned to xpath
	 */
	public Element(String locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = LocatorHelper.getPairLocator(locator);
	}

	/**
	 * Initializes element with parent of current element and string
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator and tries to initialize
	 */
	public Element(Element parentElement, String locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = LocatorHelper.getPairLocator(locator);
		this.parentElement = parentElement;
	}

	/**
	 * Initializes element with given dynamic string of locator
	 * 
	 * @param locator   - start it with a string, takes given string to identify
	 *                  type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  string
	 */
	public Element(String locator, Object... arguments) {
		this.byLocator = getByLocator(String.format(locator, arguments));
		this.pairLocator = LocatorHelper.getPairLocator(locator);
	}

	/**
	 * Initializes dynamic element via parent element
	 * 
	 * @param parentElement - Parent Element instance
	 * @param locator       - start it with a string, takes given string to identify
	 *                      type of locator by separate value to pair<FindBy,
	 *                      String>
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic string
	 */
	public Element(Element parentElement, String locator, Object... arguments) {
		this.byLocator = getByLocator(String.format(locator, arguments));
		this.pairLocator = LocatorHelper.getPairLocator(locator);
		this.parentElement = parentElement;
	}

	/**
	 * Initializes element with a Pair type of FindBy and String
	 * @param locator - type of Pair<FindBy, String>, takes given locator to identify type of locator
	 */
	public Element(Pair<FindBy, String> locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = locator;
	}
	
	/**
	 * Initializes element via parent element with a Pair type of FindBy and String
	 * @param parentElement - Parent panel instance
	 * @param locator - type of Pair<FindBy, String>, takes given locator to identify type of locator
	 */
	public Element(Element parentElement, Pair<FindBy, String> locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = locator;
		this.parentElement = parentElement;
	}
	
	/**
	 * Initializes dynamic element with a Pair type of FindBy and String
	 * @param locator - type of Pair<FindBy, String>, takes given locator to identify type of locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic locator 
	 */
	public Element(Pair<FindBy, String> locator, Object... arguments) {
		this.byLocator = getByLocator(locator.getKey(), String.format(locator.getValue(), arguments));
		this.pairLocator = locator;
	}
	
	/**
	 * Initializes dynamic element via parent element with a Pair type of FindBy and String
	 * @param parentElement - Parent panel instance
	 * @param locator - type of Pair<FindBy, String>, takes given locator to identify type of locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic locator 
	 */
	public Element(Element parentElement, Pair<FindBy, String> locator, Object... arguments) {
		this.byLocator = getByLocator(locator.getKey(), String.format(locator.getValue(), arguments));
		this.pairLocator = locator;
		this.parentElement = parentElement;
	}
	
	/**
	 * Initializes element with given FindBy and value String
	 * 
	 * @param by    - Type of locator
	 * @param value - value of Locator
	 */
	public Element(FindBy by, String value) {
		this.byLocator = getByLocator(by, value);
		this.pairLocator = new Pair<FindBy, String>(by, value);
	}

	/**
	 * Initializes element via parent element with given FindBy and value String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 */
	public Element(Element parentElement, FindBy by, String value) {
		this.byLocator = getByLocator(by, value);
		this.pairLocator = new Pair<FindBy, String>(by, value);
		this.parentElement = parentElement;
	}

	/**
	 * Initializes dynamic element with given FindBy and value String
	 * 
	 * @param by        - Type of locator
	 * @param value     - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 */
	public Element(FindBy by, String value, Object... arguments) {
		this.byLocator = getByLocator(by, String.format(value, arguments));
		this.pairLocator = new Pair<FindBy, String>(by, value);
	}

	/**
	 * Initializes dynamic element via parent element with given FindBy and value
	 * String
	 * 
	 * @param parentElement - Parent Element instance
	 * @param by            - Type of locator
	 * @param value         - value of Locator
	 * @param arguments     - variable-length arguments of type Object, use for
	 *                      dynamic locator
	 */
	public Element(Element parentElement, FindBy by, String value, Object... arguments) {
		this.byLocator = getByLocator(by, String.format(value, arguments));
		this.pairLocator = new Pair<FindBy, String>(by, value);
		this.parentElement = parentElement;
	}

	/**
	 * Generate dynamic element
	 * 
	 * @param arguments - variable-length arguments of type Object, use for dynamic
	 *                  locator
	 * @return element
	 */
	public Element generateDynamic(Object... arguments) {
		if (this.pairLocator != null)
			this.byLocator = getByLocator(this.pairLocator.getKey(),
					String.format(this.pairLocator.getValue(), arguments));
		return this;
	}

	/*
	 * ========================= Finder
	 * ===========================================================================
	 */
	/**
	 * Override methods find element from IFinder interface
	 */
	
	/**
	 * Get web element from web page
	 * Locator without type is assigned to xpath
	 * 
	 *  @return element
	 */
	@Override
	public WebElement getElement() {
		if (parentElement != null)
			return parentElement.getChildElement(getLocator());
		return Driver.findElement(getLocator());
	}

	/**
	 * Get web element child from a element in web page. We use By locator for this. Where locator start it with type "id", "css", "xpath",...
	 * @param locator - Type of locator
	 * 
	 * @return element
	 */
	@Override
	public WebElement getChildElement(By locator) {
		return getElement().findElement(locator);
	}

	/**
	 * Get web element child from a element in web page. We use a Pair<FindBy, String> for this. Where locator FindBy start it with type "id", "css", "xpath",... and value locator string of element
	 * @param locator - Type Pair<FindBy, String> of element
	 * 
	 *  @return element
	 */
	@Override
	public WebElement getChildElement(Pair<FindBy, String> locator) {
		return getElement().findElement(getByLocator(locator));
	}
	
	/**
	 * Get web element child from a element in web page. We use FindBy type and locator string for this. Where locator FindBy start it with type "id", "css", "xpath",... and value locator string of element
	 * @param by - locator type of element
	 * @param value - a string contains locator value of element
	 * 
	 *  @return element
	 */
	@Override
	public WebElement getChildElement(FindBy by, String value) {
		return getElement().findElement(getByLocator(by, value));
	}

	/**
	 * Get web element child from a element in web page. We use locator for this. Where locator start it with type "id", "css", "xpath",...
	 * Locator without type is assigned to xpath
	 * @param locator - a value of string 
	 * 
	 * @return element
	 */
	@Override
	public WebElement getChildElement(String locator) {
		return getElement().findElement(getByLocator(locator));
	}

	/**
	 * Get list of web element from web page
	 * Locator without type is assigned to xpath
	 * 
	 *  @return a list of elements
	 */
	@Override
	public List<WebElement> getElements() {
		if (parentElement != null)
			return parentElement.getChildElements(getLocator());
		return Driver.findElements(getLocator());
	}

	/**
	 * Get list of web element child from a element in web page. We use By locator for this. Where locator start it with type "id", "css", "xpath",...
	 * @param locator - Type of locator
	 * 
	 * @return a list of elements
	 */
	@Override
	public List<WebElement> getChildElements(By locator) {
		return getElement().findElements(locator);
	}
	
	/**
	 * Get list of web element child from a element in web page. We use a Pair<FindBy, String> for this. Where locator FindBy start it with type "id", "css", "xpath",... and value locator string of element
	 * @param locator - Type Pair<FindBy, String> of element
	 * 
	 *  @return a list of elements
	 */
	@Override
	public List<WebElement> getChildElements(Pair<FindBy, String> locator) {
		return getElement().findElements(getByLocator(locator));
	}

	/**
	 * Get list of web element child from a element in web page. We use FindBy type and locator string for this. Where locator FindBy start it with type "id", "css", "xpath",... and value locator string of element
	 * @param by - locator type of element
	 * @param value - a string contains locator value of element
	 * 
	 *  @return a list of elements
	 */
	@Override
	public List<WebElement> getChildElements(FindBy by, String value) {
		return getElement().findElements(getByLocator(by, value));
	}

	/**
	 * Get list of web element child from a element in web page. We use locator for this. Where locator start it with type "id", "css", "xpath",...
	 * Locator without type is assigned to xpath
	 * @param locator - a value of string 
	 * 
	 * @return a list of elements
	 */
	@Override
	public List<WebElement> getChildElements(String locator) {
		return getElement().findElements(getByLocator(locator));
	}

	/*
	 * ========================= Locator
	 * ===========================================================================
	 */
	/**
	 * Override methods get element's locator from ILocator interface
	 */
	
	/**
	 * Get element's By locator
	 * 
	 * @return By locator of the element
	 */
	@Override
	public By getLocator() {
		return this.byLocator;
	}

	/**
	 * Get parent element's By locator
	 * 
	 * @return By locator of the parent element
	 */
	@Override
	public By getParentLocator() {
		if (parentElement != null)
			return parentElement.getLocator();
		return null;
	}

	/*
	 * ========================= Check Status
	 * ===========================================================================
	 */
	/**
	 * Override methods check element status from IFinder interface
	 */
	
	/**
	 * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     * 
     * @return Whether or not the element is displayed
	 */
	@Override
	public boolean isDisplayed() {
		return isDisplayed(Constant.TIMEOUT);
	}

	/**
	 * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     * 
     * @param timeOutInSeconds - seconds to wait until element become visible or undiscovered
     * @return Whether or not the element is displayed
	 */
	@Override
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			logger.info(String.format("Check if control %s is displayed", getLocator().toString()));
			waitForCondition(Status.DISPLAYED, timeOutInSeconds, true);
			return true;
		} catch (TimeoutException timeOutEx) {
			return false;
		} catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	/**
	 * Is the element currently enabled or not? This will generally return true for everything but
     * disabled input elements.
     * 
     * @return True if the element is enabled, false otherwise
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled(Constant.TIMEOUT);
	}

	/**
	 * Is the element currently enabled or not? This will generally return false for everything but
     * disabled input elements.
     * @param timeOutInSeconds - seconds to wait until element become enabled or undiscovered
     * 
     * @return True if the element is enabled, false otherwise
	 */
	@Override
	public boolean isEnabled(int timeOutInSeconds) {
		boolean isEnabled = false;
		if (timeOutInSeconds <= 0) {
			logger.severe("The time out is invalid. It must greater than 0");
			return isEnabled;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			logger.info(String.format("Check if the control %s is enabled", getLocator().toString()));
			waitForCondition(Status.DISPLAYED, timeOutInSeconds, true);
			isEnabled = getElement().isEnabled();
		} catch (StaleElementReferenceException e) {
			if (sw.elapsed(TimeUnit.SECONDS) < (long) timeOutInSeconds) {
				logger.warning(String.format("Try to check if the control the control %s is enabled again",
						getLocator().toString()));
				return isEnabled(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS));
			}
		} catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
		return isEnabled;
	}

	/**
	 * Is the element currently selected or not? This will generally return true for everything but
     * disabled input elements
     * 
     * @return True if the element is selected, false otherwise
	 */
	@Override
	public boolean isSelected() {
		return isSelected(Constant.TIMEOUT);
	}

	/**
	 * Is the element currently selected or not? This will generally return true for everything but
     * disabled input elements
     * @param timeOutInSeconds - seconds to wait until element become selected or undiscovered
     * 
     * @return True if the element is selected, false otherwise
	 */
	@Override
	public boolean isSelected(int timeOutInSeconds) {
		logger.info(
				String.format("Check Selected status of %s in %s seconds", getLocator().toString(), timeOutInSeconds));
		try {
			waitForCondition(Status.SELECTED, timeOutInSeconds, true);
			return true;
		} catch (TimeoutException timeOutEx) {
			return false;
		} catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	/**
     * Get the value of a given CSS property. This is probably not going to return what you expect it
     * to unless you've already had a look at the element using something like firebug. Seriously,
     * even then you'll be lucky for this to work cross-browser. Colour values should be returned as
     * hex strings, so, for example if the "background-color" property is set as "green" in the HTML
     * source, the returned value will be "#008000"
     *
     * @param propertyName - css name
     * @return The current, computed value of the property
     */
	@Override
	public String getCssValue(String propertyName) {
		logger.info(String.format("Get Css value '%s' of %s", propertyName, getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getCssValue(propertyName);
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get CSS value '%s' from control %s again", propertyName,
						getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
     * Get the value of a the given attribute of the element. Will return the current value, even if
     * this has been modified after the page has been loaded
     *
     * @param attributeName - The name of the attribute
     * @return The attribute's current value or null if the value is not set
     */
	@Override
	public String getAttribute(String attributeName) {
		logger.info(String.format("Get Attribute value '%s' of %s", attributeName, getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getAttribute(attributeName);
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get Attribute '%s' from control %s again", attributeName,
						getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
     * Get the visible (i.e. not hidden by CSS) innerText of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The innerText of this element
     */
	@Override
	public String getText() {
		logger.info(String.format("Get Text of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getText();
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get Text from control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
     * Get value of the value attribute (i.e. not hidden by CSS) of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The value of this element
     */
	@Override
	public String getValue() {
		logger.info(String.format("Get Value of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getAttribute("value");
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get Value from control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}
	
	/**
     * Get class name of the class attribute (i.e. not hidden by CSS) of this element, including sub-elements,
     * without any leading or trailing whitespace
     *
     * @return The class name of this element
     */
	@Override
	public String getClassName() {
		logger.info(String.format("Get ClassName of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getAttribute("class");
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get ClassName from control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
     * Get the tag name of this element. <b>Not</b> the value of the name attribute: will return
     * <code>"input"</code> for the element <code><input name="foo" /></code>
     *
     * @return The tag name of this element
     */
	@Override
	public String getTagName() {
		logger.info(String.format("Get TagName of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getTagName();
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				logger.severe(String.format("Try to get TagName from control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
     * Get number of elements
     *
     * @return The number of the element on the page
     */
	@Override
	public int getNumber() {
		int size = 0;
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				logger.info(String.format("Get Size of %s", getLocator().toString()));
				if (this.getElements() != null) {
					size = this.getElements().size();
				}
				return size;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return size;
				logger.severe(String.format("Try to get Size from control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(String.format("Exception! - Error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
				return size;
			}
		}
		return size;
	}

	/**
	 * Get texts from list of elements
	 * 
	 * @return list of texts
	 */
	@Override
	public List<String> getAllTexts() {
		List<WebElement> listOfElement = this.getElements();
		List<String> textLst = new ArrayList<String>();
		try {
			logger.info(String.format("Get all name of elements %s", getLocator().toString()));
			for (int i = 0; i < listOfElement.size(); i++) {
				textLst.add(listOfElement.get(i).getText());
			}
		} catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
		return textLst;
	}

	/**
     * What is the width and height of the rendered element?
     * We can use:
     * - getElement().getSize().width or getElement().getSize().getHeight()
     *
     * @return The size of the element on the page
     */
	@Override
	public Dimension getSize() {
		logger.info(String.format("Get Size of %s", getLocator().toString()));
		int tries = 0;
		while (tries < Constant.SHORT_TIMEOUT) {
		    tries++;
		    try {
		    	return getElement().getSize();
		    } catch (StaleElementReferenceException staleEx) {
		    	if (tries == Constant.SHORT_TIMEOUT)
		    		return null;
		    	logger.severe(String.format("Try to get Size from control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}
	
	/*
	 * ========================= Action
	 * ===========================================================================
	 */
	/**
	 * Override methods action of element status from IAction interface
	 */
	
	/**
	 * Performs a click on the element. Try seconds of timeout for element clickable  
	 */
	@Override
	public void click() {
		logger.info(String.format("Click on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.CLICKABLE, Constant.TIMEOUT, true);
				getElement().click();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to click control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Click on the Element by JS. Try seconds of timeout for element present  
     */
	@Override
	public void clickByJS() {
		logger.info(String.format("Click by JS on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.executeJavaScript("arguments[0].click();", getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to clickByJS control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Performs a double-click on the Element. Try seconds of timeout for element clickable  
     */
	@Override
	public void doubleClick() {
		logger.info(String.format("Double-click on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.CLICKABLE, Constant.TIMEOUT, true);
				int count = 0;
				while (count < 2) {
					click();
					count++;
				}
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to doubleClick control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Wait time out for element present
     * Use this method to simulate typing into an element, which may set its value
     *
     * @param keysToSend - CharSequence to send
     */
	@Override
	public void sendKeys(CharSequence... keysToEnter) {
		logger.info(String.format("Send keys '%s' to %s", keysToEnter, getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				getElement().sendKeys(keysToEnter);
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to sendKeys to control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}

	}

	/**
     * Wait time out for element present
     * Use this method to simulate clear text value for an element
     */
	@Override
	public void clear() {
		logger.info(String.format("Clear text in element %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				getElement().clear();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to clear for control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Wait time out for element present
     * Use this method to submit for an element (Button)
     */
	@Override
	public void submit() {
		logger.info(String.format("Submit %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				getElement().submit();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to submit control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Performs focus to an element by JS. Wait time out for element present
     */
	@Override
	public void focus() {
		logger.info(String.format("Focus on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.executeJavaScript("arguments[0].focus();", getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to focus on control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	 /**
     * Performs a mover at the current mouse location. Wait time out for element present
     */
	@Override
	public void hover() {
		logger.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.executeJavaScript(mouseHoverScript, getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to hover on control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Mouse Over on the the given element. Wait time out for element present
     */
	@Override
	public void moveToElement() {
		logger.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.getActions().moveToElement(getElement()).build().perform();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to hover on control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
     * Scroll to an element by JS. Wait time out for element present
     */
	@Override
	public void scrollIntoView() {
		logger.info(String.format("Scroll to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.executeJavaScript("arguments[0].scrollIntoView(true);", getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
	 * Select an Element in seconds of timeout
	 */
	@Override
	public void select() {
		logger.info(String.format("select %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				if (!isSelected())
					click();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
	 * Check on an element. Check if element is selected of not in seconds of timeout
	 */
	@Override
	public void check() {
		logger.info(String.format("Check ON %s", getLocator().toString()));
		if (!isSelected())
			click();
	}

	/**
	 * Uncheck on an element. Check if element is selected of not in seconds of timeout
	 */
	@Override
	public void uncheck() {
		logger.info(String.format("Check OFF %s", getLocator().toString()));
		if (isSelected())
			click();
	}
}
