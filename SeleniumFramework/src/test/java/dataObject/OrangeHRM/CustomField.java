package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import utils.constant.Constant;

public class CustomField {

	private String fieldName;
	private String screen;
	private String type;
	private String selectOptions;

	public CustomField(String fieldName, String screen, String type, String selectOptions) {
		this.fieldName = fieldName;
		this.screen = screen;
		this.type = type;
		this.selectOptions = selectOptions;
	}

	public CustomField(String fieldName, String screen, String type) {
		this.fieldName = fieldName;
		this.screen = screen;
		this.type = type;
	}

	public CustomField(String key) {
		JsonObject customField = JsonHelper.getJsonObject(Constant.CUSTOM_FIELD_DATA);
		this.fieldName = customField.get(key).getAsJsonObject().get("fieldName").getAsString();
		this.screen = customField.get(key).getAsJsonObject().get("screen").getAsString();
		this.type = customField.get(key).getAsJsonObject().get("type").getAsString();
		this.selectOptions = customField.get(key).getAsJsonObject().get("selectOptions").getAsString();
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSelectOptions(String selectOptions) {
		this.selectOptions = selectOptions;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getScreen() {
		return screen;
	}

	public String getType() {
		return type;
	}

	public String getSelectOptions() {
		return selectOptions;
	}

}
