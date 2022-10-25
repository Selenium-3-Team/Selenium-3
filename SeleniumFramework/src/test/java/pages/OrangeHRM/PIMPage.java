package pages.OrangeHRM;

public class PIMPage extends GeneralPage {

	private static PIMPage instance;

	public static PIMPage newInstance() {
		if (PIMPage.instance == null)
			PIMPage.instance = new PIMPage();
		return PIMPage.instance;
	}

	public PIMPage clickSearchBtn() {
		clickSearchButton();
		waitForLoadingIconDisappear();
		return this;
	}

}
