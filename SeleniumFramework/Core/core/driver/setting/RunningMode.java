package core.driver.setting;

import java.util.Arrays;

public enum RunningMode {

	/**
	 * Declare constants for running mode
	 */
	Local, Remote;

	/**
	 * Get driver's running mode
	 * 
	 * @param type - String of running type
	 * @return this method will run a loop that check if the parameter type is equal
	 *         to one of those constanted types it will return that running type
	 *         otherwise it will throw an exception
	 */
	public static RunningMode fromString(String type) throws Exception {
		for (RunningMode e : RunningMode.values()) {
			if (e.toString().equalsIgnoreCase(type))
				return e;
		}
		throw new Exception(String.format("Running mode '%s' is not supported. Please use supported running mode: %s",
				type, Arrays.toString(RunningMode.values())));
	}
}
