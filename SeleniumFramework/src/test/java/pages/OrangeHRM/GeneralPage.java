package pages.OrangeHRM;

import core.element.base.Element;
import dataType.OrangeHRM.CopyRight;
import io.qameta.allure.Step;

public class GeneralPage {

	private final Element txtCopyRight = new Element("//p[contains(@class,'copyright') and contains(.,'%s')]");
	private final Element txtVersion = new Element("//p[contains(@class,'copyright') and text()='%s']");
	
	private static GeneralPage instance;

	public static GeneralPage newInstance() {
		if (GeneralPage.instance == null)
			GeneralPage.instance = new GeneralPage();
		return GeneralPage.instance;
	}

	@Step("Check if text CopyRight is displayed")
	public boolean isCopyRightTextDisplayed() {
		txtCopyRight.generateDynamic(CopyRight.COMPANY.getData());
		txtVersion.generateDynamic(CopyRight.VERSION.getData());
		return (txtCopyRight.isDisplayed() && txtVersion.isDisplayed());
	}
}
