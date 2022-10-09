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

public class Driver {

	private static final Logger logger = Constant.createLogger(Driver.class.getName());
	
	public static WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	public static String getURL() {
		logger.info(String.format("Get current URL web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getCurrentUrl();
		}catch(Exception e) {
			logger.severe("An severe occurred when getting current URL: " + e.getMessage());
			return null;
		}
		
	}
	
	public static String getTitle() {
		logger.info(String.format("Get current title web pageof the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getTitle();
		}catch(Exception e) {
			logger.severe("An severe occurred when getting current title: " + e.getMessage());
			return null;
		}
	}
	
	public static String getPageSource() {
		logger.info(String.format("Get source of the last load page of the driver %s", DriverManager.getCurrentDriverKey()));
		try {
			return getDriver().getPageSource();
		}catch(Exception e) {
			logger.severe("An severe occurred when getting source page: " + e.getMessage());
			return null;
		}
	}

	public static String getWindowHandle() {
		logger.info(String.format("Get window web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {	
			return getDriver().getWindowHandle();
		}catch(Exception e) {
			logger.severe("An severe occurred when getting window: " + e.getMessage());
			return null;
		}
	}
	
	public static List<String> getWindowHandles() {
		logger.info(String.format("Get all windows web page for the driver %s", DriverManager.getCurrentDriverKey()));
		try {	
			return new ArrayList<String>(getDriver().getWindowHandles());
		}catch(Exception e) {
			logger.severe("An severe occurred when getting windows: " + e.getMessage());
			return null;
		}
	}
	
	public static Actions getActions() {
		logger.info(String.format("Get action for the driver %s", DriverManager.getCurrentDriverKey()));
		try {	
			return new Actions(getDriver());
		}catch(Exception e) {
			logger.severe("An severe occurred when getting actions: " + e.getMessage());
			return null;
		}
	}
	
	public static Object executeJavaScript(String script, Object... objs) {
		logger.info("Execute javascript " + script);
		try{
			return ((JavascriptExecutor) getDriver()).executeScript(script, objs);
		}catch(Exception e) {
			logger.severe("An severe occurred when executing JS: " + e.getMessage());
			return null;
		}
	}
	
	public static void navigate(String url) {
		logger.info("Navigate to " + url);
		try {
			getDriver().get(url);
		} catch (Exception e) {
			logger.severe("An severe occurred when nagivating: " + e.getMessage());
		}
	}
	
	public static void switchToFrame(String nameOrId) {
		try {
			logger.info("Switch frame");
			getDriver().switchTo().frame(nameOrId);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching frame by name or id: " + e.getMessage());
		}
	}
	
	public static void switchToFrame(int index) {
		try {
			logger.info("Switch frame");
			getDriver().switchTo().frame(index);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching frame by frame index: " + e.getMessage());
		}
	}
	
	public static void switchToParentFrame() {
		try {
			logger.info("Switch to parent frame");
			getDriver().switchTo().parentFrame();

		} catch (Exception e) {
			logger.severe("An severe occurred when switching to parent frame: " + e.getMessage());
		}
	}
	
	public static void switchToDefaultFrame() {
		try {
			logger.info("Switch to default frame");
			getDriver().switchTo().defaultContent();

		} catch (Exception e) {
			logger.severe("An severe occurred when switching to default frame: " + e.getMessage());
		}
	}

	public static void switchTo(String windowHandle) {
		try {
			logger.info("Switch window");
			getDriver().switchTo().window(windowHandle);

		} catch (Exception e) {
			logger.severe("An severe occurred when switching window: " + e.getMessage());
		}
	}
	
	public static void switchToFirst() {
		try {
			logger.info("Switch to first window");
			getDriver().switchTo().window(getWindowHandles().get(0));

		} catch (Exception e) {
			logger.severe("An severe occurred when switching first window: " + e.getMessage());
		}
	}
	
	public static void switchToLatest() {
		try {
			logger.info("Switch to latest window");
			List<String> windows = getWindowHandles();
			getDriver().switchTo().window(windows.get(windows.size() - 1));

		} catch (Exception e) {
			logger.severe("An severe occurred when switching latest window: " + e.getMessage());
		}
	}
	
	public static void maximizeBrowser() {
		try {
			logger.info("Maximize browser");
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			logger.severe("An severe occurred when maximizing browser" + e.getMessage());
		}
	}
	
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

	public static void quit() {
		try {
			logger.info("Quit browser");
			getDriver().quit();
			DriverManager.removeDriver();

		} catch (Exception e) {
			logger.severe("An error occurred when quiting browser: " + e.getMessage());
		}
	}

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
	
	public static WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	public static List<WebElement> findElements(By by) {
		return getDriver().findElements(by);
	}
}
