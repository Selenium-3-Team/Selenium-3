package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;
import utils.constant.Constant;

public class ChangePasswordTest extends TestBase {
	
	@Test
	@Description("Test case 04: User can change passwords successfully.")
	public void TC04() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		String newPassword = Constant.STRONG_PASSWORD;
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click on the user's avatar.");
		pimPage.clickUserDropdown();

		Logger.info("Step 2: Select the \"Change Password\" option.");
		pimPage.selectChangePasswordOption();

		Logger.info("Step 3: Enter the current password in the \"Current Password\" textbox.");
		pimPage.enterCurrentPassword(account.getPassword());

		Logger.info("Step 4: Enter the new password <newPassword> in the \"Password\" textbox.");
		pimPage.enterNewPassword(newPassword);

		Logger.info("Step 5: Enter the <newPassword> in the \"Confirm Password\" textbox.");
		pimPage.enterConfirmPassword(newPassword);

		Logger.info("Step 6: Click on the \"Save\" button.");
		pimPage.clickSaveBtn();
		
		Logger.verify("VP. The success \"Successfully Saved\" message should be displayed.");
		assertHelper.assertTrue(pimPage.isToastSuccessMessageDisplayed(), "The success \"Successfully Saved\" message should be displayed.");
		
		Logger.info("Step 7: Click on the \"Logout\" button.");
		loginPage = pimPage.logoutOrangeHRM().waitForPageLoad();
		
		Logger.info("Step 8: Login with a valid account.");
		account.setPassword(newPassword);
		pimPage = loginPage.loginOrangeHRM(account);
		
		Logger.verify("VP. The PIM page should be displayed.");
		assertHelper.assertTrue(pimPage.isHeaderTitleDisplayed(LeftPanelMenuItem.PIM), "The PIM page should be displayed.");

	}
}
