package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.ContactDetails;
import dataObject.OrangeHRM.CustomField;
import dataObject.OrangeHRM.Employee;
import dataObject.OrangeHRM.PersonalInfo;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
import dataType.OrangeHRM.EmploymentStatusOption;
import dataType.OrangeHRM.FrameTitle;
import dataType.OrangeHRM.InfoRecordColumnTitle;
import dataType.OrangeHRM.State;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.AddEmployeePage;
import pages.OrangeHRM.ListCustomFieldsPage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.SaveCustomFieldsPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewJobDetailsPage;
import pages.OrangeHRM.ViewPersonalDetailsPage;
import pages.OrangeHRM.ViewPhotographPage;
import tests.TestBase;
import utils.constant.Constant;

public class PIMTest extends TestBase {

	@Test
	@Description("Test case 06: User can add a new employee successful without Create Login Details.")
	public void TC06() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
		ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();

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

		Logger.info("Step 3: Click \"Save\" button.");
		viewPersonalDetailsPage = addEmployeePage.clickSaveButton();

		Logger.verify("VP. A new employee is added successful.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeNameDisplayed(employee), "A new employee is added successful.");

		Logger.info("Step 4: Verify new added employee is displayed in Employee list.");
		viewEmployeeListPage = viewPersonalDetailsPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		boolean isInfoRecordDisplayedInRecordList = viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton()
							.waitForLoadingIconDisappear().isInfoRecordDisplayedInRecordList(employee);
		assertHelper.assertTrue(isInfoRecordDisplayedInRecordList, "A new employee is added successful in Employee list.");

	}

	@Test
	@Description("Test case 07: User can search employees with Employment Status successful.")
	public void TC07() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();

