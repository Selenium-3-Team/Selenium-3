package pages.OrangeHRM;

import core.element.base.Element;
import dataType.OrangeHRM.CopyRight;
import dataType.OrangeHRM.LeftPanelMenuItem;
import io.qameta.allure.Step;

public class GeneralPage {

	private final Element txtCopyRight = new Element("//p[contains(@class,'copyright') and contains(.,'%s')]");
	private final Element txtVersion = new Element("//p[contains(@class,'copyright') and text()='%s']");
	// Topbar header
	private final Element txtHeaderTitle = new Element("//div[@class='oxd-topbar-header-title']//h6[contains(.,'%s')]");
	private final Element lblUserDropdown = new Element("//li[@class='oxd-userdropdown']");
	private final Element btnLogout = new Element("//a[@role='menuitem' and text()='Logout']");
	// Topbar menu
	private final String sTopbarMenu = "//nav[@aria-label='Topbar Menu']";
	private final Element btnTopMenu = new Element(sTopbarMenu + "//a[text()='%s']");
	private final Element btnTopMenuActived = new Element(
			sTopbarMenu + "//li[contains(@class,'visited')]//a[text()='%s']");
	private final Element dropTopMenu = new Element(sTopbarMenu + "//span[contains(text(),'%s')]");
	private final Element dropTopMenuActived = new Element(
			sTopbarMenu + "//li[contains(@class,'visited')]//span[contains(text(),'%s')]");
	// Side panel
	private final Element tabsidePanel = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");

	private static GeneralPage instance;

	public static GeneralPage newInstance() {
		if (GeneralPage.instance == null)
			GeneralPage.instance = new GeneralPage();
		return GeneralPage.instance;
	}

	@Step("Check if text CopyRight is displayed")
	public boolean isCopyRightTextDisplayed() {
		txtCopyRight.generateDynamic(CopyRight.COMPANY.getData());
		txtVersion.generateDynamic(CopyRight.VERSION.getData());
		return (txtCopyRight.isDisplayed() && txtVersion.isDisplayed());
	}

	@Step("Check if header title is displayed")
	public boolean isHeaderTitleDisplayed(String title) {
		txtHeaderTitle.generateDynamic(title);
		return txtHeaderTitle.isDisplayed();
	}

	@Step("Check if top menu button is displayed")
	public boolean isTopMenuButtonDisplayed(String item) {
		btnTopMenu.generateDynamic(item);
		return btnTopMenu.isDisplayed();
	}

	@Step("Check if top menu dropdown is displayed")
	public boolean isTopMenuDropdownDisplayed(String item) {
		dropTopMenu.generateDynamic(item);
		return dropTopMenu.isDisplayed();
	}

	@Step("Check if top menu button is actived")
	public boolean isTopMenuButtonActived(String item) {
		btnTopMenuActived.generateDynamic(item);
		return btnTopMenuActived.isDisplayed();
	}

	@Step("Check if top menu dropdown is actived")
	public boolean isTopMenuDropdownActived(String item) {
		dropTopMenuActived.generateDynamic(item);
		return dropTopMenuActived.isDisplayed();
	}

	@Step("Logout OrangeHRM page")
	public LoginPage logoutOrangeHRM() {
		lblUserDropdown.click();
		btnLogout.click();
		return new LoginPage();
	}

	@Step("Goto Admin page")
	public AdminPage gotoAdminPage() {
		tabsidePanel.generateDynamic(LeftPanelMenuItem.ADMIN.getName());
		tabsidePanel.click();
		return new AdminPage();
	}

	@Step("Click on top menu button")
	public void clickTopMenuButton(String item) {
		btnTopMenu.generateDynamic(item);
		btnTopMenu.click();
	}
}
