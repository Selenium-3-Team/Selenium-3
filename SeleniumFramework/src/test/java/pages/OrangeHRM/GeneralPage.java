package pages.OrangeHRM;

import core.element.base.Element;
import io.qameta.allure.Step;

public class GeneralPage {

	private final Element txtCopyRight = new Element("//p[contains(@class,'copyright')]//a[text()='%s']");
	
	@Step("Check if text CopyRight is displayed")
	public boolean isCopyRightTextDisplayed() {
		txtCopyRight.generateDynamic("OrangeHRM, Inc");
		return txtCopyRight.isDisplayed();
	}
}
