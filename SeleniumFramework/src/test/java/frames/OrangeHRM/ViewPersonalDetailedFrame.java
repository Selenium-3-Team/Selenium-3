package frames.OrangeHRM;

import core.element.wrapper.Label;
import core.element.wrapper.Tab;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformationTypeTab;

public class ViewPersonalDetailedFrame extends GeneralFrame {

	private final Label lblEmployeeName = new Label("//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");
	private final Tab employeeInfoTypeTab = new Tab("//div[@role='tab']/a[text()='%s']"); 

	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}
	
	public boolean isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab employeeInformationTypeTab) {
		employeeInfoTypeTab.generateDynamic(employeeInformationTypeTab.getValue());
		return employeeInfoTypeTab.isDisplayed();
	}
	
	public void selectEmployeeInfoTypeTab(EmployeeInformationTypeTab employeeInformationTypeTab) {
		employeeInfoTypeTab.generateDynamic(employeeInformationTypeTab.getValue());
		employeeInfoTypeTab.click();
	}

}
