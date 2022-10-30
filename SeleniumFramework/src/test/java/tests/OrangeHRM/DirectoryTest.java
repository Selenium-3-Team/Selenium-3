package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.LocationOption;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.ViewDirectoryPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import tests.TestBase;

public class DirectoryTest extends TestBase {

	@Test
	@Description("Test case 18: User can search the employees in the directory by name.")
	public void TC18() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ViewDirectoryPage viewDirectoryPage = ViewDirectoryPage.newInstance();
		
		Account account = new Account(UserRoleOption.ADMIN);
		
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		String employeeName = viewEmployeeListPage.getRandomEmployeeNameInList();

		Logger.info("Step 1: Click Directory on Left menu");
		viewDirectoryPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.DIRECTORY);

		Logger.info("Step 2: Enter employee name in the employee name textbox.");
		viewDirectoryPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_NAME, employeeName);

		Logger.info("Step 3: Click Search button.");
		viewDirectoryPage.clickSearchButton();

		Logger.info("VP: Employees have name that is searched are displayed.");
		assertHelper.assertTrue(viewDirectoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(employeeName), "Employee list is displayed incorrectly.");

	}

	@Test
	@Description("Test case 19: User can search the employees in the directory by job title.")
	public void TC19() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ViewDirectoryPage viewDirectoryPage = ViewDirectoryPage.newInstance();
		
		Account account = new Account(UserRoleOption.ADMIN);
		
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		String jobTitle = viewEmployeeListPage.getRandomJobTitleInList();

		Logger.info("Step 1: Click Directory on Left menu");
		viewDirectoryPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.DIRECTORY);

		Logger.info("Step 2: Select job title in the job title dropbox list.");
		viewDirectoryPage.selectOption(DropdownTitle.JOB_TITLE, jobTitle);

		Logger.info("Step 3: Click Search button.");
		viewDirectoryPage.clickSearchButton();

		Logger.info("VP: Employees have job title that is searched are displayed.");
		assertHelper.assertTrue(viewDirectoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(jobTitle), "Employee list is displayed incorrectly.");

	}

	@Test
	@Description("Test case 20: User can search the employees in the directory by location.")
	public void TC20() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ViewDirectoryPage viewDirectoryPage = ViewDirectoryPage.newInstance();
		
		Account account = new Account(UserRoleOption.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click Directory on Left menu");
		viewDirectoryPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.DIRECTORY);

		Logger.info("Step 2: Select location in the location dropbox list.");
		String location = LocationOption.randomLocationOption();
		viewDirectoryPage.selectOption(DropdownTitle.LOCATION, location);

		Logger.info("Step 3: Click Search button.");
		viewDirectoryPage.clickSearchButton();

		Logger.info("VP: Employees have location that is searched are displayed.");
		assertHelper.assertTrue(viewDirectoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(location), "Employee list is displayed incorrectly.");

	}

}
