package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Description;
import pages.OrangeHRM.AdminPage;
import pages.OrangeHRM.HomePage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.PIMPage;
import tests.TestBase;

public class PIMTest extends TestBase {

	LoginPage loginPage = LoginPage.newInstance();
	HomePage homePage = HomePage.newInstance();
	PIMPage pimPage = PIMPage.newInstance();
	AdminPage adminPage = AdminPage.newInstance();

	@Test
	@Description("Test case 06: User can add a new employee successful without Create Login Details.")
	public void TC06() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		String firstName = "Alex";
		String middleName = " Mac";
		String lastName = "Nguyen";
		String employeeId = "123";

		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click \"Add\" or \"Add Employee\" button.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);

		Logger.verify("VP. User is redirected to \"Add Employee\" page.");
		assertHelper.assertTrue(pimPage.isTopBarMenuItemActived(TopBarMenuItem.ADD_EMPLOYEE), "User is redirected to \"Add Employee\" page.");

		Logger.info("Step 2: Enter all required information and turn off \"Create Login Details\" option.");
		Logger.info("Step 3: Click \"Save\".");
		Logger.info("Step 4: Verify new added employee is displayed in Employee list.");
		pimPage.addEmployeeWithoutCreateLoginDetails(firstName, middleName, lastName, employeeId);

		Logger.verify("VP. A new employee is added successful.");
		Logger.verify("VP. The employee is displayed.");
		assertHelper.assertTrue(pimPage.isEmployeeNameDisplayed(firstName + " " + lastName), "A new employee is added successful.");

	}

}
