package pages.OrangeHRM;

import dataType.OrangeHRM.CustomFieldType;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;

public class SaveCustomFieldsPage extends PIMPage {

	private static SaveCustomFieldsPage instance;

	public static SaveCustomFieldsPage newInstance() {
		if (SaveCustomFieldsPage.instance == null)
			SaveCustomFieldsPage.instance = new SaveCustomFieldsPage();
		return SaveCustomFieldsPage.instance;
	}

	@Step("Fill {0} for fieldName, {1} for screen, {2} for type, {3} for selectOptions on Add Custom Field form")
	public SaveCustomFieldsPage fillInformationForAddCustomField(String fieldName, String screen, String type, String selectOptions) {
		enterValueToTextboxOption(TextBoxTitle.FIELD_NAME, fieldName);
		selectOption(DropdownTitle.SCREEN, screen);
		selectOption(DropdownTitle.TYPE, type);
		if (type.equals(CustomFieldType.DROP_DOWN.getValue())) {
			enterValueToTextboxOption(TextBoxTitle.SELECT_OPTIONS, selectOptions);
		}
		return this;
	}

	@Step("Click Save button")
	public ListCustomFieldsPage clickSaveButton() {
		super.clickSaveButton();
		waitForLoadingIconDisappear();
		return new ListCustomFieldsPage();
	}

}
