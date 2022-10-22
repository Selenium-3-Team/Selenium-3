package core.report;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import core.utilities.CoreUtilities;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

/**
 * This class will implements ITestListener which listens to specificevents and
 * executes the code written inside the method and put these events to a
 * extentTest map for each thread
 */
public class TestListener implements ITestListener {

	public static ConcurrentHashMap<String, ExtentTest> testSuite = new ConcurrentHashMap<String, ExtentTest>();

	/**
	 * This method invokes when the test class is instantiated and before executing
	 * any test method
	 * 
	 * @param context - The test context
	 */
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		// Handle Report
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), null);
			testSuite.put(context.getName(), tmpSuite);
		}
	}

	/**
	 * This method invokes every time a test method is called and executed
	 * 
	 * @param result - the partially filled <code>context</code>
	 */
	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test: " + result.getMethod().getMethodName() + " ..."));
		ExtentTestManager.getTest().assignCategory(result.getTestContext().getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
	}

	/**
	 * This method is invoked every time a test case passes (succeeds).
	 * 
	 * @param result - <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS,
				"*** TEST EXECUTION COMPLETE - PASSED: " + result.getMethod().getMethodName());
	}

	/**
	 * Take screenshot for allure report attachment
	 * 
	 * @param name - screenshot name
	 * @return Object in which is stored information about the screenshot
	 */
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] saveScreenshot(String name) {
		return CoreUtilities.takeScreenShot();
	}

	/**
	 * This method invokes every time a test case fails and capture screenshot for
	 * report attatachment.
	 * 
	 * @param result - <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestFailure(ITestResult result) {

		// capture screenshot
		String screenshotFileName = UUID.randomUUID().toString();
		String screenshotFilePath = "";
		try {
			saveScreenshot(result.getName());
			screenshotFilePath = CoreUtilities.takeScreenShot(screenshotFileName,
					ExtentReportManager.getScreenshotFolder());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String methodName = result.getMethod().getMethodName();
			String throwMessage = result.getThrowable().getMessage();
			// attach screenshots to report
			String message = String.format("*** TEST EXECUTION COMPLETE - ERROR: %s - %s", methodName, throwMessage);
			if (result.getThrowable() instanceof AssertionError) {
				message = String.format("*** TEST EXECUTION COMPLETE - FAILED: %s - %s", methodName, throwMessage);
				ExtentTestManager.getTest().fail(message,
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
			} else {
				ExtentTestManager.getTest().error(message,
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());

			}
			Allure.attachment(message, screenshotFilePath);

		} catch (IOException e) {
			Logger.info("An exception occured while taking screenshot " + e.getCause());
			e.printStackTrace();
		}
	}

	/**
	 * This method invokes every time a test skips
	 * 
	 * @param result - <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestSkipped(ITestResult result) {
		Logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	/**
	 * This method invokes when the test method fails as a whole but has passed a
	 * certain success percentage which is defined by the user
	 * 
	 * @param result - <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}