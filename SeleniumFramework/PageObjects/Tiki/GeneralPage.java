package Tiki;

import java.util.List;

import org.javatuples.Pair;

import Common.Utilities;
import Constant.Constant;
import ElementBase.Element;
import ElementSetting.FindBy;
import Enum.Tiki.MenuItem;
import io.qameta.allure.Step;

public class GeneralPage {

	// Header elements
	protected final Element tikiIcon = new Element("//a[@class='tiki-logo']/img");
	protected final Element cartIcon = new Element("//div[@class='cart-wrapper']/img");
	protected final Element breadCrumbItem = new Element("//div[@class='breadcrumb']/a[@class='breadcrumb-item']");

	// Search elements
	protected final Element txtSearch = new Element("xpath=//input[@data-view-id='main_search_form_input']");
	protected final Element btnSearch = new Element("//button[@data-view-id='main_search_form_button']");
	protected final Element iconSearch = new Element("class= icon-search");
	protected final Element iconSearch1 = new Element(new Pair<FindBy, String>(FindBy.CLASS_NAME, "icon-search"));
	
	// Menu elements
	protected final Element btnMenu = new Element("//a[@class='Menu-button']");
	protected final Element menuItem = new Element(
			"//ul[@data-view-id='main_navigation']//span[text()='%s']/parent::a");
	protected final Element subMenuItem = new Element(
			"//div[@data-view-id='main_navigation_item']//span[@data-view-id='main_navigation_sub_item']//a[text()='%s']");

	// Methods

	@Step("Check if search textbox is displayed")
	public boolean isSearchTextBoxDisplayed() {
		return txtSearch.isDisplayed();
	}

	@Step("Get search value from search textbox")
	public String getSearchPlaceHolderText() {
		return txtSearch.getAttribute("placeholder");
	}

	@Step("Check if search button is displayed")
	public boolean isSearchButtonDisplayed() {
		return btnSearch.isDisplayed();
	}

	public GeneralPage enterSearchForm(String product) {
		txtSearch.sendKeys(product);
		return this;
	}

	public GeneralPage submitSearchForm() {
		btnSearch.click();
		return this;
	}

	@Step("Search product name {0}")
	public ProductSearchPage searchProduct(String product) {
		enterSearchForm(product);
		submitSearchForm();
		return new ProductSearchPage().waitForSearchTitleLoading(product);
	}

	@Step("Check if the breadcumb displayed {0}")
	public boolean isBreadCrumbDisplayed(String value) {
		String[] breadCrumbItems = Utilities.splitString(value, Constant.BREAD_CRUMB_ITEM_REGEX);
		List<String> actualBreadCrumbItems = breadCrumbItem.getAllTexts();
		for (int i = 0; i < actualBreadCrumbItems.size(); i++) {
			if (breadCrumbItems[i].trim().equals(actualBreadCrumbItems.get(i).trim())) {
				return true;
			}
		}
		return false;
	}

	public ProductSearchPage selectMenuItem(MenuItem menu, String subMenu) {
		btnMenu.click();
		if (subMenu == null) {
			menuItem.generateDynamic(menu.getMenuItem()).click();
		} else {
			menuItem.generateDynamic(menu.getMenuItem()).moveToElement();
			subMenuItem.generateDynamic(subMenu).click();
		}
		return new ProductSearchPage().waitForLoading();
	}

}
