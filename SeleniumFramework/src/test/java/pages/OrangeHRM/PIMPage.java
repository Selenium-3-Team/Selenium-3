package pages.OrangeHRM;

import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformation;
import dataType.OrangeHRM.LeftPanelMenuItem;
import frames.OrangeHRM.AddEmployeeFrame;
import frames.OrangeHRM.UpdatePasswordFrame;
import frames.OrangeHRM.ViewEmployeeListFrame;
import frames.OrangeHRM.ViewPersonalDetailedFrame;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class PIMPage extends GeneralPage {

	// Frames
	private final ViewPersonalDetailedFrame viewPersonalDetailedFrame = new ViewPersonalDetailedFrame();
	private final ViewEmployeeListFrame viewEmployeeListFrame = new ViewEmployeeListFrame();
	private final AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame();
	private final UpdatePasswordFrame updatePasswordFrame = new UpdatePasswordFrame();

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Wait for PIM page dipplayed")
	public PIMPage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.PIM);
		lblHeaderTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		viewEmployeeListFrame.waitForLoading();
		return this;
	}
	
	@Step("Click Search button")
	public PIMPage clickSearchBtn() {
		addEmployeeFrame.clickSearchButton();
		return this;
	}
	
	@Step("Click Save button")
	public PIMPage clickSaveBtn() {
		addEmployeeFrame.clickSaveButton();
		return this;
	}

	// Add Employee form
	@Step("Enter firstname {0}")
	public PIMPage enterFirstName(String firstName) {
		addEmployeeFrame.enterFirstName(firstName);
		return this;
	}

	@Step("Enter middlename {0}")
	public PIMPage enterMiddleName(String middleName) {
		addEmployeeFrame.enterMiddleName(middleName);
		return this;
	}

	@Step("Enter lastname {0}")
	public PIMPage enterLastName(String lastName) {
		addEmployeeFrame.enterLastName(lastName);
		return this;
	}

	@Step("Enter id {0}")
	public void enterEmployeeId(String id) {
		addEmployeeFrame.enterEmployeeId(id);
	}

	@Step("Enter all required information on Add Employee form")
	public PIMPage enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName, String employeeId) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		enterEmployeeId(employeeId);
		return this;
	}

	@Step("Enter all required information on Add Employee form")
	public PIMPage enterAllRequiredOnAddEmployeeForm(Employee employee) {
		return enterAllRequiredOnAddEmployeeForm(employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getId());
	}

	@Step("Add Employee without create login details")
	public PIMPage addEmployeeWithoutCreateLoginDetails(String firstName, String middleName, String lastName, String employeeId) {
		enterAllRequiredOnAddEmployeeForm(firstName, middleName, lastName, employeeId);
		clickSaveBtn();
		return this;
	}

	@Step("Add Employee without create login details")
	public PIMPage addEmployeeWithoutCreateLoginDetails(Employee employee) {
		addEmployeeWithoutCreateLoginDetails(employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getId());
		return this;
	}

	// Employee Details frame
	@Step("Check if the employee is displayed after add employee")
	public boolean isEmployeeNameDisplayed(Employee employee) {
		return viewPersonalDetailedFrame.isEmployeeNameDisplayed(employee);
	}
	
	@Step("Wait for employee displayed")
	public PIMPage waitForEmployeeDetailsDisplayed() {
		viewPersonalDetailedFrame.waitForLoadingIconDisappear();
		viewPersonalDetailedFrame.waitForFrameTitleDisplayed();
		return this;
	}
	
	@Step("Enter to {0} Textbox with value {1}")
	public PIMPage enterValueToEmployeeInformationTextbox(String title, String value) {
		viewEmployeeListFrame.enterValueToEmployeeInformationTextbox(title, value);
		return this;
	}
	
	public PIMPage enterValueToEmployeeInformationTextbox(EmployeeInformation title, String value) {
		return enterValueToEmployeeInformationTextbox(title.getValue(), value);
	}

	@Step("Check if employee {0} is displayed in Employee list")
	public boolean isEmployeeDisplayedInEmployeeList(Employee employee) {
		return viewEmployeeListFrame.isEmployeeDisplayedInEmployeeList(employee);
	}
	
	// Update Password form
	@Step("Enter {0} to Current Password textbox")
	public PIMPage enterCurrentPassword(String currentPassword) {
		updatePasswordFrame.enterCurrentPassword(currentPassword);
		return this;
	}

	@Step("Enter {0} to New Password textbox")
	public PIMPage enterNewPassword(String currentPassword) {
		updatePasswordFrame.enterNewPassword(currentPassword);
		return this;
	}

	@Step("Enter {0} to Confirm Password textbox")
	public PIMPage enterConfirmPassword(String currentPassword) {
		updatePasswordFrame.enterConfirmPassword(currentPassword);
		return this;
	}
	
	@Step("Enter all required information on Update Password form")
	public PIMPage enterAllRequiredOnUpdatePasswordForm(String currentPassword, String newPassword, String confirmPassword) {
		enterCurrentPassword(currentPassword);
		enterNewPassword(newPassword);
		enterConfirmPassword(confirmPassword);
		return this;
	}

}
