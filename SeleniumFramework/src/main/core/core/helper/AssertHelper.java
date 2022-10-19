package core.helper;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.testng.Assert;

import core.common.Constant;
import core.driver.manager.Driver;

public class AssertHelper {

	private static final Logger logger = Constant.createLogger(AssertHelper.class.getName());

	/**
	 * @example: boolean result = true -> Declare a boolean variable result &=
	 *           check() {N.1}, result &= check() {N.2},... return result
	 */
	public static boolean assertTrue(boolean actual, String message) {
		try {
			Assert.assertTrue(actual, message);
			logger.info(message);
			return true;
		} catch (AssertionError assertionError) {
			logger.severe(message + ": " + assertionError);
			try {
				logger.info("URL: " + Driver.getURL());
			} catch (Throwable throwable) {
				logger.severe("Cannot get URL browser");
			}
			return false;
		}
	}

	public static boolean returnCheckResult(ArrayList<Boolean> results) {
		return results.contains(true);
	}
}
