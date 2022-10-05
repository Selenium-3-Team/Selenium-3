package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Image extends Element{

	public Image(By locator) {
		super(locator);
	}
	
	public Image(String locator) {
		super(locator);
	}
	
	public Image(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public Image(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public Image(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public Image(FindBy by, String value) {
		super(by, value);
	}
	
	public Image(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public Image(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public Image(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public Image generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void click() {
		super.click();
	}
	
	public String getSource() {
		   return getElement().getAttribute("src");
	}
	
}
