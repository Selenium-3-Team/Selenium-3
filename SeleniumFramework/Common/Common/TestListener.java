package Common;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {

	public static ConcurrentHashMap<String, ExtentTest> testSuite = new ConcurrentHashMap<String, ExtentTest>();

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		// Handle Report
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), null);
			testSuite.put(context.getName(), tmpSuite);
		}
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test: " + result.getMethod().getMethodName() + " ..."));
		ExtentTestManager.getTest().assignCategory(result.getTestContext().getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS,
				"*** TEST EXECUTION COMPLETE - PASSED: " + result.getMethod().getMethodName());
	}

	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] saveScreenshot(String name) {
		return Utilities.takeScreenShot();
	}

	public void onTestFailure(ITestResult result) {

		// capture screenshot
		String screenshotFileName = UUID.randomUUID().toString();
		String screenshotFilePath = "";
		try {
			saveScreenshot(result.getName());
			screenshotFilePath = Utilities.takeScreenShot(screenshotFileName,
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

	public void onTestSkipped(ITestResult result) {
		Logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}