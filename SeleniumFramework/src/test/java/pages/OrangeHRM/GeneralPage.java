package pages.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TopBarMenuItem;
import frames.OrangeHRM.ViewSystemUsersFrame;
import io.qameta.allure.Step;

public class GeneralPage {

	protected final Label lblCopyRight = new Label("//p[contains(@class,'copyright') and contains(.,'%s')]");
	protected final Label lblVersion = new Label("//p[contains(@class,'copyright') and text()='%s']");
	// Topbar header
	protected final Label lblHeaderTitle = new Label("//div[@class='oxd-topbar-header-title']//h6[contains(.,'%s')]");
	protected final Element drpUser = new Element("//li[@class='oxd-userdropdown']");
	protected final Button btnLogout = new Button("//a[@role='menuitem' and text()='Logout']");
	// Topbar menu
	protected final Element topBarMenuItem = new Element(
			"//nav[@aria-label='Topbar Menu']//li[normalize-space(.)='%s']");
	// Left panel
	protected final Element leftPanel = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");
	// Toast message
	protected final Label lblToastSuccessMessage = new Label(
			"//p[contains(@class,'toast-message') and .='Successfully Saved']/preceding-sibling::p[contains(@class, 'toast-title') and .='Success']/parent::div[contains(@class, 'toast-content--success')]");

	protected final ViewSystemUsersFrame viewSystemUsersFrame = new ViewSystemUsersFrame();

	private static GeneralPage instance;

	public static GeneralPage newInstance() {
		if (GeneralPage.instance == null)
			GeneralPage.instance = new GeneralPage();
		return GeneralPage.instance;
	}

	@Step("Check if text CopyRight {0} and {1} is displayed")
	public boolean isCopyRightTextDisplayed(String companyName, String appVersion) {
		lblCopyRight.generateDynamic(companyName);
		lblVersion.generateDynamic(appVersion);
		return lblCopyRight.isDisplayed() && lblVersion.isDisplayed();
	}

	@Step("Check if header title {0} is displayed")
	public boolean isHeaderTitleDisplayed(LeftPanelMenuItem item) {
		lblHeaderTitle.generateDynamic(item.getValue());
		return lblHeaderTitle.isDisplayed();
	}

	@Step("Check if top bar menu is displayed")
	public boolean isTopBarMenuItemDisplayed(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		return topBarMenuItem.isDisplayed();
	}

	@Step("Check if top menu button is actived")
	public boolean isTopBarMenuItemActived(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		return topBarMenuItem.isAttributeValueDisplayed("class", "--visited");
	}

	@Step("Check if toast Success message is displayed")
	public boolean isToastSuccessMessageDisplayed() {
		return lblToastSuccessMessage.isDisplayed();
	}

	@Step("Click User dropdown")
	public GeneralPage clickUserDropdown() {
		drpUser.click();
		return this;
	}

	@Step("Click Logout button")
	public LoginPage clickLogoutBtn() {
		btnLogout.click();
		return new LoginPage();
	}

	@Step("Logout OrangeHRM page")
	public LoginPage logoutOrangeHRM() {
		clickUserDropdown();
		clickLogoutBtn();
		return new LoginPage();
	}

	@Step("Click tab {0} on Left panel")
	public GeneralPage clickTabOnLeftPanel(String tabName) {
		leftPanel.generateDynamic(tabName);
		leftPanel.click();
		return this;
	}

	@Step("Click Admin tab on Left panel")
	public AdminPage clickAdminTabOnLeftPanel() {
		clickTabOnLeftPanel(LeftPanelMenuItem.ADMIN.getValue());
		return new AdminPage();
	}

	@Step("Click on top bar menu item")
	public GeneralPage clickTopBarMenuItem(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		topBarMenuItem.click();
		return this;
	}
	
	@Step("Click Leave tab on Left panel")
	public LeavePage clickLeaveTabOnLeftPanel() {
		clickTabOnLeftPanel(LeftPanelMenuItem.LEAVE.getValue());
		return new LeavePage();
	}

}
