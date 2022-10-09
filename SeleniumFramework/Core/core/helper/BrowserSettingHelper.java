package core.helper;

import java.io.FileReader;

import org.apache.commons.lang3.EnumUtils;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import core.driver.setting.DriverProperty;
import core.driver.setting.DriverType;
import core.driver.setting.Platform;
import core.driver.setting.RunningMode;

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

			property.setMode(toRunningMode(mode));
			property.setPlatform(toPlatformType(platform));
			property.setDriverType(toDriverType(browserName));
			property.setRemoteUrl(remoteUrl);
			property.setCapabilities(capabilities);
			property.setArguments(args);
			property.setUserProfilePreference(userPrefs);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return property;
	}

	private static RunningMode toRunningMode(String mode) throws Exception {
		if (EnumUtils.isValidEnum(RunningMode.class, mode)) {
			return EnumUtils.getEnum(RunningMode.class, mode);
		} else {
			throw new Exception(String.format("Don't allow the '%s'. Please use %s for your configuration", mode,
					RunningMode.fromString(mode)));
		}
	}

	private static Platform toPlatformType(String type) throws Exception {
		if (EnumUtils.isValidEnum(Platform.class, type)) {
			return EnumUtils.getEnum(Platform.class, type);
		} else {
			throw new Exception(String.format("Don't allow the '%s'. Please use %s for your configuration", type,
					Platform.fromString(type)));
		}
	}

	private static DriverType toDriverType(String type) throws Exception {
		if (EnumUtils.isValidEnum(DriverType.class, type)) {
			return EnumUtils.getEnum(DriverType.class, type);
		} else {
			throw new Exception(String.format("Don't allow the '%s'. Please use %s for your configuration", type,
					DriverType.fromString(type)));
		}
	}
}
