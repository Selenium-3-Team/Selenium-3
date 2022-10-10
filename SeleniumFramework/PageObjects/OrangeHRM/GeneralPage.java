package OrangeHRM;

import core.element.base.Element;
import io.qameta.allure.Step;

public class GeneralPage {

	private final Element txtCopyRight = new Element("//p[contains(@class,'copyright')]//a");
	
	@Step("Check if text CopyRight is displayed")
	public boolean isCopyRightTextDisplayed() {
		return txtCopyRight.isDisplayed();
	}
}
