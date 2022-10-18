package tests.OrangeHRM;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import core.report.Logger;
import dataType.OrangeHRM.Account;
import dataType.OrangeHRM.PIMItem;
import dataType.OrangeHRM.UserRole;
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
	public void TC06() {
		Logger.info("Test case 06: User can add a new employee successful without Create Login Details");

		Account account = new Account(UserRole.ADMIN);
		String firstName = "Alex";
		String middleName = " Mac";
		String lastName = "Nguyen";
		String employeeId = "123";

		Logger.info("Precondition: Login successfully with a valid account");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click \"Add\" or \"Add Employee\" button");
		pimPage.clickTopMenuButton(PIMItem.ADDEMPLOYEELIST.getName());

		Logger.verify("1. User is redirected to \"Add Employee\" page.");
		assertTrue(pimPage.isTopMenuButtonActived(PIMItem.ADDEMPLOYEELIST),
				"User is redirected to \"Add Employee\" page.");

		Logger.info("Step 2: Enter all required information and turn off \"Create Login Details\" option");
		Logger.info("Step 3: Click \"Save\" ");
		Logger.info("Step 4: Verify new added employee is displayed in Employee list");
		pimPage.addEmployeeWithoutCreateLoginDetails(firstName, middleName, lastName, employeeId);

		Logger.verify("3. A new employee is added successful.");
		Logger.verify("4. The employee is displayed.");
		assertTrue(pimPage.isEmployeeNameDisplayed(firstName + " " + lastName), "A new employee is added successful.");

	}

}
