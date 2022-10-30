package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.ApplyLeavePage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewLeaveListPage;
import pages.OrangeHRM.ViewMyLeaveListPage;
import tests.TestBase;

public class LeaveTest extends TestBase {

	@Test
	@Description("Test case 11: User can apply leave.")
	public void TC11() {

		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		LoginPage loginPage = LoginPage.newInstance();
		ViewLeaveListPage viewLeaveListPage = ViewLeaveListPage.newInstance();
		ApplyLeavePage applyLeavePage = ApplyLeavePage.newInstance();
		ViewMyLeaveListPage viewMyLeaveListPage = ViewMyLeaveListPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
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
		assertHelper.assertTrue(applyLeavePage.isToastSuccessMessageDisplayed(), "Success popup is displyed.");

		Logger.info("Post-condition: Delete leave ticket.");
		viewMyLeaveListPage = applyLeavePage.clickTopBarMenuItem(TopBarMenuItem.MYLEAVE);
		viewMyLeaveListPage.cancelLeaveTicket(leaveTicket);

	}

	@Test
	@Description("Test case 12: User can cacel a leave ticket.")
	public void TC12() {

		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		LoginPage loginPage = LoginPage.newInstance();
		ViewLeaveListPage viewLeaveListPage = ViewLeaveListPage.newInstance();
		ApplyLeavePage applyLeavePage = ApplyLeavePage.newInstance();
		ViewMyLeaveListPage viewMyLeaveListPage = ViewMyLeaveListPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		LeaveTicket leaveTicket = new LeaveTicket("leaveTicket");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Submit a leave ticket.");
		viewLeaveListPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.LEAVE);
		applyLeavePage = viewLeaveListPage.clickTopBarMenuItem(TopBarMenuItem.APPLY);
		applyLeavePage.enterAllRequiredOnApplyLeaveForm(leaveTicket);
		applyLeavePage.clickApplyButton().waitForLoadingIconDisappear();

		Logger.info("Step 2: Select Leave -> My Leave.");
		viewMyLeaveListPage = applyLeavePage.clickTopBarMenuItem(TopBarMenuItem.MYLEAVE);

		Logger.info("Step 3: Enter from date on From date textbox.");
		viewMyLeaveListPage.enterValueToTextboxOption(TextBoxTitle.FROMDATE, leaveTicket.getFromDate());

		Logger.info("Step 4: Enter to date on To date textbox.");
		viewMyLeaveListPage.enterValueToTextboxOption(TextBoxTitle.TODATE, leaveTicket.getToDate());

		Logger.info("Step 5: Click search button.");
		viewMyLeaveListPage.clickSearchButton().waitForLoadingIconDisappear();

		Logger.info("Step 6: Click cacel button on created ticket .");
		viewMyLeaveListPage.cancelLeaveTicket(leaveTicket);

		Logger.verify("VP. Update success popup is displyed.");
		assertHelper.assertTrue(viewMyLeaveListPage.isUpdatedSuccessMessageDisplayed(), "Update success popup is displyed.");

	}

}
