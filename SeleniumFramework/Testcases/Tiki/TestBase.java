package Tiki;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import core.driver.manager.Driver;
import core.driver.manager.DriverManager;
import Constant.Constant;

public class TestBase {

	@Parameters({ "driverConfig", "platform"})
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String driverConfig, String platform)
			throws Throwable {
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
