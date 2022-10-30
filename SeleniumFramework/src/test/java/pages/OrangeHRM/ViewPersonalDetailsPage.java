package pages.OrangeHRM;

import core.element.wrapper.Label;
import core.element.wrapper.Tab;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.ContactDetails;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.EmployeeInformationTypeTab;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class ViewPersonalDetailsPage extends PIMPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewPersonalDetailsPage.class);

	private final Label lblEmployeeName = new Label(locator.getLocator("lblEmployeeName"));
	private final Tab employeeInfoTypeTab = new Tab(locator.getLocator("employeeInfoTypeTab"));

	private static ViewPersonalDetailsPage instance;

	public static ViewPersonalDetailsPage newInstance() {
		if (ViewPersonalDetailsPage.instance == null)
			ViewPersonalDetailsPage.instance = new ViewPersonalDetailsPage();
		return ViewPersonalDetailsPage.instance;
	}

	@Step("Check employee name is displayed correctly on Personal details form")
	public boolean isEmployeeNameDisplayed(Employee employee) {
		lblEmployeeName.generateDynamic(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
		return lblEmployeeName.isDisplayed();
	}

	@Step("Check employee info tab option is displayed on Personal details form")
	public boolean isEmployeeInfoTypeTabDisplayed(EmployeeInformationTypeTab employeeInformationTypeTab) {
		employeeInfoTypeTab.generateDynamic(employeeInformationTypeTab.getValue());
		return employeeInfoTypeTab.isDisplayed();
	}

	@Step("Select employee info tab option on Personal details form")
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

	@Step("Check employee info tab option is displayed on Personal details form")
	public ViewPersonalDetailsPage fillContactDetailsForm(ContactDetails contactDetails) {
		enterValueToTextboxOption(TextBoxTitle.WORK_EMAIL, contactDetails.getWorkEmail());
		enterValueToTextboxOption(TextBoxTitle.OTHER_EMAIL, contactDetails.getOtherEmail());
		enterValueToTextboxOption(TextBoxTitle.STREET_1, contactDetails.getStreet1());
		enterValueToTextboxOption(TextBoxTitle.STREET_2, contactDetails.getStreet2());
		enterValueToTextboxOption(TextBoxTitle.CITY, contactDetails.getCity());
		enterValueToTextboxOption(TextBoxTitle.PROVINCE, contactDetails.getProvince());
		enterValueToTextboxOption(TextBoxTitle.POSTAL_CODE, contactDetails.getPostalCode());
		enterValueToTextboxOption(TextBoxTitle.HOME, contactDetails.getHomePhone());
		enterValueToTextboxOption(TextBoxTitle.MOBILE, contactDetails.getMobilePhone());
		enterValueToTextboxOption(TextBoxTitle.WORK, contactDetails.getWorkPhone());
		selectOption(DropdownTitle.COUNTRY, contactDetails.getCountry());
		return this;
	}

}
