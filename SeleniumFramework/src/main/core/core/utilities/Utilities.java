package core.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.driver.manager.DriverManager;
import core.helper.AlertModal;

public class Utilities {

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
			System.err.println("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	public static String getAlertMessage() {
		return new AlertModal(DriverManager.getDriver()).getAlertText();
	}

	public static void promptAlert(String inputText) {
		new AlertModal(DriverManager.getDriver()).prompt(inputText);
	}

	public static void acceptAlert() {
		new AlertModal(DriverManager.getDriver()).confirm();
	}

	public static void dismissAlert() {
		new AlertModal(DriverManager.getDriver()).dismiss();
	}

	public static String convertToNbsp(String input) {
		return String.format(input).replaceAll(" ", "�");
	}

}