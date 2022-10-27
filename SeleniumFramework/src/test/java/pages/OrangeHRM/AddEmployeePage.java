package pages.OrangeHRM;

import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Employee;
import utils.constant.Constant;

public class AddEmployeePage extends PIMPage{
	
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
	
	
	public AddEmployeePage enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
		return this;
	}

	public AddEmployeePage enterMiddleName(String middleName) {
		txtMiddleName.sendKeys(middleName);
		return this;
	}

	public AddEmployeePage enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
		return this;
	}

	public AddEmployeePage enterEmployeeId(String id) {
		txtEmployeeId.clearByHotKeys();
		txtEmployeeId.sendKeys(id);
		return this;
	}
	
	public AddEmployeePage enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName,
			String employeeId) {
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

	public AddEmployeePage addEmployeeWithoutCreateLoginDetails(Employee employee) {
		return addEmployeeWithoutCreateLoginDetails(employee.getFirstName(), employee.getMiddleName(),
				employee.getLastName(), employee.getEmloyeeId());
	}
	
	public ViewPersonalDetailsPage clickSaveButton() {
		btnSave.click();
		waitForLoadingIconDisappear();
		return new ViewPersonalDetailsPage();
	}
	
}
