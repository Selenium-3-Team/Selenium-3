package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.CustomField;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.InfoRecordColumnTitle;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
import dataType.OrangeHRM.EmploymentStatusOption;
import dataType.OrangeHRM.FrameTitle;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import tests.TestBase;
import utils.constant.Constant;

public class PIMTest extends TestBase {
	
	@Test
	@Description("Test case 06: User can add a new employee successful without Create Login Details.")
	public void TC06() {

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click \"Add\" or \"Add Employee\" button.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);

		Logger.verify("VP. User is redirected to \"Add Employee\" page.");
		assertHelper.assertTrue(addEmployeePage.isTopBarMenuItemActived(TopBarMenuItem.ADD_EMPLOYEE), "User is redirected to \"Add Employee\" page.");

		Logger.info("Step 2: Enter all required information and turn off \"Create Login Details\" option.");
		addEmployeePage.enterAllRequiredOnAddEmployeeForm(employee);
		
		Logger.info("Step 3: Click \"Save\".");
		viewPersonalDetailsPage = addEmployeePage.clickSaveButton();
		
		Logger.verify("VP. A new employee is added successful.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeNameDisplayed(employee), "A new employee is added successful.");
		
		Logger.info("Step 4: Verify new added employee is displayed in Employee list.");
		viewEmployeeListPage = pimPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		viewEmployeeListPage.waitForLoadingIconDisappear();
		viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton();
		
		assertHelper.assertTrue(viewEmployeeListPage.isInfoRecordDisplayedInRecordList(employee), "A new employee is added successful in Employee list.");

	}

	@Test
	@Description("Test case 07: User can search employees with Employment Status successful.")
	public void TC07() {
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Step 1: Filter by \"Employment Status\".");
		viewEmployeeListPage.selectOption(DropdownTitle.EMPLOYEE_STATUS, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSearchButton();
		
		Logger.verify("VP. Employee's name are displayed in the alphabet by default.");
		assertHelper.assertTrue(viewEmployeeListPage.isAllCellValueOfColumnSortedAlphabet(InfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME), "Employee's name are not displayed in the alphabet.");
	
	}
	
	@Test
	@Description("Test case 08: User can modify employee's Employment Status successful.")
	public void TC08() {
	
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Precondition 2: Create successfully a new employee.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.enterAllRequiredOnAddEmployeeForm(employee).clickSaveButton();
		viewEmployeeListPage = viewPersonalDetailsPage.waitForLoadingIconDisappear().clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton();
		String actualResult = viewEmployeeListPage.getCellValueOfColumn(InfoRecordColumnTitle.EMPLOYMENT_STATUS.getValue());
		
		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		viewPersonalDetailsPage = viewEmployeeListPage.clickEditInfoRecord(employee);
		
		Logger.verify("VP. User is redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab.PERSONAL_DETAILS), "User is not redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeNameDisplayed(employee), "User is redirected wrong selected employee.");
		
		Logger.info("Step 2: Select \"Job\" employee information type.");
		viewJobDetailsPage = viewPersonalDetailsPage.selectEmployeeInfoType(EmployeeInformationTypeTab.JOB);
		
		Logger.info("Step 3: Modify Employement Status");
		viewJobDetailsPage.selectOption(DropdownTitle.EMPLOYEE_STATUS, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSaveButton().waitForLoadingIconDisappear();
		String expectedResult = viewJobDetailsPage.getSelectedOption(DropdownTitle.EMPLOYEE_STATUS);
		
		Logger.verify("VP. Employee's job Employment Status is changed successfull.");
		viewEmployeeListPage = viewJobDetailsPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton();
		assertHelper.assertNotEquals(actualResult, expectedResult, "Employee's job Employment Status is not changed successfull.");
		
	}
	
	@Test
	@Description("Test case 09: User cannot upload a valid format image by over the size of 1MB.")
	public void TC09() {
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Precondition 2: Create successfully a new employee.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.enterAllRequiredOnAddEmployeeForm(employee).clickSaveButton();
		viewEmployeeListPage = viewPersonalDetailsPage.waitForLoadingIconDisappear().clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton();
		
		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		viewPersonalDetailsPage = viewEmployeeListPage.clickEditInfoRecord(employee);
		
		Logger.verify("VP. User is redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab.PERSONAL_DETAILS), "User is not redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeNameDisplayed(employee), "User is redirected wrong selected employee.");
		
		Logger.info("Step 2: Click on the photograph at the top left corner of the page.");
		viewPhotographPage = viewPersonalDetailsPage.clickEmployeeImage();
		
		Logger.verify("VP. The photograph screen is displayed.");
		assertHelper.assertEquals(viewPhotographPage.getFrameTitleDisplayed(), FrameTitle.CHANGE_PROFILE_PICTURE.getValue(), "The photograph screen is not displayed.");
		
		Logger.info("Step 3: Click on plus button.");
		viewPhotographPage.clickAddImage();
		
		Logger.info("Step 4: Choose the image file type jpg/ png/ gif that is more than 1MB.");
		viewPhotographPage.uploadImage(Constant.IMAGE_DATA);
		
		Logger.verify("VP. The error message is displayed that \"Attchment Size Exceeded\".");
		assertHelper.assertTrue(viewPhotographPage.isLblErrorMessageDisplayed(), "The error message is not displayed as expected.");
		assertHelper.assertEquals(viewPhotographPage.getLblErrorMessage(), Constant.UPLOAD_IMAGE_ERROR_MESSAGE, "The error message is not displayed as \"Attchment Size Exceeded\".");
		
	}
	
	@Test
	@Description("Test case 10: User can add a custom field for employee successful.")
	public void TC10() {
		
		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		CustomField customField = new CustomField("customField");
		
		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);
		
		Logger.info("Step 1: Select Configuration -> Custom Fields.");
		listCustomFieldsPage = viewEmployeeListPage.clickTopBarMenuItem("Configuration/Custom Fields", "/");

		Logger.verify("VP. Custom Field page is displayed");
		assertHelper.assertEquals(listCustomFieldsPage.getFrameTitleDisplayed(), FrameTitle.CUSTOM_FIELDS.getValue(), "Custom Field page is not displayed.");
		
		Logger.info("Step 2: Click add button");
		saveCustomFieldsPage = listCustomFieldsPage.clickAddButton();
		
		Logger.info("Step 3: Enter all required information (Field Name, Screen, Type)");
		saveCustomFieldsPage.fillInformationForAddCustomField(customField.getFieldName(), customField.getScreen(), customField.getType(), customField.getSelectOptions());
		
		Logger.info("Step 4: Click save button");
		listCustomFieldsPage = saveCustomFieldsPage.clickSaveButton();
		
		Logger.verify("VP. New custom field is displayed in the custom field list.");
		assertHelper.assertTrue(listCustomFieldsPage.isInfoRecordDisplayedInRecordList(customField), "New custom field is not displayed in the custom field list.");
		
		Logger.info("Post-condition: Delete added custom field.");
		listCustomFieldsPage.clickDeleteInfoRecord(customField).waitForLoadingIconDisappear();
		
	}
	
}
