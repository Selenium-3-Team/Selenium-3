package pages.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import dataType.OrangeHRM.SystemUsers;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Step;

public class AdminPage extends GeneralPage {

	// User management
	private final Label lblSystemUsers = new Label("//h5[text()='System Users']");
	private final Element drpOnSystemUsers = new Element("//div[.='%s']//following-sibling::div");
	private final Label drpOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	private final Button btnSearch = new Button("//button[@type='submit']");
	private final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");

	private static AdminPage instance;

	public static AdminPage newInstance() {
		if (AdminPage.instance == null)
			AdminPage.instance = new AdminPage();
		return AdminPage.instance;
	}

	@Step("Check if System Users label is displayed")
	public boolean isSystemUsersLabelDisplayed() {
		return lblSystemUsers.isDisplayed();
	}

	@Step("Click {0} dropdown on the System Users")
	public void clickDropdownOnSystemUsers(String drpName) {
		drpOnSystemUsers.generateDynamic(drpName);
		drpOnSystemUsers.click();
	}

	@Step("Select {0} option")
	public void selectOption(String optionName) {
		drpOption.generateDynamic(optionName);
		drpOption.click();
	}

	@Step("Select option on the User Role dropdown")
	public AdminPage selectOptionOnUserRoleDropdown(UserRole userRole) {
		clickDropdownOnSystemUsers(SystemUsers.USER_ROLE_DROPDOWN.getName());
		selectOption(userRole.getUserRole());
		return this;
	}

	@Step("Click on search button")
	public AdminPage clickSearchButton() {
		btnSearch.click();
		return this;
	}

	@Step("Check if 'No Records Found' label is displayed")
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}

}
