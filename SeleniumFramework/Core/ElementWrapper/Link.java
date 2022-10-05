package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Link extends Element{

	public Link(By locator) {
		super(locator);
	}
	
	public Link(String locator) {
		super(locator);
	}
	
	public Link(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public Link(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public Link(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public Link(FindBy by, String value) {
		super(by, value);
	}
	
	public Link(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public Link(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public Link(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public Link generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void click() {
		super.click();
	}
	
	public void clickByJS() {
		super.clickByJS();
	}
	
	public String getReference() {
		return getElement().getAttribute("href");
	}
	
}
