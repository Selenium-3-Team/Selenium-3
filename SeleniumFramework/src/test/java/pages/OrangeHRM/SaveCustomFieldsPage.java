package pages.OrangeHRM;

import dataType.OrangeHRM.CustomFieldType;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.TextBoxTitle;

public class SaveCustomFieldsPage extends PIMPage{

	private static SaveCustomFieldsPage instance;

	public static SaveCustomFieldsPage newInstance() {
		if (SaveCustomFieldsPage.instance == null)
			SaveCustomFieldsPage.instance = new SaveCustomFieldsPage();
		return SaveCustomFieldsPage.instance;
	}

	public SaveCustomFieldsPage fillInformationForAddCustomField(String fieldName, String screen, String type, String selectOptions) {
		enterValueToTextboxOption(TextBoxTitle.FIELD_NAME, fieldName);
		selectOption(DropdownTitle.SCREEN, screen);
		selectOption(DropdownTitle.TYPE, type);
		if(type.equals(CustomFieldType.DROP_DOWN.getValue())) {
			enterValueToTextboxOption(TextBoxTitle.SELECT_OPTIONS, selectOptions);
		}
		return this;
	}
	
	public ListCustomFieldsPage clickSaveButton() {
		super.clickSaveButton();
		waitForLoadingIconDisappear();
		return new ListCustomFieldsPage();
	}
	
}
