package pages.OrangeHRM;

import io.qameta.allure.Step;

public class PIMPage extends GeneralPage {

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	@Step("Click Search button on PIM page")
	public PIMPage clickSearchBtn() {
		clickSearchButton();
		waitForLoadingIconDisappear();
		return this;
	}

}
