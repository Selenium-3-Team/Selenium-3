package core.common;

import java.util.logging.Logger;

import Common.Utilities;

/**
 * A Constants class for drivers and elements
 */
public class Constant {

	/**
	 * Wait time out in seconds
	 */
	public static final int TIMEOUT = 30;

	/**
	 * Wait time out in seconds
	 */
	public static final int SHORT_TIMEOUT = 5;

	/**
	 * Wait time out in seconds
	 */
	public static final int VERY_SHORT_TIMEOUT = 3;

	/**
	 * Wait time out in seconds
	 */
	public static final int LONG_TIMEOUT = 60;

	/**
	 * Referenced method by class name itself. This is use for point to the logger
	 * properties file
	 */
	static {
		String path = Utilities.getProjectPath() + "\\Core\\core.common\\logger.properties";
		System.setProperty("java.util.logging.config.file", path);
	}

	/**
	 * Create logger object for a class
	 * 
	 * @param className - a string of class name
	 * @return Logger object which contains log messages
	 */
	// Creates the logger.
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}

}