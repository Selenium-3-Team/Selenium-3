package Tiki;

import java.util.List;

import Common.RandomHelper;
import Common.Utilities;
import Constant.Constant;
import ElementBase.Element;
import io.qameta.allure.Step;

public class ProductSearchPage extends GeneralPage {

	private final Element searchPageIframe = new Element("//iframe[@id='_hjRemoteVarsFrame']");
	private final Element productSearchTitle = new Element("//div[@class='title']/h1[contains(text(),'%s')]");
	private final Element resultSearchTitle = new Element("//div[@class='title']/h1");
	private final Element productItems = new Element("//a[@class='product-item']//div[@class='name']/span");
	private final Element productNameIndex = new Element("(//a[@class='product-item']//div[@class='name']/span)[%s]");
	private final Element productPriceIndex = new Element("(//a[@class='product-item']//div[contains(@class,'price-discount')]/div[@class='price-discount__price'])[%s]");
	private final Element productName = new Element("//span[contains(text(),'%s')]/ancestor::a[@class='product-item']");
	private final Element lnkFilterOpt = new Element("//div[@data-view-label='%s']//span[text()='%s']");
	private final Element appliedFilterOpt = new Element("//div[@data-view-id='search_selected_filter_container']/p[text()='%s']");
	private final Element lnkServiceFilterOpt = new Element("//label[translate(@data-view-label, '"+Constant.UPPER_CHARS+"', '"+Constant.LOWER_CHARS+"')='%s']");
	private final Element txtPriceFrom = new Element("//div[@data-view-label='Giá']//div[@class='input-group']/input[@placeholder='Giá từ']");
	private final Element txtPriceTo = new Element("//div[@data-view-label='Giá']//div[@class='input-group']/input[@placeholder='Giá đến']");
	private final Element btnFilterPrice = new Element("//div[@data-view-label='Giá']//button[@data-view-id='search_filter_submit_button']");

	// Methods
	public ProductSearchPage waitForLoading() {
		searchPageIframe.waitForPresent(Constant.DEFAULT_TIMEOUT);
		resultSearchTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		return this;
	}
	
	public ProductSearchPage waitForSearchTitleLoading(String product) {
		waitForLoading();
		productSearchTitle.generateDynamic(product).waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		return this;
	}
	
	@Step("Get search result title")
	public String getSearchResultTitle() {
		return resultSearchTitle.getText();
	}
	
	@Step("Get random product")
	public Product getRandomProductInformation() {
		Utilities.waitForPageLoad(Constant.DEFAULT_TIMEOUT);
		productItems.waitForPresent(Constant.DEFAULT_TIMEOUT);
		int totalOfProducts = productItems.getSize();
		int randomProductNumber = RandomHelper.randomNumbers(totalOfProducts);
		if(randomProductNumber==0) {
			randomProductNumber+=1;
		}
		String index = String.valueOf(randomProductNumber);
		Element productName1 = new Element(productNameIndex.getElement().toString(), index);
		Element price1 = new Element(productPriceIndex.getElement().toString(), index);
		String name = productName1.getText();
		String price = price1.getText();
		return new Product(name, price);
	}
	
	@Step("Select product name {0}")
	public ProductPage selectProduct(Product product) {
		productName.generateDynamic(product.getName()).click();
		return new ProductPage().waitForLoading(product);
	}
	
	// Locations, evaluates, prices, brands, colors, suppliers filter
	public ProductSearchPage selectFilterOption(String category, String type) {
		lnkFilterOpt.generateDynamic(category, type).click();
		return this.waitForLoading();
	}
	
	// Tiki services filter
	public ProductSearchPage selectServiceFilterOption(String serviceType) {
		lnkServiceFilterOpt.generateDynamic(serviceType.toLowerCase()).click();
		return this.waitForLoading();
	}
	
	public ProductSearchPage filterProductByRangeOfPrice(int from, int to) {
		txtPriceFrom.sendKeys(String.valueOf(from));
		txtPriceTo.sendKeys(String.valueOf(to));
		btnFilterPrice.click();
		return this.waitForLoading();
	}
	
	public boolean isKeyworkTagDisplayed(String tag) {
		return appliedFilterOpt.generateDynamic(tag).isDisplayed();
	}
	
	public boolean isTextContainedInProductName(String value) {
		productItems.waitForPresent(Constant.DEFAULT_TIMEOUT);
		List<String> productNames = productItems.getAllTexts();
		for(int i = 0; i < productNames.size(); i++) {
			if(productNames.get(i).equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
}
