package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Label extends Element{

	public Label(By locator) {
		super(locator);
	}
	
	public Label(String locator) {
		super(locator);
	}
	
	public Label(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public Label(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public Label(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public Label(FindBy by, String value) {
		super(by, value);
	}
	
	public Label(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public Label(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public Label(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public Label generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
}
