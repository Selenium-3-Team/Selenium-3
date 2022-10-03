package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class TextBox extends Element{

	public TextBox(By locator) {
		super(locator);
	}
	
	public TextBox(String locator) {
		super(locator);
	}
	
	public TextBox(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public TextBox(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public TextBox(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public TextBox(FindBy by, String value) {
		super(by, value);
	}
	
	public TextBox(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public TextBox(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public TextBox(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public TextBox generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void sendKeys(CharSequence... keysToEnter) {
		super.sendKeys(keysToEnter);
	}
	
	
	public void clear() {
		super.clear();
	}
}
