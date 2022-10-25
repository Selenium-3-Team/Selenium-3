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
import pages.OrangeHRM.AddEmployeePage;
import pages.OrangeHRM.AdminPage;
import pages.OrangeHRM.ApplyLeavePage;
import pages.OrangeHRM.LeavePage;
import pages.OrangeHRM.ListCustomFieldsPage;
import pages.OrangeHRM.DirectoryPage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.PIMPage;
import pages.OrangeHRM.SaveCustomFieldsPage;
import pages.OrangeHRM.UpdatePasswordPage;
import pages.OrangeHRM.ViewDirectoryPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewJobDetailsPage;
import pages.OrangeHRM.ViewLeaveListPage;
import pages.OrangeHRM.ViewPersonalDetailsPage;
import pages.OrangeHRM.ViewPhotographPage;
import pages.OrangeHRM.ViewSystemUsersPage;
import utils.constant.Constant;

public class TestBase {

	protected LoginPage loginPage = LoginPage.newInstance();
	protected PIMPage pimPage = PIMPage.newInstance();
	protected AdminPage adminPage = AdminPage.newInstance();
	protected LeavePage leavePage = LeavePage.newInstance();
	protected DirectoryPage directoryPage = DirectoryPage.newInstance();
	protected UpdatePasswordPage updatePasswordPage = UpdatePasswordPage.newInstance();
	protected ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
	protected ViewSystemUsersPage viewSystemUsersPage = ViewSystemUsersPage.newInstance();
	protected AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
	protected ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();
	protected ViewJobDetailsPage viewJobDetailsPage = ViewJobDetailsPage.newInstance();
	protected ViewPhotographPage viewPhotographPage = ViewPhotographPage.newInstance();
	protected ListCustomFieldsPage listCustomFieldsPage = ListCustomFieldsPage.newInstance();
	protected SaveCustomFieldsPage saveCustomFieldsPage = SaveCustomFieldsPage.newInstance();
	protected ViewLeaveListPage viewLeaveListPage = ViewLeaveListPage.newInstance();
	protected ApplyLeavePage applyLeavePage = ApplyLeavePage.newInstance();
	protected ViewDirectoryPage viewDirectoryPage = ViewDirectoryPage.newInstance();

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
