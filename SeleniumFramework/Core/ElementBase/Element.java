package ElementBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.javatuples.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Stopwatch;

import CoreCommon.Constant;
import DriverWrapper.Driver;
import DriverWrapper.DriverManagement;
import ElementSetting.FindBy;
import ElementSetting.Status;
import Helper.LocatorHelper;

/**
 * Element control implementation
 * @author Thang Nguyen
 * @author Tuan Ngo
 */
public class Element implements IWaiter, IAction, IInfo{

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
	 * @param locator - start it with locator type: id, xpath, css,...
	 */
	public Element(By locator) {
		this.byLocator = locator;
	}
	
	/**
	 * Initializes element with given string of locator
	 * @param locator - start it with a string, takes given string to identify type of locator and tries to initialize
	 */
	public Element(String locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = LocatorHelper.getPairLocator(locator);
	}
	
	/**
	 * Initializes element with parent of current element and string
	 * @param parentElement - Parent Element instance 
	 * @param locator - start it with a string, takes given string to identify type of locator and tries to initialize
	 */
	public Element(Element parentElement, String locator) {
		this.byLocator = getByLocator(locator);
		this.pairLocator = LocatorHelper.getPairLocator(locator);
		this.parentElement = parentElement;
	}
	
	/**
	 * Initializes element with given dynamic string of locator 
	 * @param locator - start it with a string, takes given string to identify type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic string 
	 */
	public Element(String locator, Object... arguments) {
		this.byLocator = getByLocator(String.format(locator, arguments));
		this.pairLocator = LocatorHelper.getPairLocator(locator);
	}
	
	/**
	 * Initializes dynamic element via parent element
	 * @param parentElement - Parent Element instance 
	 * @param locator - start it with a string, takes given string to identify type of locator by separate value to pair<FindBy, String>
	 * @param arguments - variable-length arguments of type Object, use for dynamic string 
	 */
	public Element(Element parentElement, String locator, Object... arguments) {
		this.byLocator = getByLocator(String.format(locator, arguments));
		this.pairLocator = LocatorHelper.getPairLocator(locator);
		this.parentElement = parentElement;
	}

	/**
	 * Initializes element with given FindBy and value String
	 * @param by - Type of locator
	 * @param value - value of Locator
	 */
	public Element(FindBy by, String value) {
		this.byLocator = getByLocator(by, value);
		this.pairLocator = new Pair<FindBy, String>(by, value);
	}
	
	/**
	 * Initializes element via parent element with given FindBy and value String
	 * @param parentElement - Parent Element instance
	 * @param by - Type of locator
	 * @param value - value of Locator
	 */
	public Element(Element parentElement, FindBy by, String value) {
		this.byLocator = getByLocator(by, value);
		this.pairLocator = new Pair<FindBy, String>(by, value);
		this.parentElement = parentElement;
	}

	/**
	 * Initializes dynamic element with given FindBy and value String
	 * @param by - Type of locator
	 * @param value - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic locator 
	 */
	public Element(FindBy by, String value, Object... arguments) {
		this.byLocator = getByLocator(by, String.format(value, arguments));
		this.pairLocator = new Pair<FindBy, String>(by, value);
	}
	
	/**
	 * Initializes dynamic element via parent element with given FindBy and value String
	 * @param parentElement - Parent Element instance
	 * @param by - Type of locator
	 * @param value - value of Locator
	 * @param arguments - variable-length arguments of type Object, use for dynamic locator 
	 */
	public Element(Element parentElement, FindBy by, String value, Object... arguments) {
		this.byLocator = getByLocator(by, String.format(value, arguments));
		this.pairLocator = new Pair<FindBy, String>(by, value);
		this.parentElement = parentElement;
	}
	
	/**
	 * Generate dynamic element
	 * @param arguments - variable-length arguments of type Object, use for dynamic locator 
	 * @return element
	 */
	public Element generateDynamic(Object... arguments)
	{
		if (this.pairLocator != null)
			this.byLocator = getByLocator(this.pairLocator.getValue0(), String.format(this.pairLocator.getValue1(), arguments));
		return this;
	}
	
	
	/*========================= Finder ===========================================================================*/
	/**
	 * Override methods find element from IFinder interface
	 */
	@Override
	public WebElement getElement() {
		if (parentElement != null)
			return parentElement.getChildElement(getLocator());
		return DriverManagement.getDriver().findElement(getLocator());
	}

	@Override
	public WebElement getChildElement(By locator) {
		return getElement().findElement(locator);
	}
	
	@Override
	public WebElement getChildElement(FindBy by, String value) {
		return getElement().findElement(getByLocator(by, value));
	}

	@Override
	public WebElement getChildElement(String locator) {
		return getElement().findElement(getByLocator(locator));
	}

	@Override
	public List<WebElement> getElements() {
		if (parentElement != null)
			return parentElement.getChildElements(getLocator());
		return DriverManagement.getDriver().findElements(getLocator());
	}

	@Override
	public List<WebElement> getChildElements(By locator) {
		return getElement().findElements(locator);
	}
	
	@Override
	public List<WebElement> getChildElements(FindBy by, String value) {
		return getElement().findElements(getByLocator(by, value));
	}

