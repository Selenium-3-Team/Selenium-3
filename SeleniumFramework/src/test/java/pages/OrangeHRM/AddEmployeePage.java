package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.State;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class AddEmployeePage extends PIMPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, AddEmployeePage.class);

	private final Button tglCreateLoginDetails = new Button(locator.getLocator("tglCreateLoginDetails"));
	private final Button rdStatus = new Button(locator.getLocator("rdStatus"));

	private static AddEmployeePage instance;

	public static AddEmployeePage newInstance() {
		if (AddEmployeePage.instance == null)
			AddEmployeePage.instance = new AddEmployeePage();
		return AddEmployeePage.instance;
	}

	@Step("Change toogle state on Add Employee form")
	public AddEmployeePage changeToggleCreateLoginDetails(State state) {
		String currentState = tglCreateLoginDetails.getAttribute("class");
		if (!currentState.equalsIgnoreCase(state.getValue()))
			tglCreateLoginDetails.click();
		return this;
	}

	@Step("Select account status")
	public AddEmployeePage selectAccountStatus(State state) {
		rdStatus.generateDynamic(state.getValue());
		rdStatus.click();
		return this;
	}

	@Step("Enter {0} for firstName, {1} for middleName, {2} for lastName, {3} for employeeId on Add Employee form")
	private AddEmployeePage enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName, String employeeId) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		enterEmployeeId(employeeId);
		return this;
	}

	public AddEmployeePage enterAllRequiredOnAddEmployeeForm(Employee employee) {
		return enterAllRequiredOnAddEmployeeForm(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getEmloyeeId());
	}

	private ViewPersonalDetailsPage addEmployeeWithoutCreateLoginDetails(String firstName, String middleName, String lastName, String employeeId) {
		enterAllRequiredOnAddEmployeeForm(firstName, middleName, lastName, employeeId);
		return clickSaveButton();
	}

	@Step("Add employee without Create Login Details")
	public ViewPersonalDetailsPage addEmployeeWithoutCreateLogilnDetails(Employee employee) {
		return addEmployeeWithoutCreateLoginDetails(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getEmloyeeId());
	}

	@Step("Add employee with Create Login Details")
	public ViewPersonalDetailsPage addEmployeeWithCreateLogilnDetails(Employee employee, Account account, State state) {
		enterAllRequiredOnAddEmployeeForm(employee);
		changeToggleCreateLoginDetails(State.FOCUS);
		enterValueToTextboxOption(TextBoxTitle.USERNAME, account.getUsername());
		enterValueToTextboxOption(TextBoxTitle.PASSWORD, account.getPassword());
		enterValueToTextboxOption(TextBoxTitle.CONFIRM_PASSWORD, account.getPassword());
		selectAccountStatus(state);
		return clickSaveButton();
	}
	
	@Step("Click Save button")
	public ViewPersonalDetailsPage clickSaveButton() {
		super.clickSaveButton();
		waitForLoadingIconDisappear();
		return new ViewPersonalDetailsPage();
	}

}
