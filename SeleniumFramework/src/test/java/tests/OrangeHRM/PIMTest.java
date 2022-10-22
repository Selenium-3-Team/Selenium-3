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

public class PIMTest extends TestBase {
	
	@Test
	@Description("Test case 06: User can add a new employee successful without Create Login Details.")
	public void TC06() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRole.ADMIN);
		Employee employee = new Employee();

		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();

		Logger.info("Step 1: Click \"Add\" or \"Add Employee\" button.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		pimPage.waitForPageLoad();

		Logger.verify("VP. User is redirected to \"Add Employee\" page.");
		assertHelper.assertTrue(pimPage.isTopBarMenuItemActived(TopBarMenuItem.ADD_EMPLOYEE), "User is redirected to \"Add Employee\" page.");

		Logger.info("Step 2: Enter all required information and turn off \"Create Login Details\" option.");
		pimPage.enterAllRequiredOnAddEmployeeForm(employee);
		
		Logger.info("Step 3: Click \"Save\".");
		pimPage.clickSaveBtn().waitForEmployeeDetailsDisplayed();
		
		Logger.verify("VP. A new employee is added successful.");
		assertHelper.assertTrue(pimPage.isEmployeeNameDisplayed(employee), "A new employee is added successful.");
		
		Logger.info("Step 4: Verify new added employee is displayed in Employee list.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		pimPage.waitForPageLoad();
		pimPage.enterValueToEmployeeInformationTextbox(EmployeeInformation.EMPLOYEE_ID_TEXTBOX, employee.getId()).clickSearchBtn();
		assertHelper.assertTrue(pimPage.isEmployeeDisplayedInEmployeeList(employee), "A new employee is added successful in Employee list.");

	}

}
