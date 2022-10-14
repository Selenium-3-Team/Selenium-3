package core.helper;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import core.driver.setting.DriverProperty;

/**
 * <p>
 * A Collection of helper methods for browser setting.
 * </p>
 */
public class BrowserSettingHelper {

	/**
	 * This method will parse browser setting value json to DriverProperty
	 * 
	 * @param file        - a string path of file
	 * @param sectionName - a string of key driver config
	 * @return - Driver property
	 */
	public static DriverProperty getDriverProperty(String file, String sectionName) {
		try {
			JSONObject jObject = JsonHelper.getJSONObject(file);
			DriverProperty property = new Gson().fromJson(jObject.get(sectionName).toString(), DriverProperty.class);

			return property;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
