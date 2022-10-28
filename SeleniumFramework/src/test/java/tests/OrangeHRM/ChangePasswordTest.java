package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.UpdatePasswordPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import tests.TestBase;
import utils.constant.Constant;

public class ChangePasswordTest extends TestBase {
	
	@Test
	@Description("Test case 04: User can change passwords successfully.")
	public void TC04() {

		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		UpdatePasswordPage updatePasswordPage = UpdatePasswordPage.newInstance();
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		String newPassword = Constant.STRONG_PASSWORD;
		
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click on the user's avatar.");
		viewEmployeeListPage.clickUserDropdown();

		Logger.info("Step 2: Select the \"Change Password\" option.");
		updatePasswordPage = viewEmployeeListPage.selectChangePasswordOption();

		Logger.info("Step 3: Enter the current password in the \"Current Password\" textbox.");
		updatePasswordPage.enterValueToTextboxOption(TextBoxTitle.CURRENT_PASSWORD, account.getPassword());

		Logger.info("Step 4: Enter the new password <newPassword> in the \"Password\" textbox.");
		updatePasswordPage.enterValueToTextboxOption(TextBoxTitle.NEW_PASSWORD, newPassword);

		Logger.info("Step 5: Enter the <newPassword> in the \"Confirm Password\" textbox.");
		updatePasswordPage.enterValueToTextboxOption(TextBoxTitle.CONFIRM_PASSWORD, newPassword);

		Logger.info("Step 6: Click on the \"Save\" button.");
		updatePasswordPage.clickSaveButton();
		
		Logger.verify("VP. The success \"Successfully Saved\" message should be displayed.");
		assertHelper.assertTrue(updatePasswordPage.isToastSuccessMessageDisplayed(), "The success \"Successfully Saved\" message should be displayed.");
		
		Logger.info("Step 7: Click on the \"Logout\" button.");
		loginPage = updatePasswordPage.logoutOrangeHRM().waitForPageLoad();
		
		Logger.info("Step 8: Login with a valid account.");
		account.setPassword(newPassword);
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		
		Logger.verify("VP. The PIM page should be displayed.");
		assertHelper.assertTrue(viewEmployeeListPage.isHeaderTitleDisplayed(LeftPanelMenuItem.PIM), "The PIM page should be displayed.");

	}
	
}
