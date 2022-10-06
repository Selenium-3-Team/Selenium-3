package ElementBase;

import java.util.function.Function;
import java.util.logging.Logger;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import CoreCommon.Constant;
import DriverWrapper.DriverManagement;
import ElementSetting.Status;

public interface IWaiter extends IFinder{
	/**
	 * Element's waiter 
	 */
	public static final Logger logger = Constant.createLogger(IWaiter.class.getName());
	
	/**
	 * Wait for element following to condition with timeout
	 * @param status - element's status, start it with "displayed, not displayed,..."
	 * @param timeOut - a time out in seconds
	 * @param throwable - throwable status: True or False, if waits of element are false. it will throw a exception
	 */
	public default void waitForCondition(Status status, int timeOut, boolean throwable) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManagement.getDriver(), timeOut);
			switch (status) {
			case PRESENT:
				wait.until(new Function<WebDriver, Boolean>(){
					public Boolean apply(WebDriver driver) {
						try {
							getElement();
						} catch (NoSuchElementException e) {
							return false;
						}
						return true;
					}
				});
				break;
			case NOT_PRESENT:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							getElement();
						} catch (NoSuchElementException e) {
							return true;
						}
						return false;
					}
				});
				break;
			case DISPLAYED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return getElement().isDisplayed();
						} catch (NoSuchElementException e) {
							return false;
						}
						catch (StaleElementReferenceException e) {
							return false;
						}
					}
				});
				break;
			case NOT_DISPLAYED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return !(getElement().isDisplayed());
						} catch (NoSuchElementException e) {
							return true;
						}
						catch (StaleElementReferenceException e) {
							return true;
						}
					}
				});
				break;
			case CLICKABLE:
			case ENABLED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return getElement().isDisplayed() && getElement().isEnabled();
						} catch (NoSuchElementException e) {
							return false;
						}
						catch (StaleElementReferenceException e) {
							return false;
						}
					}
				});
				break;
			case NOT_CLICKABLE:
			case DISABLED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return !(getElement().isDisplayed() && getElement().isEnabled());
						} catch (NoSuchElementException e) {
							return false;
						}
						catch (StaleElementReferenceException e) {
							return false;
						}
					}
				});
				break;
			case SELECTED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return getElement().isSelected();
						} catch (NoSuchElementException e) {
							return false;
						}
						catch (StaleElementReferenceException e) {
							return false;
						}
					}
				});
				break;
			case NOT_SELECTED:
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							return !(getElement().isSelected());
						} catch (NoSuchElementException e) {
							return false;
						}
						catch (StaleElementReferenceException e) {
							return false;
						}
					}
				});
				break;
			default:
				break;
			}
		}catch (Exception error) {
			logger.severe(String.format("Has error when wait for element %s '%s': %s", 
					status.toString().toLowerCase(), getLocator().toString(), error.getMessage()));
			if (throwable)
			{
				if (error instanceof TimeoutException)
				{
					throw new TimeoutException(String.format("Timeout when wait for element '%s' is '%s' within %s seconds", 
							getLocator().toString(), status.toString().toLowerCase(), timeOut));
				}
				else throw error;
			}
		}
	}
	
	/**
	 * Wait for element is presented in DOM
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForPresent(int timeOut) {
		waitForCondition(Status.PRESENT, timeOut, true);
	}
	
	/**
	 * Wait for element is not presented in DOM
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForNotPresent(int timeOut) {
		waitForCondition(Status.NOT_PRESENT, timeOut, true);
	}

	/**
	 * Wait for element is clickable
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForClickable(int timeOut) {
		waitForCondition(Status.CLICKABLE, timeOut, true);
	}
	
	/**
	 * Wait for element is not clickable
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForNotClickable(int timeOut) {
		waitForCondition(Status.NOT_CLICKABLE, timeOut, true);
	}

	/**
	 * Wait for element is displayed
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForDisplayed(int timeOut) {
		waitForCondition(Status.DISPLAYED, timeOut, true);
	}
	
	/**
	 * Wait for element is not displayed
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForNotDisplayed(int timeOut) {
		waitForCondition(Status.NOT_DISPLAYED, timeOut, true);
	}
	
	/**
	 * Wait for element is enabled
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForEnabled(int timeOut) {
		waitForCondition(Status.ENABLED, timeOut, true);
	}
	
	/**
	 * Wait for element is disabled
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForDisabled(int timeOut) {
		waitForCondition(Status.DISABLED, timeOut, true);
	}
	
	/**
	 * Wait for element is selected
	 * @param timeOut - wait for seconds time out 
	 */
	public default void waitForSelected(int timeOut) {
		waitForCondition(Status.SELECTED, timeOut, true);
	}

}
