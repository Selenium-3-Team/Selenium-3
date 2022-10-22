package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformation;
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
		Employee employee = new Employee();

		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();

		Logger.info("Step 1: Select Leave -> Apply.");
		leavePage = pimPage.clickLeaveTabOnLeftPanel().waitForPageLoad();
		leavePage.clickTopBarMenuItem(TopBarMenuItem.APPLY);

		Logger.info("Step 2: Enter all required information.");
		
		Logger.info("Step 3: Click Apply button.");
		
		Logger.verify("VP. Success popup is displyed.");

	}

}
