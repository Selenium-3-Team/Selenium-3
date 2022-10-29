package pages.OrangeHRM;

import io.qameta.allure.Step;

public class ListCustomFieldsPage extends PIMPage {

	private static ListCustomFieldsPage instance;

	public static ListCustomFieldsPage newInstance() {
		if (ListCustomFieldsPage.instance == null)
			ListCustomFieldsPage.instance = new ListCustomFieldsPage();
		return ListCustomFieldsPage.instance;
	}

	@Step("Click Add button")
	public SaveCustomFieldsPage clickAddButton() {
		super.clickAddButton();
		waitForLoadingIconDisappear();
		return new SaveCustomFieldsPage();
	}

}
