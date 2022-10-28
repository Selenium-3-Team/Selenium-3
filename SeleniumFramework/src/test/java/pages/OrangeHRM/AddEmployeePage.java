package pages.OrangeHRM;

import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Employee;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class AddEmployeePage extends PIMPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, AddEmployeePage.class);

	private final TextBox txtFirstName = new TextBox(locator.getLocator("txtFirstName"));
	private final TextBox txtMiddleName = new TextBox(locator.getLocator("txtMiddleName"));
	private final TextBox txtLastName = new TextBox(locator.getLocator("txtLastName"));
	private final TextBox txtEmployeeId = new TextBox(locator.getLocator("txtEmployeeId"));

	private static AddEmployeePage instance;

	public static AddEmployeePage newInstance() {
		if (AddEmployeePage.instance == null)
			AddEmployeePage.instance = new AddEmployeePage();
		return AddEmployeePage.instance;
	}

	@Step("Enter firstName {0} on Add Employee form")
	public AddEmployeePage enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
		return this;
	}

	@Step("Enter middleName {0} on Add Employee form")
	public AddEmployeePage enterMiddleName(String middleName) {
		txtMiddleName.sendKeys(middleName);
		return this;
	}

	@Step("Enter lastName {0} on Add Employee form")
	public AddEmployeePage enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
		return this;
	}

	@Step("Enter employeeId {0} on Add Employee form")
	public AddEmployeePage enterEmployeeId(String id) {
		txtEmployeeId.clearByHotKeys();
		txtEmployeeId.sendKeys(id);
		return this;
	}

	@Step("Enter {0} for firstName, {1} for middleName, {2} for lastName, {3} for employeeId on Add Employee form")
	public AddEmployeePage enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName, String employeeId) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		return enterEmployeeId(employeeId);
	}

	public AddEmployeePage enterAllRequiredOnAddEmployeeForm(Employee employee) {
		return enterAllRequiredOnAddEmployeeForm(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getEmloyeeId());
	}

	public AddEmployeePage addEmployeeWithoutCreateLoginDetails(String firstName, String middleName, String lastName,
			String employeeId) {
		enterAllRequiredOnAddEmployeeForm(firstName, middleName, lastName, employeeId);
		clickSaveButton();
		return this;
	}

	@Step("Add employee without Create Login Details")
	public AddEmployeePage addEmployeeWithoutCreateLogilnDetails(Employee employee) {
		return addEmployeeWithoutCreateLoginDetails(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getEmloyeeId());
	}

	@Step("Click Save button on Add Employee form")
	public ViewPersonalDetailsPage clickSaveButton() {
		btnSave.click();
		waitForLoadingIconDisappear();
		return new ViewPersonalDetailsPage();
	}

}
