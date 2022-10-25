package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.FrameTitle;
import dataType.OrangeHRM.LeftPanelMenuItem;
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
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click the \"Admin\" tab on the Left Menu.");
		viewSystemUsersPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.ADMIN);
		viewSystemUsersPage.waitForLoadingIconDisappear();

		Logger.verify("VP. The \"User Management\" page should be displayed.");
		assertHelper.assertTrue(viewSystemUsersPage.isTopBarMenuItemActived(TopBarMenuItem.USER_MANAGEMENT), "The \"User Management\" page should be displayed.");

		Logger.verify("VP. The \"System Users\" form should be displayed.");
		assertHelper.assertEquals(viewSystemUsersPage.getFrameTitleDisplayed(), FrameTitle.SYSTEM_USERS.getValue(), "The \"System Users\" form should be displayed.");

		Logger.info("Step 2: Select \"Admin\" on the \"User Role\" dropdown.");
		viewSystemUsersPage.selectOption(DropdownTitle.USER_ROLE, UserRoleOption.ADMIN.getValue());

		Logger.info("Step 3: Click on the \"Search\" button.");
		viewSystemUsersPage.clickSearchButton();

		Logger.verify("VP. At least 1 record should be displayed on the \"Record Found\" form.");
		assertHelper.assertFalse(viewSystemUsersPage.isNoRecordsFoundLabelDisplayed(), "At least 1 record should be displayed on the \\\"Record Found\\\" form.");

	}

}
