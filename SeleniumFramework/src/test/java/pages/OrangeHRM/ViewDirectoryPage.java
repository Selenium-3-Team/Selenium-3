package pages.OrangeHRM;

import java.util.ArrayList;
import java.util.List;

import core.element.wrapper.Label;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Employee;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class ViewDirectoryPage extends GeneralPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewDirectoryPage.class);

	private final Label lblCardHeader = new Label(locator.getLocator("lblCardHeader"));
	private final Label lblCardSubtitle = new Label(locator.getLocator("lblCardSubtitle"));
	private final Label lblCardDescription = new Label(locator.getLocator("lblCardDescription"));

	private static ViewDirectoryPage instance;

	public static ViewDirectoryPage newInstance() {
		if (ViewDirectoryPage.instance == null)
			ViewDirectoryPage.instance = new ViewDirectoryPage();
		return ViewDirectoryPage.instance;
	}

	@Step("Get employee info in Directory data")
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

	@Step("Check employee list displayed correctly according to search value {0}")
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
