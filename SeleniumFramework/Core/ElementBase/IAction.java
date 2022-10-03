package ElementBase;

public interface IAction {
	
	/**
	 * Element's actions
	 */
	void click();
	
	void clickByJS();
	
	void doubleClick();
	
	void sendKeys(CharSequence... keysToEnter);
	
	void clear();
	
	void submit();
	
	void focus();
	
	void hover();
	
	void moveToElement();
	
	void scrollIntoView();
	
	void select();
	
	void check();
	
	void uncheck();

}
