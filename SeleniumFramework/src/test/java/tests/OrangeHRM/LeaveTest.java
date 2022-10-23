package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Description;
import tests.TestBase;

public class LeaveTest extends TestBase {

	@Test
	@Description("Test case 11: User can apply leave.")
	public void TC11() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		LeaveTicket leaveTicket = new LeaveTicket("leaveTicket");

		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();

		Logger.info("Step 1: Select Leave -> Apply.");
		leavePage = pimPage.clickLeaveTabOnLeftPanel().waitForPageLoad();
		leavePage.clickTopBarMenuItem(TopBarMenuItem.APPLY);

		Logger.info("Step 2: Enter all required information.");
		leavePage.enterAllRequiredOnApplyLeaveForm(leaveTicket);

		Logger.info("Step 3: Click Apply button.");
		leavePage.clickApplyButton();

		Logger.verify("VP. Success popup is displyed.");
		assertHelper.assertTrue(leavePage.isToastSuccessMessageDisplayed(), "Success popup is displyed.");
	}

}
