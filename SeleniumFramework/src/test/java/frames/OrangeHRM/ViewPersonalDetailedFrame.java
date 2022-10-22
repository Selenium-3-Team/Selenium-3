package frames.OrangeHRM;

import core.element.wrapper.Label;
import dataObject.OrangeHRM.Employee;

public class ViewPersonalDetailedFrame extends GeneralFrame {

	private final Label lblEmployeeName = new Label("//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");

	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}

}
