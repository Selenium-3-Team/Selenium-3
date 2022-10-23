package frames.OrangeHRM;

import java.util.ArrayList;
import java.util.List;

import core.element.wrapper.Label;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.DirectoryForm;

public class ViewDirectoryFrame extends GeneralFrame {

	// Locators
	private final Label lblCardHeader = new Label("//p[contains(@class,'directory-card-header')]");
	private final Label lblCardSubtitle = new Label("//p[contains(@class,'card-subtitle')]");
	private final Label lblCardDescription = new Label("//p[contains(@class,'card-description')]/parent::div");

	public void enterEmployeeName(String employeeName) {
		enterValueToTextboxOption(DirectoryForm.EMPLOYEE_NAME_TEXTBOX.getValue(), employeeName);
	}

	public List<Employee> getEmployeeInfoInDirectoryData() {
		List<Employee> results = new ArrayList<Employee>();
		List<String> employeeNameList = lblCardHeader.getAllTexts();
		List<String> jobTitleList = lblCardSubtitle.getAllTexts();
		List<String> employeeDescriptionList = lblCardDescription.getAllTexts();
		for (int i = 0; i < employeeNameList.size(); i++) {
			String description = employeeDescriptionList.get(i);
			if (!description.isEmpty())
				description = description.split("\n")[1];
			results.add(new Employee(employeeNameList.get(i), jobTitleList.get(i), description));
		}
		return results;
	}

	public boolean isEmployeeListDisplayedCorrectlyAccordingTo(String valueToSearch) {
		boolean result = true;
		List<Employee> employeeList = getEmployeeInfoInDirectoryData();
		for (Employee employee : employeeList) {
			if (employee.getFullName().equalsIgnoreCase(valueToSearch)
					|| employee.getJobTitle().equalsIgnoreCase(valueToSearch)
					|| employee.getOfficeLocation().equalsIgnoreCase(valueToSearch)) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}

}
