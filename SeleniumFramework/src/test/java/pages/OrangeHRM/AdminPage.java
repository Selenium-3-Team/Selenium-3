package pages.OrangeHRM;

import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.SystemUsers;
import frames.OrangeHRM.ViewSystemUsersFrame;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class AdminPage extends GeneralPage {

	// Frames
	private final ViewSystemUsersFrame viewSystemUsersFrame = new ViewSystemUsersFrame();
	
	private static AdminPage instance;

	public static AdminPage newInstance() {
		if (AdminPage.instance == null)
			AdminPage.instance = new AdminPage();
		return AdminPage.instance;
	}
	
	@Step("Wait for Admin page displayed")
	public AdminPage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.ADMIN);
		lblHeaderTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		viewSystemUsersFrame.waitForLoading();
		return this;
	}

	@Step("Click {0} dropdown on the System Users")
	public AdminPage clickDropdownOnSystemUsers(String drpName) {
		viewSystemUsersFrame.clickDropdownOption(drpName);
		return this;
	}
	
	@Step("Select {0} option")
	public AdminPage selectOption(String optionName) {
		viewSystemUsersFrame.selectOption(optionName);
		return this;
	}
	
	@Step("Select option on the User Role dropdown")
	public AdminPage selectOptionOnSystemUsers(SystemUsers systemUsers, String value) {
		clickDropdownOnSystemUsers(systemUsers.getValue());
		selectOption(value);
		return this;
	}

	@Step("Click on search button")
	public AdminPage clickSearchButton() {
		viewSystemUsersFrame.clickSearchButton();
		return this;
	}

	@Step("Check if System Users label is displayed")
	public boolean isSystemUsersLabelDisplayed() {
		return viewSystemUsersFrame.isSystemUsersLabelDisplayed();
	}
	
	@Step("Check if 'No Records Found' label is displayed")
	public boolean isNoRecordsFoundLabelDisplayed() {
		return viewSystemUsersFrame.isNoRecordsFoundLabelDisplayed();
	}

}
