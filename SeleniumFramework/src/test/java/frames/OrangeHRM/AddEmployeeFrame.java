package frames.OrangeHRM;

import core.element.wrapper.TextBox;

public class AddEmployeeFrame extends GeneralFrame{

	private final TextBox txtFirstName = new TextBox("//input[@name='firstName']");
	private final TextBox txtMiddleName = new TextBox("//input[@name='middleName']");
	private final TextBox txtLastName = new TextBox("//input[@name='lastName']");
	private final TextBox txtEmployeeId = new TextBox("//label[text()='Employee Id']/parent::div/following-sibling::div//input");
	
	public void enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	public void enterMiddleName(String middleName) {
		txtMiddleName.sendKeys(middleName);
	}

	public void enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	public void enterEmployeeId(String id) {
		txtEmployeeId.clearByHotKeys();
		txtEmployeeId.sendKeys(id);
	}
	
}
