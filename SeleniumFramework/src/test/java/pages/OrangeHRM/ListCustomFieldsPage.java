package pages.OrangeHRM;

public class ListCustomFieldsPage extends PIMPage{

	private static ListCustomFieldsPage instance;

	public static ListCustomFieldsPage newInstance() {
		if (ListCustomFieldsPage.instance == null)
			ListCustomFieldsPage.instance = new ListCustomFieldsPage();
		return ListCustomFieldsPage.instance;
	}
	
	public SaveCustomFieldsPage clickAddButton() {
		super.clickAddButton();
		waitForLoadingIconDisappear();
		return new SaveCustomFieldsPage();
	}
}
