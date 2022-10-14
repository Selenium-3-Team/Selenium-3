package core.driver.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.common.Constant;
import core.driver.base.BaseDriver;

/**
 * Driver is a remote control that enables introspection and control of user
 * agents(browsers)
 */
public class Driver {

	/**
	 * Contains log of the class
	 */
	private static final Logger logger = Constant.createLogger(Driver.class.getName());

	/**
	 * Get driver via driver manager
	 * 
	 * @return WebDriver control
	 */
	public static WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	/**
	 * Get a string representing the current URL that the browser is looking at
	 * 
	 * @return The URL of the page currently loaded in the browser
	 */
	public static String getURL() {
		logger.info(String.format("Get current URL web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getCurrentUrl();
		} catch (Exception e) {
			logger.severe("An severe occurred when getting current URL: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Get the title of the current page.
	 * 
	 * @return The title of the current page, with leading and trailing whitespace
	 *         stripped, or null if one is not already set
	 */
	public static String getTitle() {
		logger.info(String.format("Get current title web pageof the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getTitle();
		} catch (Exception e) {
			logger.severe("An severe occurred when getting current title: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get the source of the last loaded page. If the page has been modified after
	 * loading (for example, by Javascript) there is no guarantee that the returned
	 * text is that of the modified page
	 * 
	 * @return The source of the current page
	 */
	public static String getPageSource() {
		logger.info(String.format("Get source of the last load page of the driver %s",
				DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getPageSource();
		} catch (Exception e) {
			logger.severe("An severe occurred when getting source page: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Return an opaque handle to this window that uniquely identifies it within
	 * this driver instance This can be used to switch to this window at a later
	 * date
	 * 
	 * @return the current window handle
	 */
	public static String getWindowHandle() {
		logger.info(String.format("Get window web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getWindowHandle();
		} catch (Exception e) {
			logger.severe("An severe occurred when getting window: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Return a set of window handles which can be used to iterate over all open
	 * windows of this WebDriver instance
	 * 
	 * @return A set of window handles which can be used to iterate over all open
	 *         windows
	 */
	public static List<String> getWindowHandles() {
		logger.info(String.format("Get all windows web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return new ArrayList<String>(getDriver().getWindowHandles());
		} catch (Exception e) {
			logger.severe("An severe occurred when getting windows: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get driver action
	 * 
	 * @return action which can be used to perform on element
	 */
	public static Actions getActions() {
		logger.info(String.format("Get action for the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return new Actions(getDriver());
		} catch (Exception e) {
			logger.severe("An severe occurred when getting actions: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Excute JS on web page
	 * 
	 * @param script - a string of js script
	 * @param objs   - variable-length arguments of type Object
	 * @return a string representation of a JS object data type
	 */
	public static Object executeJavaScript(String script, Object... objs) {
		logger.info("Execute javascript " + script);
		try {
			return ((JavascriptExecutor) getDriver()).executeScript(script, objs);
		} catch (Exception e) {
			logger.severe("An severe occurred when executing JS: " + e.getMessage());
			return null;
		}
	}

	/**
	 * An abstraction allowing the driver to access the browser's history and to
	 * navigate to a given URL
	 * 
	 * @param url - a string of browser address
	 */
	public static void navigate(String url) {
		logger.info("Navigate to " + url);
		try {
			getDriver().get(url);
		} catch (Exception e) {
			logger.severe("An severe occurred when nagivating: " + e.getMessage());
		}
	}

	/**
	 * Send future commands to a different frame or window and select a frame by its
	 * name or ID
	 * 
	 * @param nameOrId - a string of frame's name of ID
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			logger.info("Switch frame");
			getDriver().switchTo().frame(nameOrId);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching frame by name or id: " + e.getMessage());
		}
	}

	/**
	 * Send future commands to a different frame or window and select a frame by
	 * index
	 * 
	 * @param index - a number of index
	 */
	public static void switchToFrame(int index) {
		try {
			logger.info("Switch frame");
			getDriver().switchTo().frame(index);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching frame by frame index: " + e.getMessage());
		}
	}

	/**
	 * Send future commands to a different frame or window and select the parent
	 * frame
	 */
	public static void switchToParentFrame() {
		try {
			logger.info("Switch to parent frame");
			getDriver().switchTo().parentFrame();

		} catch (Exception e) {
			logger.severe("An severe occurred when switching to parent frame: " + e.getMessage());
		}
	}

	/**
	 * Send future commands to a different frame or window and select the default
	 * frame
	 */
	public static void switchToDefaultFrame() {
		try {
			logger.info("Switch to default frame");
			getDriver().switchTo().defaultContent();

		} catch (Exception e) {
			logger.severe("An severe occurred when switching to default frame: " + e.getMessage());
		}
	}

	/**
	 * Switch the focus of future commands for this driver to the window with the
	 * given name/handle.
	 * 
	 * @param windowHandle - a string name of window handle
	 */
	public static void switchTo(String windowHandle) {
		try {
			logger.info("Switch window");
			getDriver().switchTo().window(windowHandle);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching window: " + e.getMessage());
		}
	}

	/**
	 * Switch the focus of future commands for this driver to the first window
	 */
	public static void switchToFirst() {
		try {
			logger.info("Switch to first window");
			getDriver().switchTo().window(getWindowHandles().get(0));

		} catch (Exception e) {
			logger.severe("An severe occurred when switching first window: " + e.getMessage());
		}
	}

	/**
	 * Switch the focus of future commands for this driver to the last window
	 */
	public static void switchToLatest() {
		try {
			logger.info("Switch to latest window");
			List<String> windows = getWindowHandles();
			getDriver().switchTo().window(windows.get(windows.size() - 1));

		} catch (Exception e) {
			logger.severe("An severe occurred when switching latest window: " + e.getMessage());
		}
	}

	/**
	 * Maximizes the current window if it is not already maximized
	 */
	public static void maximizeBrowser() {
		try {
			logger.info("Maximize browser");
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			logger.severe("An severe occurred when maximizing browser" + e.getMessage());
		}
	}

	/**
	 * Close the current window, quitting the browser if it's the last window
	 * currently open.
	 */
	public static void close() {
		try {
			logger.info("Close browser");
			int windowCount = getWindowHandles().size();
			getDriver().close();
			if (windowCount == 1) {
				DriverManager.removeDriver();
			}

		} catch (Exception e) {
			logger.severe("An error occurred when closing browser:" + e.getMessage());
		}
	}

	/**
	 * Quits this driver, closing every associated window.
	 */
	public static void quit() {
		try {
			logger.info("Quit browser");
			getDriver().quit();
			DriverManager.removeDriver();

		} catch (Exception e) {
			logger.severe("An error occurred when quiting browser: " + e.getMessage());
		}
	}

	/**
	 * Quits all drivers, closing every associated window.
	 */
	public static void quitAll() {
		try {
			logger.info("Quit all browsers");
			for (Map.Entry<String, BaseDriver> item : DriverManager.getdriverThreadMap().entrySet()) {
				item.getValue().getWebDriver().quit();
			}

		} catch (Exception e) {
			logger.severe("An error occurred when quiting all browsers: " + e.getMessage());
		}
	}

	/**
	 * Capture screen shot
	 * 
	 * @param filename - a string of file name
	 * @param filepath - a string of file path location for saving
	 * @return - path of capture
	 * @throws Exception if driver cannot capture screen shot
	 */
	public static String takeScreenShot(String filename, String filepath) throws Exception {
		String path = "";
		try {
			// Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) getDriver());

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFile = new File(filepath + File.separator + filename + ".png");

			// Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
			path = DestFile.getAbsolutePath();
		} catch (Exception e) {
			logger.severe("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	/**
	 * Find the first web element using the given method
	 * 
	 * @param by - The locating mechanism to use
	 * @return The first matching element on the current page
	 */
	public static WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	/**
	 * Find all elements within the current page using the given mechanism
	 * 
	 * @param by - The locating mechanism to use
	 * @return A list of all matching web elements, or an empty list if nothing
	 *         matches
	 */
	public static List<WebElement> findElements(By by) {
		return getDriver().findElements(by);
	}
}
