package ElementBase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ElementSetting.FindBy;



public interface IFinder extends ILocator{
	
	/**
	 * Element's finder
	 */
	
	WebElement getElement();
	
	WebElement getChildElement(By locator);
	
	WebElement getChildElement(FindBy by, String value);
	
	WebElement getChildElement(String locator);
	
	List<WebElement> getElements();
	
	List<WebElement> getChildElements(By locator);
	
	List<WebElement> getChildElements(FindBy by, String value);
	
	List<WebElement> getChildElements(String locator);
}
