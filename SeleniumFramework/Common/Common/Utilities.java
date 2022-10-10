package Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;
import core.driver.manager.Driver;
import core.driver.manager.DriverManager;
import core.helper.JsonHelper;
import core.utilities.AlertModal;

public class Utilities {

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
			System.err.println("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	public static void waitForPageLoad(int timeOutInSecond) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Driver.executeJavaScript("return document.readyState").equals("complete"));
			}
		};
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSecond));
		wait.until(pageLoadCondition);
	}

	public static boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constant.DEFAULT_TIMEOUT));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
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

	public static String[] splitString(String string, String delimiter) {
		return string.trim().split(delimiter);
	}

	public static String replaceEscapeCharacters(String string) {
		final String[] characters = { "\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "|", "<", ">",
				"-", "&", "%" };
		for (int i = 0; i < characters.length; i++) {
			if (string.contains(characters[i])) {
				string = string.replace(characters[i], "\\").trim();
			}
		}

		return string;
	}

	public static String getDriverTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public static Object[] removeStringFromArrayByIndex(Object[] array, int index) {
		if (array == null || index < 0 || index >= array.length) {
			return array;
		}
		Object[] anotherArray = new String[array.length - 1];
		for (int i = 0, k = 0; i < array.length; i++) {

			// if the index is
			// the removal element index
			if (i == index) {
				continue;
			}
			anotherArray[k++] = array[i];
		}
		return anotherArray;
	}

	public static boolean isContains(String[] outer, String[] inner) {
		return Arrays.asList(outer).containsAll(Arrays.asList(inner));
	}

	public static boolean isContains(String[] outer, String string) {
		return Arrays.asList(outer).contains(string);
	}

	public static boolean isItemExistedInList(String[] array, String item) {
		boolean existed = false;
		List<String> list = Arrays.asList(array);
		if (list.contains(item)) {
			existed = true;
		}
		return existed;
	}

	public static boolean isSorted(String[] array) {
		boolean isSorted = true;
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1].toLowerCase().compareTo(array[i].toLowerCase()) > 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public static String replaceCharFromStringByIndex(String str, int index, char replace) {
		StringBuilder string = new StringBuilder(str);
		string.setCharAt(index, replace);
		return string.toString();
	}

	public static String removeSubString(String originalString, String subString) {
		String newString = originalString.replace(subString, "").trim();
		return newString;
	}

	public static String removeAllCharacterInString(String originalString, String character) {
		String newString = originalString.replaceAll(character, "").trim();
		return newString;
	}
	
	public static String getValue(String filePath, String key) {
		String value = "";
		try {
			value = JsonHelper.getJsonObject(filePath).get(key).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static Object getDataFromJsonFile(String folderName, String fileName) {
		JSONParser parser = new JSONParser();
		String locatorTestPath = System.getProperty("user.dir");

		String locatorResourcePath = File.separator + "Data" + File.separator + folderName + File.separator + fileName + ".json";
		try {
			JSONArray a = (JSONArray) parser.parse(new FileReader(locatorTestPath + locatorResourcePath));
			for (Object o : a) {
				return (JSONObject) o;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}