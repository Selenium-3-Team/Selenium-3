package pages.OrangeHRM;

import core.element.wrapper.Label;
import core.element.wrapper.Tab;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
import utils.constant.Constant;

public class ViewPersonalDetailsPage extends PIMPage{

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewPersonalDetailsPage.class);
	
	private final Label lblEmployeeName = new Label(locator.getLocator("lblEmployeeName"));
	private final Tab employeeInfoTypeTab = new Tab(locator.getLocator("employeeInfoTypeTab")); 
	
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
