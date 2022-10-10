package core.helper;

import java.io.FileReader;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import core.driver.setting.DriverProperty;

/**
 * <p>
 * A Collection of helper methods for browser setting.
 * </p>
 */
public class BrowserSettingHelper {

	public static DriverProperty getDriverProperty(String file, String sectionName) {
		DriverProperty property = new DriverProperty();
		try {
			Ini ini = new Ini(new FileReader(file));
			Section section = ini.get(sectionName);
			if (section == null) {
				throw new Exception(String.format("Cannot find '%s' in file '%s'", sectionName, file));
			}
			String mode = section.get("mode");
			String platform = section.get("platform");
			String driverType = section.get("driver");
			String browserName = driverType.toLowerCase();
			String remoteUrl = section.get("remoteUrl");
			String capabilities = section.get("capabilities");
			String args = section.get("arguments");
			String userPrefs = section.get("userProfilePreference");

			property.setMode(mode);
			property.setPlatform(platform);
			property.setDriverType(browserName);
			property.setRemoteUrl(remoteUrl);
			property.setCapabilities(capabilities);
			property.setArguments(args);
			property.setUserProfilePreference(userPrefs);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return property;
	}

}
