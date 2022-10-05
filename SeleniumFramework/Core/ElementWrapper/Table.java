package ElementWrapper;

import org.openqa.selenium.By;

import ElementBase.Element;
import ElementSetting.FindBy;

public class Table extends Element {

	public Table(By locator) {
		super(locator);
	}
	
	public Table(String locator) {
		super(locator);
	}
	
	public Table(Element parentElement, String locator) {
		super(parentElement, locator);
	}
	
	public Table(String locator, Object... arguments) {
		super(locator, arguments);
	}
	
	public Table(Element parentElement, String locator, Object... arguments) {
		super(parentElement, locator, arguments);
	}

	public Table(FindBy by, String value) {
		super(by, value);
	}
	
	public Table(Element parentElement, FindBy by, String value) {
		super(parentElement, by, value);
	}

	public Table(FindBy by, String value, Object... arguments) {
		super(by, value, arguments);
	}
	
	public Table(Element parentElement, FindBy by, String value, Object... arguments) {
		super(parentElement, by, value, arguments);
	}
	
	public Table generateDynamic(Object... arguments)
	{
		super.generateDynamic(arguments);
		return this;
	}
	
	public void select() {
		super.select();
	}
	
}
