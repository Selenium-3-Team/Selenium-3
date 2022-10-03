package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class RadioButton extends Element{
	
	public RadioButton(By locator) {
		super(locator);
	}
	
	public RadioButton(String locator) {
		super(locator);
	}
	
	public RadioButton(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public RadioButton(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public RadioButton(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}
	
	public RadioButton(FindBy by, String value) {
		super(by, value);
	}
	
	public RadioButton(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}
	
	public RadioButton(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public RadioButton(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public RadioButton generateDynamic(Object... arguments) {
		super.generateDynamic(arguments);
		return this;
	}
	
	public void select() {
		super.select();
	}
	
}
