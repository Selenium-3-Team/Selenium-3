package core.driver.setting;

import java.util.Arrays;

public enum Platform {

	/**
	 * Declare constants for browser platform
	 */
	WINDOWS, MAC, LINUX;

	/**
	 * Get driver's platform
	 * 
	 * @param type - String of platform
	 * @return this method will run a loop that check if the parameter type is equal
	 *         to one of those constanted types it will return that platform
	 *         otherwise it will throw an exception
	 */
	public static Platform fromString(String type) throws Exception {
		for (Platform e : Platform.values()) {
			if (e.toString().equalsIgnoreCase(type))
				return e;
		}
		throw new Exception(String.format("Platform '%s' is not supported. Please use supported platform: %s", type,
				Arrays.toString(Platform.values())));
	}
}
