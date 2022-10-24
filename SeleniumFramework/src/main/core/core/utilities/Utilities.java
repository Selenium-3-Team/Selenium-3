package core.utilities;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.common.Constant;
import core.driver.manager.DriverManager;

/**
 * A collection of methods to deal with various text related activities
 */
public class Utilities {

	/**
	 * Contains log of the utilities used
	 */
	private static final Logger logger = Constant.createLogger(Utilities.class.getName());

	/**
	 * Get project absolute path
	 * 
	 * @return a path string
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * Capture the screenshot
	 * 
	 * @return the output type for a screenshot
	 */
	public static byte[] takeScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
		return (byte[]) (scrShot.getScreenshotAs(OutputType.BYTES));
	}

	/**
	 * Capture the screenshot
	 * 
	 * @param filename - a string of file name
	 * @param filepath - a string of location
	 * @return the specified location
	 * @throws Exception - throw if driver cannot capture the screenshot
	 */
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
	 * Put path to your file in a clipboard
	 * 
	 * @param string - file path
	 */
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * Imitate mouse events like ENTER, CTRL+C, CTRL+V to paste the data from
	 * clipboard
	 * 
	 * @param fileLocation - file path
	 */
	public static void uploadFile(String fileLocation) {
		try {
			setClipboardData(fileLocation);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}