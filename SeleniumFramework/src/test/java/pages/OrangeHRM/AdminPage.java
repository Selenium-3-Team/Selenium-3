package pages.OrangeHRM;

import core.element.base.Element;
import io.qameta.allure.Step;

public class AdminPage extends GeneralPage {

	// User management
	private final Element lblSystemUsers = new Element("//h5[text()='System Users']");
	private final Element dropUserRole = new Element("//label[text()='User Role']/parent::div/following-sibling::div");
	private final Element lblUserRoleItem = new Element("//div[@class='oxd-select-option']//span[text()='%s']");
	private final Element btnSearch = new Element("//button[@type='submit']");
	private final Element lblNoRecordsFound = new Element("//span[text()='No Records Found']");

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

	@Step("Select item on the User Role dropdown")
	public AdminPage selectItemOnUserRoleDropdown(String item) {
		dropUserRole.click();
		lblUserRoleItem.generateDynamic(item);
		lblUserRoleItem.click();
		return this;
	}

	@Step("Click on search button")
	public AdminPage clickSearchButton() {
		btnSearch.click();
		return this;
	}

	@Step("Check if System Users label is displayed")
	public boolean isNoRecordsFoundLabelNotDisplayed() {
		return !lblNoRecordsFound.isDisplayed();
	}

}
