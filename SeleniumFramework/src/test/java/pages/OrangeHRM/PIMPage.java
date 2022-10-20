package pages.OrangeHRM;

import core.element.base.Element;
import io.qameta.allure.Step;

public class PIMPage extends GeneralPage {

	// Add Employee
	private final Element txtFirstName = new Element("//input[@name='firstName']");
	private final Element txtMiddleName = new Element("//input[@name='middleName']");
	private final Element txtLastName = new Element("//input[@name='lastName']");
	private final Element txtEmployeeId = new Element(
			"//label[text()='Employee Id']/parent::div/following-sibling::div//input");
	private final Element btnSave = new Element("//button[@type='submit']");
	private final Element txtEmployeeName = new Element(
			"//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Add Employee without create login details")
	public PIMPage addEmployeeWithoutCreateLoginDetails(String firstName, String middleName, String lastName,
			String employeeId) {
		txtFirstName.sendKeys(firstName);
		txtMiddleName.sendKeys(middleName);
		txtLastName.sendKeys(lastName);
		txtEmployeeId.clear();
		txtEmployeeId.sendKeys(employeeId);
		btnSave.click();
		return this;
	}

	@Step("Check if the employee is displayed after add employee")
	public boolean isEmployeeNameDisplayed(String name) {
		txtEmployeeName.generateDynamic(name);
		return txtEmployeeName.isDisplayed();
	}
}
