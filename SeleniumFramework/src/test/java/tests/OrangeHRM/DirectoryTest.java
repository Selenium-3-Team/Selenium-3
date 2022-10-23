package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.DirectoryForm;
import dataType.OrangeHRM.LocationOption;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;

public class DirectoryTest extends TestBase {

	@Test
	@Description("Test case 18: User can search the employees in the directory by name.")
	public void TC18() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();
		String employeeName = pimPage.getRandomEmployeeNameInList();

		Logger.info("Step 1: Click Directory on Left menu");
		directoryPage = pimPage.clickDirectoryTabOnLeftPanel().waitForPageLoad();

		Logger.info("Step 2: Enter employee name in the employee name textbox.");
		directoryPage.enterEmployeeName(employeeName);

		Logger.info("Step 3: Click Search button.");
		directoryPage.clickSearchBtn();

		Logger.info("VP: Employees have name that is searched are displayed.");
		assertHelper.assertTrue(directoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(employeeName), "Employee list is displayed incorrectly.");

	}

	@Test
	@Description("Test case 19: User can search the employees in the directory by job title.")
	public void TC19() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();
		String jobTitle = pimPage.getRandomJobTitleInList();

		Logger.info("Step 1: Click Directory on Left menu");
		directoryPage = pimPage.clickDirectoryTabOnLeftPanel().waitForPageLoad();

		Logger.info("Step 2: Select job title in the job title dropbox list.");
		directoryPage.selectOptionOnDirectoryForm(DirectoryForm.JOB_TITLE_DROPDOWN, jobTitle);

		Logger.info("Step 3: Click Search button.");
		directoryPage.clickSearchBtn();

		Logger.info("VP: Employees have job title that is searched are displayed.");
		assertHelper.assertTrue(directoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(jobTitle), "Employee list is displayed incorrectly.");

	}

	@Test
	@Description("Test case 20: User can search the employees in the directory by location.")
	public void TC20() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();

		Logger.info("Step 1: Click Directory on Left menu");
		directoryPage = pimPage.clickDirectoryTabOnLeftPanel().waitForPageLoad();

		Logger.info("Step 2: Select location in the location dropbox list.");
		String location = LocationOption.randomLocationOption();
		directoryPage.selectOptionOnDirectoryForm(DirectoryForm.LOCATION_DROPDOWN, location);

		Logger.info("Step 3: Click Search button.");
		directoryPage.clickSearchBtn();

		Logger.info("VP: Employees have location that is searched are displayed.");
		assertHelper.assertTrue(directoryPage.isEmployeeListDisplayedCorrectlyAccordingTo(location), "Employee list is displayed incorrectly.");

	}

}
