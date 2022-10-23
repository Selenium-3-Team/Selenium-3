package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInfoRecordColumnTitle;
import dataType.OrangeHRM.EmployeeInformationForm;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
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
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click \"Add\" or \"Add Employee\" button.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		pimPage.waitForPageLoad();

		Logger.verify("VP. User is redirected to \"Add Employee\" page.");
		assertHelper.assertTrue(pimPage.isTopBarMenuItemActived(TopBarMenuItem.ADD_EMPLOYEE), "User is redirected to \"Add Employee\" page.");

		Logger.info("Step 2: Enter all required information and turn off \"Create Login Details\" option.");
		pimPage.enterAllRequiredOnAddEmployeeForm(employee);
		
		Logger.info("Step 3: Click \"Save\".");
		pimPage.clickSaveButton();
		pimPage.waitForEmployeeDetailsDisplayed();
		
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
		pimPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Step 1: Filter by \"Employment Status\".");
		pimPage.selectOptionOnEmployeeInformation(EmployeeInformationForm.EMPLOYEE_STATUS_DROPDOWN, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSearchBtn();
		
		Logger.verify("VP. Employee's name are displayed in the alphabet by default.");
		assertHelper.assertTrue(pimPage.isAllCellValueOfColumnSortedAlphabet(EmployeeInfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME), "Employee's name are not displayed in the alphabet.");
	
	}
	
	@Test
	@Description("Test case 08: User can modify employee's Employment Status successful.")
	public void TC08() {
	
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Precondition 2: Create successfully a new employee.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		pimPage.enterAllRequiredOnAddEmployeeForm(employee).clickSaveButton();
		pimPage.waitForEmployeeDetailsDisplayed().clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		pimPage.enterValueToEmployeeInformationTextbox(EmployeeInformationForm.EMPLOYEE_ID_TEXTBOX, employee.getId()).clickSearchButton();
		String actualResult = pimPage.getCellValueOfColumn(EmployeeInfoRecordColumnTitle.EMPLOYMENT_STATUS);
		
		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		pimPage.clickEditEmployeeInfoRecord(employee).waitForEmployeeDetailsDisplayed();
		
		Logger.verify("VP. User is redirected to the employee personal details.");
		assertHelper.assertTrue(pimPage.isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab.PERSONAL_DETAILS), "User is not redirected to the employee personal details.");
		assertHelper.assertTrue(pimPage.isEmployeeNameDisplayed(employee), "User is redirected wrong selected employee.");
		
		Logger.info("Step 2: Select \"Job\" employee information type.");
		pimPage.selectEmployeeInfoTypeTab(EmployeeInformationTypeTab.JOB);
		
		Logger.info("Step 3: Modify Employement Status");
		pimPage.selectOptionOnEmployeeInformation(EmployeeInformationForm.EMPLOYEE_STATUS_DROPDOWN, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSaveButton();
		String expectedResult = pimPage.getSelectedOptionOnViewJobDetail(EmployeeInformationForm.EMPLOYEE_STATUS_DROPDOWN.getValue());
		
		Logger.verify("VP. Employee's job Employment Status is changed successfull.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		pimPage.enterValueToEmployeeInformationTextbox(EmployeeInformationForm.EMPLOYEE_ID_TEXTBOX, employee.getId()).clickSearchButton();
		assertHelper.assertNotEquals(actualResult, expectedResult, "Employee's job Employment Status is not changed successfull.");
		
	}
	
	@Test
	@Description("Test case 09: User cannot upload a valid format image by over the size of 1MB.")
	public void TC09() {
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		pimPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Precondition 2: Create successfully a new employee.");
		pimPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		pimPage.enterAllRequiredOnAddEmployeeForm(employee).clickSaveButton();
		pimPage.waitForEmployeeDetailsDisplayed().clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		pimPage.enterValueToEmployeeInformationTextbox(EmployeeInformationForm.EMPLOYEE_ID_TEXTBOX, employee.getId()).clickSearchButton();
		
		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		pimPage.clickEditEmployeeInfoRecord(employee).waitForEmployeeDetailsDisplayed();
		
		Logger.verify("VP. User is redirected to the employee personal details.");
		assertHelper.assertTrue(pimPage.isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab.PERSONAL_DETAILS), "User is not redirected to the employee personal details.");
		assertHelper.assertTrue(pimPage.isEmployeeNameDisplayed(employee), "User is redirected wrong selected employee.");
		
		Logger.info("Step 2: Click on the photograph at the top left corner of the page.");
		pimPage.clickEmployeeImageOnViewPersonalDetails();
		
		Logger.verify("VP. The photograph screen is displayed.");
		assertHelper.assertTrue(pimPage.isChangeProfilePictureTitleDisplayed(), "The photograph screen is not displayed.");
		
		Logger.info("Step 3: Click on plus button.");
		pimPage.clickAddPicture();
		
		Logger.info("Step 4: Choose the image file type jpg/ png/ gif that is more than 1MB.");
		
		Logger.verify("VP. The error message is displayed that \"Attchment Size Exceeded\".");
		
	}
	
}
