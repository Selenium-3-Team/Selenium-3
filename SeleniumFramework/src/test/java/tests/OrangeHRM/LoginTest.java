package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.helper.RandomHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Description;
import tests.TestBase;
import utils.constant.Constant;

public class LoginTest extends TestBase {

	@Test
	@Description("Test case 01: User can log in successfully with a valid username and a valid password.")
	public void TC01() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		Logger.info("Step 1: Navigate to OrangeHRM.");

		Logger.verify("VP. The login form should be displayed.");
		assertHelper.assertTrue(loginPage.isDisplayed(), "The login form should be displayed.");

		Logger.verify("VP. OrangeHRM copyright text should be displayed.");
		assertHelper.assertTrue(loginPage.isCopyRightTextDisplayed(Constant.COMPANY, Constant.VERSION), "OrangeHRM copyright text should be displayed.");

		Logger.info("Step 2: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);
		
		Logger.verify("VP. The PIM page should be displayed.");
		assertHelper.assertTrue(pimPage.isHeaderTitleDisplayed(LeftPanelMenuItem.PIM), "The PIM page should be displayed.");

		Logger.verify("VP. The currently selected tab is \"Employee List\".");
		assertHelper.assertTrue(pimPage.isTopBarMenuItemActived(TopBarMenuItem.EMPLOYEE_LIST), "The currently selected tab is \"Employee List\".");

		Logger.verify("VP. OrangeHRM copyright text should be displayed.");
		assertHelper.assertTrue(pimPage.isCopyRightTextDisplayed(Constant.COMPANY, Constant.VERSION), "OrangeHRM copyright text should be displayed.");

	}

	@Test
	@Description("Test case 02: User can't log in successfully with an invalid username and a valid password.")
	public void TC02() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(RandomHelper.randomString(), RandomHelper.randomString());
		Logger.info("Step 1: Navigate to OrangeHRM.");

		Logger.info("Step 2: Login with a invalid account.");
		loginPage.enterUsername(account.getUsername());
		
		Logger.info("Step 3: Enter a valid password in the \"Password\" textbox.");
		loginPage.enterPassword(account.getPassword());

		Logger.info("Step 4: Click the \"Login\" button.");
		loginPage.clickLoginBtn();

		Logger.verify("VP. The Login page should still be displayed.");
		assertHelper.assertTrue(loginPage.isDisplayed(), "The Login page should still be displayed.");

		Logger.verify("VP. The error \"Invalid credentials\" message should be displayed.");
		assertHelper.assertEquals(loginPage.getErrorMessage(), Constant.ERROR_LOGIN_MESSAGE, "The error \"Invalid credentials\" message should be displayed.");

	}

	@Test
	@Description("Test case 03: User can log out of the account successfully.")
	public void TC03() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click on the user's avatar.");
		pimPage.clickUserDropdown();

		Logger.info("Step 2: Click on the \"Logout\" button.");
		loginPage = pimPage.clickLogoutBtn();

		Logger.verify("VP. The Login page should still be displayed.");
		assertHelper.assertTrue(loginPage.isDisplayed(), "The Login page should still be displayed.");

	}

}
