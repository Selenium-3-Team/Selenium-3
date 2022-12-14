package tests;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import core.driver.manager.Driver;
import core.driver.manager.DriverManager;
import core.report.ExtentTestManager;
import core.report.TestListener;
import utils.constant.Constant;

public class TestBase {

	@Parameters({ "driverConfig", "platform" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome.local") String driverConfig, @Optional("Windows") String platform,
			ITestContext context, Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(), TestListener.testSuite.get(context.getName()));
		DriverManager.loadDriverProperty(Constant.DRIVER_SETTING_FILE, platform, driverConfig);
		DriverManager.initDriver();
		Driver.maximizeBrowser();
		Driver.navigate(Constant.ORANGEHRM_URL);
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		System.out.println("Post Condition: Clean up.");
		Driver.quitAll();
	}

}
