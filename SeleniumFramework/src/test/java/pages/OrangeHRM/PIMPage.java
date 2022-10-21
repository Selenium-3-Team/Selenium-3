package pages.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.element.wrapper.TextBox;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformation;
import io.qameta.allure.Step;

public class PIMPage extends GeneralPage {

	// Add Employee form
	private final TextBox txtFirstName = new TextBox("//input[@name='firstName']");
	private final TextBox txtMiddleName = new TextBox("//input[@name='middleName']");
	private final TextBox txtLastName = new TextBox("//input[@name='lastName']");
	private final TextBox txtEmployeeId = new TextBox("//label[text()='Employee Id']/parent::div/following-sibling::div//input");
	private final Button btnSave = new Button("//button[@type='submit' and normalize-space(.)='Save']");
	// Employee form
	private final Label lblEmployeeName = new Label("//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");
	// Employee Information
	private final Element employeeInfoRow = new Element("//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");
	private final TextBox txtInEmployeeInformation = new TextBox("//label[normalize-space(text())='%s']/parent::div/following-sibling::div//input");
	private final Button btnSeach = new Button("//button[@type='submit' and normalize-space(.)='Search']");
	
	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Enter firstname {0}")
	public void enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	@Step("Enter middlename {0}")
	public void enterMiddleName(String middleName) {
		txtMiddleName.sendKeys(middleName);
	}

	@Step("Enter lastname {0}")
	public void enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	@Step("Enter id {0}")
	public void enterEmployeeId(String id) {
		txtEmployeeId.clearByHotKeys();
		txtEmployeeId.sendKeys(id);
	}

	@Step("Click Save button")
	public void clickSaveBtn() {
		btnSave.click();
	}
	
	@Step("Click Search button")
	public PIMPage clickSearchBtn() {
		btnSeach.click();
		return this;
	}
	
	@Step("Enter all required information")
	public void enterAllRequiredOnAddEmployeeForm(String firstName, String middleName, String lastName, String employeeId) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		enterEmployeeId(employeeId);
	}
	
	@Step("Enter all required information")
	public void enterAllRequiredOnAddEmployeeForm(Employee employee) {
		enterAllRequiredOnAddEmployeeForm(employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getId());
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

	@Step("Check if the employee is displayed after add employee")
	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}
	
	@Step("Enter value {0} to Employee Information Textbox")
	public PIMPage enterValueToEmployeeInformationTextbox(EmployeeInformation itemName, String value) {
		txtInEmployeeInformation.generateDynamic(itemName.getName());
		txtInEmployeeInformation.sendKeys(value);
		return this;
	}
	
	@Step("Check if new added employee is displayed in Employee list")
	public boolean isEmployeeDisplayedInEmployeeList(Employee employee) {
		employeeInfoRow.generateDynamic(employee.getId(), String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		return employeeInfoRow.isDisplayed();
	}
}