	@Override
	public List<WebElement> getChildElements(String locator) {
		return getElement().findElements(getByLocator(locator));
	}

	/*========================= Locator ===========================================================================*/
	/**
	 * Override methods get element's locator from ILocator interface
	 */
	@Override
	public By getLocator() {
		return this.byLocator;
	}

	@Override
	public By getParentLocator() {
		if (parentElement != null)
			return parentElement.getLocator();
		return null;
	}
	
	/*========================= Check Status ===========================================================================*/
	/**
	 * Override methods check element status from IFinder interface
	 */
	@Override
	public boolean isDisplayed() {
		return isDisplayed(Constant.TIMEOUT);
	}

	@Override
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			logger.info(String.format("Check if control %s is displayed", getLocator().toString()));
			waitForCondition(Status.DISPLAYED, timeOutInSeconds, true);
			return true;
		} catch (TimeoutException timeOutEx) {
	    	return false;
		}catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean isEnabled() {
		return isEnabled(Constant.TIMEOUT);
	}

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
		}catch (StaleElementReferenceException e) {
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

	@Override
	public boolean isSelected() {
		return isSelected(Constant.TIMEOUT);
	}

	@Override
	public boolean isSelected(int timeOutInSeconds) {
		logger.info(String.format("Check Selected status of %s in %s seconds", getLocator().toString(), timeOutInSeconds));
		try {
	    	waitForCondition(Status.SELECTED, timeOutInSeconds, true);
	    	return true;
	    } catch (TimeoutException timeOutEx) {
	    	return false;
	    } catch (Exception e) {
	    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
	    	return false;
	    }
	}

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
		    	logger.severe(String.format("Try to get CSS value '%s' from control %s again", propertyName, getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}

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
		    	logger.severe(String.format("Try to get Attribute '%s' from control %s again", attributeName, getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}

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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}

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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}

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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	return null;
		    }
		}
		return null;
	}
	
	@Override
	public int getSize() {
		int size = 0;
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	logger.info(String.format("Get Size of %s", getLocator().toString()));
		    	if(this.getElements() != null){
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

	@Override
	public List<String> getAllTexts(){
		List<WebElement> listOfElement = this.getElements();
		List<String> textLst = new ArrayList<String>();
		try {
			logger.info(String.format("Get all name of elements %s", getLocator().toString()));
			for(int i = 0; i < listOfElement.size(); i++) {
				textLst.add(listOfElement.get(i).getText());
			}			
		} catch (Exception e) {
			logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
		return textLst;
	}
	
	/*========================= Action ===========================================================================*/
	/**
	 * Override methods action of element status from IAction interface
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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void clickByJS() {
		logger.info(String.format("Click by JS on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
		    	Driver.executeScript("arguments[0].click();", getElement());
		    	return;
		    } catch (StaleElementReferenceException staleEx) {
		    	if (i == Constant.SHORT_TIMEOUT)
		    		throw staleEx;
		    	logger.severe(String.format("Try to clickByJS control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void doubleClick() {
		logger.info(String.format("Double-click on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	waitForCondition(Status.CLICKABLE, Constant.TIMEOUT, true);
		    	int count = 0;
				while(count < 2) {
					click();
					count++;
				}
		    	return;
		    } catch (StaleElementReferenceException staleEx) {
		    	if (i == Constant.SHORT_TIMEOUT)
		    		throw staleEx;
		    	logger.severe(String.format("Try to doubleClick control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
		
	}

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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}
	
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
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void focus() {
		logger.info(String.format("Focus on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
		    	Driver.executeScript("arguments[0].focus();", getElement());
		    	return;
		    } catch (StaleElementReferenceException staleEx) {
		    	if (i == Constant.SHORT_TIMEOUT)
		    		throw staleEx;
		    	logger.severe(String.format("Try to focus on control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void hover() {
		logger.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		    	waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
		    	Driver.executeScript(mouseHoverScript, getElement());
		    	return;
		    } catch (StaleElementReferenceException staleEx) {
		    	if (i == Constant.SHORT_TIMEOUT)
		    		throw staleEx;
		    	logger.severe(String.format("Try to hover on control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void moveToElement() {
		logger.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
		    i++;
		    try {
		    	waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
		    	Actions action = new Actions(DriverManagement.getDriver());
		    	action.moveToElement(getElement()).build().perform();
		    	return;
		    } catch (StaleElementReferenceException staleEx) {
		    	if (i == Constant.SHORT_TIMEOUT)
		    		throw staleEx;
		    	logger.severe(String.format("Try to hover on control %s again", getLocator().toString()));
		    } catch (Exception e) {
		    	logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void scrollIntoView() {
		logger.info(String.format("Scroll to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				waitForCondition(Status.PRESENT, Constant.TIMEOUT, true);
				Driver.executeScript("arguments[0].scrollIntoView(true);", getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}
	
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
				logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
		    	throw e;
		    }
		}
	}

	@Override
	public void check() {
		logger.info(String.format("Check ON %s", getLocator().toString()));
		if (!isSelected())
			click();
	}
	
	@Override
	public void uncheck() {
		logger.info(String.format("Check OFF %s", getLocator().toString()));
		if (isSelected())
			click();
	}
}