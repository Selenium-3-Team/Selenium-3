package pages.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserDrpOption;
import frames.OrangeHRM.ViewSystemUsersFrame;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class GeneralPage {

	protected final Label lblCopyRight = new Label("//p[contains(@class,'copyright') and contains(.,'%s')]");
	protected final Label lblVersion = new Label("//p[contains(@class,'copyright') and text()='%s']");
	protected final Button btnSearch = new Button("//button[contains(.,'Search')]");
	protected final Button btnAdd = new Button("//button[contains(.,'Add')]");
	protected final Button btnSave = new Button("//button[contains(.,'Save')]");
	protected final Element iconLoading = new Element("//div[@class='oxd-loading-spinner']");
	
	// Topbar header
	protected final Label lblHeaderTitle = new Label("//div[@class='oxd-topbar-header-title']//h6[contains(.,'%s')]");
	protected final Element drpUser = new Element("//li[@class='oxd-userdropdown']");
	protected final Label lblUserDrpOption = new Label("//a[@role='menuitem' and text()='%s']");
	// Topbar menu
	protected final Element topBarMenuItem = new Element("//nav[@aria-label='Topbar Menu']//li[normalize-space(.)='%s']");
	// Left panel
	protected final Element leftPanel = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");
	// Toast message
	protected final Label lblToastSuccessMessage = new Label("//p[contains(@class,'toast-message') and .='Successfully Saved']/preceding-sibling::p[contains(@class, 'toast-title') and .='Success']/parent::div[contains(@class, 'toast-content--success')]");

	protected final ViewSystemUsersFrame viewSystemUsersFrame = new ViewSystemUsersFrame();

	private static GeneralPage instance;

	public static GeneralPage newInstance() {
		if (GeneralPage.instance == null)
			GeneralPage.instance = new GeneralPage();
		return GeneralPage.instance;
	}

	// Click|Select methods
	@Step("Click User dropdown")
	public GeneralPage clickUserDropdown() {
		drpUser.click();
		return this;
	}
	
	@Step("Select {0} option in User dropdown")
	public GeneralPage selectOptionInUserDrp(String optionName) {
		lblUserDrpOption.generateDynamic(optionName);
		lblUserDrpOption.click();
		return this;
	}
	
	public LoginPage selectLogoutOption() {
		selectOptionInUserDrp(UserDrpOption.LOGOUT.getValue());
		return new LoginPage();
	}
	
	public PIMPage selectChangePasswordOption() {
		selectOptionInUserDrp(UserDrpOption.CHANGE_PASSWORD.getValue());
		return new PIMPage();
	}
	
	@Step("Logout OrangeHRM page")
	public LoginPage logoutOrangeHRM() {
		clickUserDropdown();
		selectLogoutOption();
		return new LoginPage();
	}
	
	@Step("Click tab {0} on Left panel")
	public GeneralPage clickTabOnLeftPanel(String tabName) {
		leftPanel.generateDynamic(tabName);
		leftPanel.click();
		return this;
	}
	
	public AdminPage clickAdminTabOnLeftPanel() {
		clickTabOnLeftPanel(LeftPanelMenuItem.ADMIN.getValue());
		return new AdminPage();
	}

	@Step("Click on top bar menu item")
	public GeneralPage clickTopBarMenuItem(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		topBarMenuItem.click();
		waitForLoadingIconDisappear();
		return this;
	}
	
	// Verify methods
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
	
	@Step("Click Search button")
	public GeneralPage clickSearchButton() {
		btnSearch.click();
		waitForLoadingIconDisappear();
		return this;
	}

	@Step("Click Save button")
	public GeneralPage clickSaveButton() {
		btnSave.click();
		return this;
	}
	
	@Step("Wait for loading icon dispappear if it is displayed")
	public GeneralPage waitForLoadingIconDisappear() {
		if (iconLoading.isDisplayed()) {
			iconLoading.waitForNotPresent(Constant.DEFAULT_TIMEOUT);
		}
		return this;
	}
	
}
