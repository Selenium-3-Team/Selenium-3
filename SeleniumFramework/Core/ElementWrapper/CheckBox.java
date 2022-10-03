package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class CheckBox extends Element{

	public CheckBox(By locator) {
		super(locator);
	}
	
	public CheckBox(String locator) {
		super(locator);
	}
	
	public CheckBox(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public CheckBox(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public CheckBox(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public CheckBox(FindBy by, String value) {
		super(by, value);
	}
	
	public CheckBox(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public CheckBox(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public CheckBox(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public CheckBox generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void check() {
		super.check();
	}
	
	public void uncheck() {
		super.uncheck();
	}
}
