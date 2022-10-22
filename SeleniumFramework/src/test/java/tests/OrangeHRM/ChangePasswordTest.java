package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Description;
import tests.TestBase;

public class ChangePasswordTest extends TestBase {
	
	@Test
	@Description("Test case 04: User can change passwords successfully.")
	public void TC04() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();

		Logger.info("Step 1: Click on the user's avatar.");
		pimPage.clickUserDropdown();

		Logger.info("Step 2: Select the \"Change Password\" option.");
		pimPage.selectChangePasswordOption();

		Logger.info("Step 3: Enter the current password in the \"Current Password\" textbox.");
		pimPage.enterCurrentPassword(account.getPassword());

		Logger.info("Step 4: Enter the new password <newPassword> in the \"Password\" textbox.");
//		pimPage.enterNewPassword();

		Logger.info("Step 5: Enter the <newPassword> in the \"Confirm Password\" textbox.");
//		pimPage.enterConfirmPassword();

		Logger.info("Step 6: Click on the \"Save\" button.");
		pimPage.clickSaveBtn();
		
		Logger.verify("VP. The success \"Successfully Saved\" message should be displayed.");
		assertHelper.assertFalse(adminPage.isNoRecordsFoundLabelDisplayed(), "At least 1 record should be displayed on the \"Record Found\" form.");
		
		Logger.info("Step 7: Click on the \"Logout\" button.");
		loginPage = pimPage.selectLogoutOption();
		
		Logger.info("Step 8: Enter a valid username in the \"Username\" textbox.");
		loginPage.enterUsername(account.getUsername());

		Logger.info("Step 9: Enter a valid password <newPassword> in the \"Password\" textbox.");
//		loginPage.enterPassword();
		
		Logger.info("Step 10: Click the \"Login\" button.");
		pimPage.clickSaveBtn();
		
		Logger.verify("VP.  The PIM page should be displayed.");
		assertHelper.assertTrue(pimPage.isHeaderTitleDisplayed(LeftPanelMenuItem.PIM), "The PIM page should be displayed.");

	}
}
