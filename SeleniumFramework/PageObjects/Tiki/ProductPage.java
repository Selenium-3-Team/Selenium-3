package Tiki;

import Constant.Constant;
import ElementWrapper.Element;
import io.qameta.allure.Step;

public class ProductPage extends GeneralPage {

	private final Element breadCrumbProductName = new Element("//div[@class='breadcrumb']/a[@class='breadcrumb-item']/span[contains(text(),'%s')]");
	private final Element lblProductName = new Element("//h1[@class='title']");
	private final Element lblProductPrice = new Element("((//div[contains(@class,'product-price')]/div[contains(@class,'current-price')]) | (//div[@class='flash-sale-price']/span))");
	
	public ProductPage waitForLoading(Product product) {
		breadCrumbProductName.getDynamicElement(product.getName()).waitForVisibility(Constant.DEFAULT_TIMEOUT);
		lblProductName.waitForVisibility(Constant.DEFAULT_TIMEOUT);
		lblProductPrice.waitForVisibility(Constant.DEFAULT_TIMEOUT);
		return this;
	}
	
	@Step("Get product name")
	public String getProductName() {
		return lblProductName.getText();
	}
	
	@Step("Get product price")
	public String getProductPrice() {
		return lblProductPrice.getText();
	}
	
}