		Account account = new Account(UserRoleOption.ADMIN);

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Filter by \"Employment Status\".");
		viewEmployeeListPage.selectOption(DropdownTitle.EMPLOYEE_STATUS, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSearchButton().waitForLoadingIconDisappear();

		Logger.verify("VP. Employee's name are displayed in the alphabet by default.");
		assertHelper.assertTrue(viewEmployeeListPage.isAllCellValueOfColumnSortedAlphabet(InfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME), "Employee's name are not displayed in the alphabet.");

	}

	@Test
	@Description("Test case 08: User can modify employee's Employment Status successful.")
	public void TC08() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
		ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();
		ViewJobDetailsPage viewJobDetailsPage = ViewJobDetailsPage.newInstance();

		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();

		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Precondition 2: Create successfully a new employee.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.addEmployeeWithoutCreateLogilnDetails(employee);
		viewEmployeeListPage = viewPersonalDetailsPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		String actualResult = viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton()
													.waitForLoadingIconDisappear().getCellValueOfColumn(InfoRecordColumnTitle.EMPLOYMENT_STATUS.getValue());

		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		viewPersonalDetailsPage = viewEmployeeListPage.clickEditInfoRecord(employee);

		Logger.verify("VP. User is redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab.PERSONAL_DETAILS), "User is not redirected to the employee personal details.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isEmployeeNameDisplayed(employee), "User is redirected wrong selected employee.");

		Logger.info("Step 2: Select \"Job\" employee information type.");
		viewJobDetailsPage = viewPersonalDetailsPage.selectEmployeeInfoType(EmployeeInformationTypeTab.JOB);

		Logger.info("Step 3: Modify Employement Status");
		String expectedResult = viewJobDetailsPage.selectOption(DropdownTitle.EMPLOYEE_STATUS, EmploymentStatusOption.FULL_TIME_CONTRACT.getValue()).clickSaveButton()
							.waitForLoadingIconDisappear().getSelectedOption(DropdownTitle.EMPLOYEE_STATUS);

		Logger.verify("VP. Employee's job Employment Status is changed successfull.");
		assertHelper.assertNotEquals(actualResult, expectedResult, "Employee's job Employment Status is not changed successfull.");

	}

	@Test
	@Description("Test case 09: User cannot upload a valid format image by over the size of 1MB.")
	public void TC09() {

		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
		ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();
		ViewPhotographPage viewPhotographPage = ViewPhotographPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Employee employee = new Employee();

		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Precondition 2: Create successfully a new employee.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.addEmployeeWithoutCreateLogilnDetails(employee);

		Logger.info("Step 1: Select \"Edit\" button for the employee.");
		viewEmployeeListPage = viewPersonalDetailsPage.clickTopBarMenuItem(TopBarMenuItem.EMPLOYEE_LIST);
		viewPersonalDetailsPage = viewEmployeeListPage.enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, employee.getEmloyeeId()).clickSearchButton()
														.waitForLoadingIconDisappear().clickEditInfoRecord(employee);;

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
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ListCustomFieldsPage listCustomFieldsPage = ListCustomFieldsPage.newInstance();
		SaveCustomFieldsPage saveCustomFieldsPage = SaveCustomFieldsPage.newInstance();

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

	@Test
	@Description("Test case 16: User can edit full name successfully.")
	public void TC16() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
		ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();

		Account account = new Account(UserRoleOption.ADMIN);
		Account newAccount = new Account();
		Employee employee = new Employee();
		PersonalInfo personalInfo = new PersonalInfo();
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Precondition 2: Add a employee with Create login details.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.addEmployeeWithCreateLogilnDetails(employee, newAccount, State.ENABLED);

		Logger.info("Precondition 3: Logout OrangeHRM.");
		loginPage = viewPersonalDetailsPage.logoutOrangeHRM();

		Logger.info("Precondition 4: Login successfully with a new account.");
		viewPersonalDetailsPage = loginPage.loginOrangeHRM(newAccount);
		viewPersonalDetailsPage.waitForLoadingIconDisappear();

		Logger.info("Step 1: Enter new first name in the first name textbox.");
		viewPersonalDetailsPage.enterFirstName(personalInfo.getFirstName());

		Logger.info("Step 2: Enter new middle name in the middle name textbox.");
		viewPersonalDetailsPage.enterMiddleName(personalInfo.getMiddleName());

		Logger.info("Step 3: Enter new last name in the last name textbox.");
		viewPersonalDetailsPage.enterLastName(personalInfo.getLastName());

		Logger.info("Step 4: Click Save button.");
		viewPersonalDetailsPage.clickSaveButton();
		
		Logger.verify("VP. The \"Successful Updated\" message should be displayed.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isUpdatedSuccessMessageDisplayed(), "The \"Successful Updated\" message is not displayed.");
				
	}
	
	@Test
	@Description("Test case 17: User can update the contact details.")
	public void TC17() {

		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = LoginPage.newInstance();
		ContactDetails contactDetails = new ContactDetails("contact-details-test-data-1");
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AddEmployeePage addEmployeePage = AddEmployeePage.newInstance();
		ViewPersonalDetailsPage viewPersonalDetailsPage = ViewPersonalDetailsPage.newInstance();

		Account account = new Account(UserRoleOption.ADMIN);
		Account newAccount = new Account();
		Employee employee = new Employee();
		
		
		Logger.info("Precondition 1: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Precondition 2: Add a employee with Create login details.");
		addEmployeePage = viewEmployeeListPage.clickTopBarMenuItem(TopBarMenuItem.ADD_EMPLOYEE);
		viewPersonalDetailsPage = addEmployeePage.addEmployeeWithCreateLogilnDetails(employee, newAccount, State.ENABLED);

		Logger.info("Precondition 3: Logout OrangeHRM.");
		loginPage = viewPersonalDetailsPage.logoutOrangeHRM();

		Logger.info("Precondition 4: Login successfully with a new account.");
		viewPersonalDetailsPage = loginPage.loginOrangeHRM(newAccount);
		viewPersonalDetailsPage.waitForLoadingIconDisappear();
		
		Logger.info("Step 1: Select My info > Personal Details > Contact Details.");
		viewPersonalDetailsPage.selectEmployeeInfoType(EmployeeInformationTypeTab.CONTACT_DETAILS);

		Logger.info("Step 2: Enter appropropriate values in the contact details form.");
		viewPersonalDetailsPage.fillContactDetailsForm(contactDetails);

		Logger.info("Step 3: Click Save button.");
		viewPersonalDetailsPage.clickSaveButton();
		
		Logger.verify("VP. The \"Successful Updated\" message should be displayed.");
		assertHelper.assertTrue(viewPersonalDetailsPage.isUpdatedSuccessMessageDisplayed(), "The \"Successful Updated\" message is not displayed.");
				
	}
}
