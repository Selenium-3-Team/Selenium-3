package pages.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.PIMItem;
import io.qameta.allure.Step;

public class GeneralPage {

	private final Label lblCopyRight = new Label("//p[contains(@class,'copyright') and contains(.,'%s')]");
	private final Label lblVersion = new Label("//p[contains(@class,'copyright') and text()='%s']");
	// Topbar header
	private final Label lblHeaderTitle = new Label("//div[@class='oxd-topbar-header-title']//h6[contains(.,'%s')]");
	private final Element drpUser = new Element("//li[@class='oxd-userdropdown']");
	private final Button btnLogout = new Button("//a[@role='menuitem' and text()='Logout']");
	// Topbar menu
	private final String sTopBarMenu = "//nav[@aria-label='Topbar Menu']";
	private final Button btnTopMenu = new Button(sTopBarMenu + "//li[.='%s']");
	private final Element drpPageOptionItem = new Element(sTopBarMenu + "//span[contains(text(),'%s')]");
	private final Element drpTopMenuActived = new Element(sTopBarMenu + "//li[contains(@class,'visited')]//span[contains(text(),'%s')]");
	// Left panel
	private final Element leftPanel = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");

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
		lblHeaderTitle.generateDynamic(item.getName());
		return lblHeaderTitle.isDisplayed();
	}

	@Step("Check if top menu button is displayed")
	public boolean isTopMenuButtonDisplayed(String item) {
		btnTopMenu.generateDynamic(item);
		return btnTopMenu.isDisplayed();
	}

	@Step("Check if top menu dropdown is displayed")
	public boolean isTopMenuDropdownDisplayed(String item) {
		drpPageOptionItem.generateDynamic(item);
		return drpPageOptionItem.isDisplayed();
	}

	@Step("Check if top menu button is actived")
	public boolean isTopMenuButtonActived(PIMItem item) {
		btnTopMenu.generateDynamic(item.getName());
		return btnTopMenu.isAttributeValueDisplayed("class", "--visited");
	}

	@Step("Check if top menu dropdown is actived")
	public boolean isTopMenuDropdownActived(String item) {
		drpTopMenuActived.generateDynamic(item);
		return drpTopMenuActived.isDisplayed();
	}

	@Step("Logout OrangeHRM page")
	public LoginPage logoutOrangeHRM() {
		drpUser.click();
		btnLogout.click();
		return new LoginPage();
	}

	@Step("Goto Admin page")
	public AdminPage gotoAdminPage() {
		leftPanel.generateDynamic(LeftPanelMenuItem.ADMIN.getName());
		leftPanel.click();
		return new AdminPage();
	}

	@Step("Click on top menu button")
	public void clickTopMenuButton(String item) {
		btnTopMenu.generateDynamic(item);
		btnTopMenu.click();
	}
}
