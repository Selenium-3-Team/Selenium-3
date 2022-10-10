package core.driver.setting;

import java.util.Arrays;

public enum DriverType {

	/**
	 * Declare constants for driver's type
	 */
	Chrome, Firefox, Safari, Edge, IE;
	
	/**
	 * Get driver's type
	 * @param type - String of driver type
	 * @return this method will run a loop that check if the parameter type is equal to one of those constanted types
	 * it will return that driver type otherwise it will throw an exception
	 */
	public static DriverType fromString(String type) throws Exception {
		for(DriverType driverType: DriverType.values()) {
			if(driverType.toString().equalsIgnoreCase(type)) {
				return driverType;
			}
		}
		throw new Exception(String.format("Driver type '%s' is not supported. Please use supported driver types: %s", type,
				Arrays.toString(DriverType.values())));
	}
}
