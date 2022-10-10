package Tiki;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Common.ExtentTestManager;
import Common.TestListener;
import Constant.Constant;
import core.driver.manager.Driver;
import core.driver.manager.DriverManager;

public class TestBase {

	@Parameters({ "driverConfig", "platform"})
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String driverConfig, String platform, ITestContext context, Method method)
			throws Throwable {
		ExtentTestManager.startTest(method.getName(), TestListener.testSuite.get(context.getName()));
		DriverManager.loadDriverProperty(Constant.DRIVER_SETTING_FILE, platform, driverConfig);
		DriverManager.initDriver();
		Driver.maximizeBrowser();
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		System.out.println("Post Condition: Clean up.");
		Driver.quitAll();
	}
	
}
