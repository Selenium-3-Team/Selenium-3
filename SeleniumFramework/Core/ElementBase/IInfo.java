package ElementBase;

import java.util.List;

public interface IInfo {

	/**
	 * Element's statuses
	 */
	
	boolean isDisplayed();
	
	boolean isDisplayed(int timeOutInSeconds);
	
	boolean isEnabled();
	
	boolean isEnabled(int timeOutInSeconds);
	
	boolean isSelected();
	
	boolean isSelected(int timeOutInSeconds);
	
	String getCssValue(String propertyName);
	
	String getAttribute(String attributeName);
	
	String getText();
	
	List<String> getAllTexts();
	
	String getValue();
	
	String getTagName();
	
	int getSize();
	
}
