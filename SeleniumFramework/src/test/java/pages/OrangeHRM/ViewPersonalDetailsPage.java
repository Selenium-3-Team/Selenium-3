package pages.OrangeHRM;

import core.element.wrapper.Label;
import core.element.wrapper.Tab;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformationTypeTab;

public class ViewPersonalDetailsPage extends PIMPage{

	private final Label lblEmployeeName = new Label("//div[@class='orangehrm-edit-employee-name']//h6[text()='%s']");
	private final Tab employeeInfoTypeTab = new Tab("//div[@role='tab']/a[text()='%s']"); 
	
	private static ViewPersonalDetailsPage instance;

	public static ViewPersonalDetailsPage newInstance() {
		if (ViewPersonalDetailsPage.instance == null)
			ViewPersonalDetailsPage.instance = new ViewPersonalDetailsPage();
		return ViewPersonalDetailsPage.instance;
	}
	
	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}

	public boolean isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab employeeInformationTypeTab) {
		employeeInfoTypeTab.generateDynamic(employeeInformationTypeTab.getValue());
		return employeeInfoTypeTab.isDisplayed();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T selectEmployeeInfoType(EmployeeInformationTypeTab employeeInformationTypeTab) {
		employeeInfoTypeTab.generateDynamic(employeeInformationTypeTab.getValue());
		employeeInfoTypeTab.click();
		waitForLoadingIconDisappear();
		switch (employeeInformationTypeTab) {
		case JOB:
			return (T) new ViewJobDetailsPage();
		default:
			return null;
		}
	}
	
}
