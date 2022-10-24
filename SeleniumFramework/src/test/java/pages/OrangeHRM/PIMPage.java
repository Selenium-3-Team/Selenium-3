package pages.OrangeHRM;

import java.util.List;

import core.helper.RandomHelper;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInfoRecordColumnTitle;
import dataType.OrangeHRM.EmployeeInformationForm;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
import dataType.OrangeHRM.LeftPanelMenuItem;
import frames.OrangeHRM.AddEmployeeFrame;
import frames.OrangeHRM.UpdatePasswordFrame;
import frames.OrangeHRM.ViewEmployeeListFrame;
import frames.OrangeHRM.ViewJobDetailsFrame;
import frames.OrangeHRM.ViewPersonalDetailedFrame;
import frames.OrangeHRM.ViewPhotographFrame;
import io.qameta.allure.Step;

public class PIMPage extends GeneralPage {

	// Frames
	private final ViewPersonalDetailedFrame viewPersonalDetailedFrame = new ViewPersonalDetailedFrame();
	private final ViewEmployeeListFrame viewEmployeeListFrame = new ViewEmployeeListFrame();
	private final AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame();
	private final UpdatePasswordFrame updatePasswordFrame = new UpdatePasswordFrame();
	private final ViewJobDetailsFrame viewJobDetailsFrame = new ViewJobDetailsFrame();
	private final ViewPhotographFrame viewPhotographFrame = new ViewPhotographFrame();

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Wait for PIM page dipplayed")
	public PIMPage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.PIM);
		lblHeaderTitle.waitForDisplayed();
		viewEmployeeListFrame.waitForLoading();
		waitForLoadingIconDisappear();
		return this;
	}

	public PIMPage clickSearchBtn() {
		clickSearchButton();
		waitForLoadingIconDisappear();
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

	@Step("Enter employee id {0}")
	public PIMPage enterEmployeeId(String id) {
		addEmployeeFrame.enterEmployeeId(id);
		return this;
	}

	@Step("Enter all required information on Add Employee form")
	public PIMPage enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName,
			String employeeId) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		return enterEmployeeId(employeeId);
	}

	public PIMPage enterAllRequiredOnAddEmployeeForm(Employee employee) {
		return enterAllRequiredOnAddEmployeeForm(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getId());
	}

	@Step("Add Employee without create login details")
	public PIMPage addEmployeeWithoutCreateLoginDetails(String firstName, String middleName, String lastName,
			String employeeId) {
		enterAllRequiredOnAddEmployeeForm(firstName, middleName, lastName, employeeId);
		clickSaveButton();
		return this;
	}

	public PIMPage addEmployeeWithoutCreateLoginDetails(Employee employee) {
		return addEmployeeWithoutCreateLoginDetails(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getId());
	}

	// Employee Details frame
	@Step("Check if the employee is displayed after add employee")
	public boolean isEmployeeNameDisplayed(Employee employee) {
		return viewPersonalDetailedFrame.isEmployeeNameDisplayed(employee);
	}

	@Step("Wait for employee displayed")
	public PIMPage waitForEmployeeDetailsDisplayed() {
		waitForLoadingIconDisappear();
		viewPersonalDetailedFrame.waitForFrameTitleDisplayed();
		return this;
	}

	@Step("Enter to {0} textbox with value {1}")
	public PIMPage enterValueToEmployeeInformationTextbox(String title, String value) {
		viewEmployeeListFrame.enterValueToEmployeeInformationTextbox(title, value);
		return this;
	}

	public PIMPage enterValueToEmployeeInformationTextbox(EmployeeInformationForm title, String value) {
		return enterValueToEmployeeInformationTextbox(title.getValue(), value);
	}

	@Step("Check if employee is displayed in Employee list")
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
	public PIMPage enterAllRequiredOnUpdatePasswordForm(String currentPassword, String newPassword,
			String confirmPassword) {
		enterCurrentPassword(currentPassword);
		enterNewPassword(newPassword);
		return enterConfirmPassword(confirmPassword);
	}

	// Employee Information form
	@Step("Click {0} dropdown on the System Users")
	public PIMPage clickDropdownOnEmployeeInformation(String drpName) {
		viewEmployeeListFrame.clickDropdownOption(drpName);
		return this;
	}

	@Step("Select {0} option")
	public PIMPage selectOption(String optionName) {
		viewEmployeeListFrame.selectOption(optionName);
		return this;
	}

	public PIMPage selectOptionOnEmployeeInformation(EmployeeInformationForm title, String value) {
		clickDropdownOnEmployeeInformation(title.getValue());
		return selectOption(value);
	}

	public List<String> getAllCellValueOfColumn(EmployeeInfoRecordColumnTitle title) {
		return viewEmployeeListFrame.getAllCellValueOfColumn(title.getValue());
	}

	public boolean isAllCellValueOfColumnSortedAlphabet(EmployeeInfoRecordColumnTitle title) {
		return viewEmployeeListFrame.isAllCellValueOfColumnSortedAlphabet(title.getValue());
	}

	@Step("Get random employee name in Employee List")
	public String getRandomValueOfColumn(EmployeeInfoRecordColumnTitle title) {
		List<String> directoryList = getAllCellValueOfColumn(title);
		String result = "";
		while (result.trim().isEmpty()) {
			int randomIndex = RandomHelper.getRandomNumber(0, directoryList.size());
			result = directoryList.get(randomIndex);
		}
		return result;
	}

	@Step("Get random employee name in Employee List")
	public String getRandomEmployeeNameInList() {
		return getRandomValueOfColumn(EmployeeInfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME);
	}

	@Step("Get random job title in Employee List")
	public String getRandomJobTitleInList() {
		return getRandomValueOfColumn(EmployeeInfoRecordColumnTitle.JOB_TITLE);
	}

	public String getCellValueOfColumn(EmployeeInfoRecordColumnTitle title) {
		return viewEmployeeListFrame.getCellValueOfColumn(title.getValue());
	}

	@Step("Click edit button for the employee {0}")
	public PIMPage clickEditEmployeeInfoRecord(Employee employee) {
		viewEmployeeListFrame.clickEditEmployeeInfoRecord(employee);
		return this;
	}

	@Step("Check if employee information type {0} display")
	public boolean isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab employeeInformationTypeTab) {
		return viewPersonalDetailedFrame.isEmployeeInfoTypeTabDisplayed(employeeInformationTypeTab);
	}

	@Step("Select employee information type {0}")
	public PIMPage selectEmployeeInfoTypeTab(EmployeeInformationTypeTab employeeInformationTypeTab) {
		viewPersonalDetailedFrame.selectEmployeeInfoTypeTab(employeeInformationTypeTab);
		waitForLoadingIconDisappear();
		return this;
	}

	@Step("Get selected value of option {0}")
	public String getSelectedOptionOnViewJobDetail(String drpName) {
		return viewJobDetailsFrame.getSelectedOption(drpName);
	}

	@Step("Click employee image")
	public PIMPage clickEmployeeImageOnViewPersonalDetails() {
		viewPersonalDetailedFrame.clickEmployeeImage();
		return this;
	}

	@Step("Check if change profile picture title displayed")
	public boolean isChangeProfilePictureTitleDisplayed() {
		return viewPhotographFrame.isFrameTitleDisplayed();
	}

	@Step("Click add image button")
	public PIMPage clickAddImageButton() {
		viewPhotographFrame.clickAddImage();
		return this;
	}

	@Step("Upload a image")
	public PIMPage uploadImage(String filePath) {
		viewPhotographFrame.uploadPicture(filePath);
		return this;
	}

	@Step("Get upload image error message")
	public boolean IsUploadImageErrorMessageDisplayed() {
		return viewPhotographFrame.isLblErrorMessageDisplayed();
	}

	@Step("Get upload image error message")
	public String getUploadImageErrorMessage() {
		return viewPhotographFrame.getLblErrorMessage();
	}

}
