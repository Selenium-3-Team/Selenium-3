package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Button extends Element{

	public Button(By locator) {
		super(locator);
	}
	
	public Button(String locator) {
		super(locator);
	}
	
	public Button(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public Button(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public Button(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public Button(FindBy by, String value) {
		super(by, value);
	}
	
	public Button(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public Button(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public Button(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public Button generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void click() {
		super.click();
	}
	
	public void clickByJS() {
		super.clickByJS();
	}
	
	public void doubleClick() {
		super.doubleClick();
	}
	
	public void submit() {
		super.submit();
	}
	
}
