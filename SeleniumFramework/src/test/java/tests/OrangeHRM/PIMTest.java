package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInfoRecordColumnTitle;
import dataType.OrangeHRM.EmployeeInformationForm;
import dataType.OrangeHRM.EmploymentStatusOption;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;

public class PIMTest extends TestBase {
	
	@Test
	@Description("Test case 06: User can add a new employee successful without Create Login Details.")
	public void TC06() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
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
		pimPage.enterValueToEmployeeInformationTextbox(EmployeeInformationForm.EMPLOYEE_ID_TEXTBOX, employee.getId()).clickSearchBtn();
		assertHelper.assertTrue(pimPage.isEmployeeDisplayedInEmployeeList(employee), "A new employee is added successful in Employee list.");

	}

	@Test
	@Description("Test case 07: User can search employees with Employment Status successful.")
	public void TC07() {
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Logger.info("Precondition: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account).waitForPageLoad();
		
		Logger.info("Step 1: Filter by \"Employment Status\".");
		pimPage.selectOptionOnEmployeeInformation(EmployeeInformationForm.EMPLOYEE_STATUS_DROPDOWN, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSearchBtn();
		
		Logger.verify("VP. Employee's name are displayed in the alphabet by default.");
		assertHelper.assertTrue(pimPage.isAllCellValueOfColumnSortedAlphabet(EmployeeInfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME), "Employee's name are not displayed in the alphabet.");
	
	}
	
}
