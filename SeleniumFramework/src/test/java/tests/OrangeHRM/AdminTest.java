package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.SystemUsersForm;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;

public class AdminTest extends TestBase {

	@Test
	@Description("Test case 05: Admin User can search employees successfully by \"User Role\".")
	public void TC05() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);

		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click the \"Admin\" tab on the Left Menu.");
		adminPage = pimPage.clickAdminTabOnLeftPanel().waitForPageLoad();

		Logger.verify("VP. The \"User Management\" page should be displayed.");
		assertHelper.assertTrue(adminPage.isTopBarMenuItemActived(TopBarMenuItem.USER_MANAGEMENT), "The \"User Management\" page should be displayed.");

		Logger.verify("VP. The \"System Users\" form should be displayed.");
		assertHelper.assertTrue(adminPage.isSystemUsersLabelDisplayed(), "The \"System Users\" form should be displayed.");

		Logger.info("Step 2: Select \"Admin\" on the \"User Role\" dropdown.");
		adminPage.selectOptionOnSystemUsers(SystemUsersForm.USER_ROLE_DROPDOWN, UserRoleOption.ADMIN.getValue());

		Logger.info("Step 3: Click on the \"Search\" button.");
		adminPage.clickSearchButton();

		Logger.verify("VP. At least 1 record should be displayed on the \"Record Found\" form.");
		assertHelper.assertFalse(adminPage.isNoRecordsFoundLabelDisplayed(), "At least 1 record should be displayed on the \\\"Record Found\\\" form.");

	}

}
