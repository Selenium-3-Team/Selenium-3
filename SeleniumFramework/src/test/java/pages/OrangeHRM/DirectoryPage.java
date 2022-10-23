package pages.OrangeHRM;

import dataType.OrangeHRM.DirectoryForm;
import dataType.OrangeHRM.LeftPanelMenuItem;
import frames.OrangeHRM.ViewDirectoryFrame;
import io.qameta.allure.Step;

public class DirectoryPage extends GeneralPage {

	// Frames
	private final ViewDirectoryFrame viewDirectoryFrame = new ViewDirectoryFrame();

	private static DirectoryPage instance;

	public static DirectoryPage newInstance() {
		if (DirectoryPage.instance == null)
			DirectoryPage.instance = new DirectoryPage();
		return DirectoryPage.instance;
	}

	@Step("Wait for Directory page dipplayed")
	public DirectoryPage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.DIRECTORY.getValue());
		lblHeaderTitle.waitForDisplayed();
		viewDirectoryFrame.waitForLoading();
		waitForLoadingIconDisappear();
		return this;
	}

	@Step("Enter {0} to Employee Name textbox")
	public DirectoryPage enterEmployeeName(String employeeName) {
		viewDirectoryFrame.enterEmployeeName(employeeName);
		return this;
	}

	@Step("Click Search button")
	public DirectoryPage clickSearchBtn() {
		clickSearchButton();
		return this;
	}

	@Step("Click {0} dropdown on the Directory form")
	public DirectoryPage clickDropdownOnDirectoryForm(String drpName) {
		viewDirectoryFrame.clickDropdownOption(drpName);
		return this;
	}

	@Step("Select {0} option")
	public DirectoryPage selectOption(String optionName) {
		viewDirectoryFrame.selectOption(optionName);
		return this;
	}

	@Step("Select the User Role dropdown {0} with value {1}")
	public DirectoryPage selectOptionOnDirectoryForm(DirectoryForm itemName, String value) {
		clickDropdownOnDirectoryForm(itemName.getValue());
		selectOption(value);
		return this;
	}

	@Step("Check if the employee list is displayed correctly according to {0} value")
	public boolean isEmployeeListDisplayedCorrectlyAccordingTo(String valueToSearch) {
		return viewDirectoryFrame.isEmployeeListDisplayedCorrectlyAccordingTo(valueToSearch);
	}

}
