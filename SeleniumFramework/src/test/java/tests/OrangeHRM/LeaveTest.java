package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;

public class LeaveTest extends TestBase {

	@Test
	@Description("Test case 11: User can apply leave.")
	public void TC11() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		LeaveTicket leaveTicket = new LeaveTicket("leaveTicket");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Select Leave -> Apply.");
		viewLeaveListPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.LEAVE);
		applyLeavePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.APPLY);

		Logger.info("Step 2: Enter all required information.");
		applyLeavePage.enterAllRequiredOnApplyLeaveForm(leaveTicket);

		Logger.info("Step 3: Click Apply button.");
		applyLeavePage.clickApplyButton().waitForLoadingIconDisappear();

		Logger.verify("VP. Success popup is displyed.");
		assertHelper.assertTrue(applyLeavePage.isToastSuccessMessageDisplayed(), "Success popup is displyed.");

		Logger.info("Post-condition: Delete leave ticket.");
		viewMyLeaveListPage = applyLeavePage.clickTopBarMenuItem(TopBarMenuItem.MYLEAVE);
		viewMyLeaveListPage.cancelLeaveTicket(leaveTicket);

	}

}
