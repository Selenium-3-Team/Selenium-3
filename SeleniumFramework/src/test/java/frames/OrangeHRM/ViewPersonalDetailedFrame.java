package frames.OrangeHRM;

import core.element.wrapper.Label;
import dataObject.OrangeHRM.Employee;
import utils.constant.Constant;

public class ViewPersonalDetailedFrame extends GeneralFrame{

	private final Label lblEmployeeName = new Label("//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");
	private final Label lblPersonalDetails = new Label("//h6[text()='Personal Details']");
	
	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}
	
	public void waitForEmployeeDetailsDisplayed() {
		lblPersonalDetails.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
	}
	
}
