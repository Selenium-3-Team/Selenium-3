package core.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import core.common.Constant;
import core.driver.manager.Driver;
import core.driver.manager.DriverManager;

public class Utilities {
	
	private static final Logger logger = Constant.createLogger(Utilities.class.getName());
	
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public static String getDateNow(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}

	public static byte[] takeScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
		return (byte[]) (scrShot.getScreenshotAs(OutputType.BYTES));
	}

	public static String takeScreenShot(String filename, String filepath) throws Exception {
		String path = "";
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filepath + File.separator + filename + ".png");
			FileUtils.copyFile(SrcFile, DestFile);
			path = DestFile.getAbsolutePath();
		} catch (Exception e) {
			logger.severe("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	/**
	 *	@example: 	boolean result = true -> Declare a boolean variable
	 *				result &= check() {N.1}, result &= check() {N.2},...
	 *				return result	 
	 */
	public static boolean check(boolean actual, String message) {
		try {
			Assert.assertTrue(actual, message);
			logger.info(message);
			return true;
		} catch (AssertionError assertionError) {
			logger.severe(message + ": " + assertionError);
			try {
				logger.info("URL: " + Driver.getURL());
			} catch (Throwable throwable) {
				logger.severe("Cannot get URL browser");
			}
			return false;
		}
	}
	
	public static boolean returnCheckResult(ArrayList<Boolean> results) {
		return results.contains(true);
	}
}