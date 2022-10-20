package core.report;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import core.helper.RandomHelper;
import io.qameta.allure.Step;

/**
 * This class is designed for get log messages and put into ExtentTestManager
 * base on current test
 */
public class Logger {

	/**
	 * Log info
	 * 
	 * @param message - a string of message
	 */
	@Step("{0}")
	public static void info(String message) {
		Reporter.log("<b>INFO: </b>" + message);
		Reporter.log(RandomHelper.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - INFO: " + message, true);
		ExtentTestManager.getTest().log(Status.INFO, message);
	}

	/**
	 * Log bug
	 * 
	 * @param bugId   - a string of ID
	 * @param bugDesc - a string of description
	 */
	@Step("{0}")
	public static void bug(String bugId, String bugDesc) {
		String bugInfo = String.format("Bug %s - %s", bugId, bugDesc);
		String message = "<a target=\"_blank\" href=\"" + bugDesc
				+ "\" style=\"color:#DF0101;font-size:14px;word-break:break-word;\">" + bugInfo + "</a>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
	}

	/**
	 * Log warning
	 * 
	 * @param message - a string of message
	 */
	@Step("{0}")
	public static void warning(String message) {
		message = "<b style=\"color: darkorange;word-break:break-word;\"><i>WARNING: </i>" + message + "</b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
	}

	/**
	 * Log verify
	 * 
	 * @param message - a string of message
	 */
	@Step("{0}")
	public static void verify(String message) {
		String messageLog = message;
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: orange\">VERIFY POINT: </i>"
				+ message + "</b>";

		Reporter.log(message);
		Reporter.log(RandomHelper.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - VERIFY POINT: " + messageLog, true);
		ExtentTestManager.getTest().log(Status.INFO, message);
	}

	/**
	 * Log passed assertion
	 * 
	 * @param message - a string of message
	 */
	@Step("{0}")
	public static void passedAssertion(String message) {
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: #00af00\">" + message
				+ " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.PASS, message);
	}

	/**
	 * Log failed assertion
	 * 
	 * @param message - a string of message
	 */
	@Step("{0}")
	public static void failedAssertion(String message) {
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: red\">" + message + " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.FAIL, message);
	}

}