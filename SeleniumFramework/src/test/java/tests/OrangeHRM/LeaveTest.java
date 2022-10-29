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
import pages.OrangeHRM.ApplyLeavePage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewLeaveListPage;
import tests.TestBase;

public class LeaveTest extends TestBase {

	@Test
	@Description("Test case 11: User can apply leave.")
	public void TC11() {

		AssertHelper assertHelper = new AssertHelper();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		LoginPage loginPage = LoginPage.newInstance();
		ViewLeaveListPage viewLeaveListPage = ViewLeaveListPage.newInstance();
		ApplyLeavePage applyLeavePage = ApplyLeavePage.newInstance();
		
		Account account = new Account(UserRoleOption.ADMIN);
		LeaveTicket leaveTicket = new LeaveTicket("leaveTicket");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Select Leave -> Apply.");
		viewLeaveListPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.LEAVE);
		applyLeavePage = viewLeaveListPage.clickTopBarMenuItem(TopBarMenuItem.APPLY);

		Logger.info("Step 2: Enter all required information.");
		applyLeavePage.enterAllRequiredOnApplyLeaveForm(leaveTicket);

		Logger.info("Step 3: Click Apply button.");
		applyLeavePage.clickApplyButton().waitForLoadingIconDisappear();

		Logger.verify("VP. Success popup is displyed.");
		assertHelper.assertTrue(applyLeavePage.isSavedSuccessMessageDisplayed(), "Success popup is displyed.");
		
	}

}
