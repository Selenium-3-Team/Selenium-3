package tests.OrangeHRM;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import core.report.Logger;
import dataType.OrangeHRM.Account;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.PIMItem;
import dataType.OrangeHRM.UserRole;
import pages.OrangeHRM.HomePage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.PIMPage;
import tests.TestBase;
import utils.constant.Constant;

public class LoginTest extends TestBase {

	LoginPage loginPage = LoginPage.newInstance();
	HomePage homePage = HomePage.newInstance();
	PIMPage pimPage = PIMPage.newInstance();

	@Test
	public void TC01() {
		Logger.info("Test case 01: User can log in successfully with a valid username and a valid password");

		Account account = new Account(UserRole.ADMIN);
		Logger.info("Step 1: Navigate to OrangeHRM");

		Logger.verify("1.1. The login form should be displayed.");
		assertTrue(loginPage.isDisplayed(), "The login form should be displayed.");

		Logger.verify("1.2. OrangeHRM copyright text should be displayed.");
		assertTrue(loginPage.isCopyRightTextDisplayed(Constant.COMPANY, Constant.VERSION), "OrangeHRM copyright text should be displayed.");

		Logger.info("Step 2: Enter a valid username in the \"Username\" textbox");
		loginPage.enterUsername(account.getUsername());

		Logger.info("Step 3: Enter a valid password in the \"Password\" textbox");
		loginPage.enterPassword(account.getPassword());

		Logger.info("Step 4: Click the \"Login\" button");
		pimPage = loginPage.clickLoginBtn();

		Logger.verify("4.1. VP. The PIM page should be displayed.");
		assertTrue(pimPage.isHeaderTitleDisplayed(LeftPanelMenuItem.PIM), "The PIM page should be displayed");

		Logger.verify("4.2. The currently selected tab is \"Employee List\".");
		assertTrue(pimPage.isTopMenuButtonActived(PIMItem.EMPLOYEELIST), "The currently selected tab is \"Employee List\".");

		Logger.verify("4.3 OrangeHRM copyright text should be displayed.");
		assertTrue(homePage.isCopyRightTextDisplayed(Constant.COMPANY, Constant.VERSION), "OrangeHRM copyright text should be displayed.");
	}

	@Test
	public void TC02() {
		Logger.info("Test case 02: User can't log in successfully with an invalid username and a valid password");

		String invalidUsername = "Admin1";
		String password = "admin123";
		Logger.info("Step 1: Navigate to OrangeHRM");

		Logger.info("Step 2: Enter an invalid username in the \"Username\" textbox");
		Logger.info("Step 3: Enter a valid password in the \"Password\" textbox");
		Logger.info("Step 4: Click the \"Login\" button");
		loginPage.loginOrangeHRM(invalidUsername, password);

		Logger.verify("4.1. The sLogin page should still be displayed.");
		assertTrue(loginPage.isDisplayed(), "The Login page should still be displayed. ");

		Logger.verify("4.2. The error \"Invalid credentials\" message should be displayed.");
		assertTrue(loginPage.isInvalidCredentialsAlertDisplayed(),
				"The error \"Invalid credentials\" message should be displayed.");

	}

	@Test
	public void TC03() {
		Logger.info("Test case 03: User can log out of the account successfully");

		Account account = new Account(UserRole.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click on the user's avatar");
		Logger.info("Step 2: Click on the \"Logout\" button");
		loginPage = pimPage.logoutOrangeHRM();

		Logger.verify("2. The Login page should still be displayed. ");
		assertTrue(loginPage.isDisplayed(), "The Login page should still be displayed. ");

	}

}
