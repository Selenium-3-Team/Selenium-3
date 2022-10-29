package pages.OrangeHRM;

import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class PIMPage extends GeneralPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, PIMPage.class);

	private final TextBox txtFirstName = new TextBox(locator.getLocator("txtFirstName"));
	private final TextBox txtMiddleName = new TextBox(locator.getLocator("txtMiddleName"));
	private final TextBox txtLastName = new TextBox(locator.getLocator("txtLastName"));

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Click Search button on PIM page")
	public PIMPage clickSearchBtn() {
		clickSearchButton();
		waitForLoadingIconDisappear();
		return this;
	}

	@Step("Enter firstName {0} on Add Employee form")
	public PIMPage enterFirstName(String firstName) {
		txtFirstName.clearByHotKeys();
		txtFirstName.sendKeys(firstName);
		return this;
	}

	@Step("Enter middleName {0} on Add Employee form")
	public PIMPage enterMiddleName(String middleName) {
		txtMiddleName.clearByHotKeys();
		txtMiddleName.sendKeys(middleName);
		return this;
	}

	@Step("Enter lastName {0} on Add Employee form")
	public PIMPage enterLastName(String lastName) {
		txtLastName.clearByHotKeys();
		txtLastName.sendKeys(lastName);
		return this;
	}

	@Step("Enter employeeId {0} on Add Employee form")
	public PIMPage enterEmployeeId(String id) {
		enterValueToTextboxOption(TextBoxTitle.EMPLOYEE_ID, id);
		return this;
	}

}
