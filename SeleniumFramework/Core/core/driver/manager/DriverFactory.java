package core.driver.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import com.google.common.base.Throwables;

import core.common.Constant;
import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;

/**
 * Driver factory pattern, where only the concrete classes need to know exactly
 * how to create a new instance. This helps create a simple one way to create
 * WebDriver instance. Containing all the concrete implementations for every
 * browser. If we want to create an instance of Chrome Driver, we just pass the
 * chrome driver property and it will get type and mode of the corressponding
 * driver class.
 */
public class DriverFactory {

	/**
	 * Contains log of the class
	 */
	private static final Logger logger = Constant.createLogger(DriverFactory.class.getName());

	/**
	 * Driver type class full name
	 */
	private static final String DRIVER_CLASS_FULLNAME = "core.driver.resource.%sDriver";

	/**
	 * Driver mode method in driver type class
	 */
	private static final String METHOD_NAME = "create%sDriver";

	/**
	 * Depend on driver property to create new instance of driver(Chrome,
	 * Firefox,...). It gets type and mode of the driver to catch exactly each
	 * resource for the instance
	 * 
	 * @param property - Driver property contains set of attributes(Type, Platform,
	 *                 Mode,...)
	 * @return a new instance of driver
	 */
	public static BaseDriver newInstance(DriverProperty property) {
		String classFullName = String.format(DRIVER_CLASS_FULLNAME, property.getDriverType());
		String methodName = String.format(METHOD_NAME, property.getMode());

		try {
			Method method;
			Class<?> cla = Class.forName(classFullName);
			Constructor<?> cons = cla.getDeclaredConstructor(new Class[] { DriverProperty.class });
			Object obj = cons.newInstance(property);

			// Create driver
			method = cla.getDeclaredMethod(methodName);
			method.invoke(obj);
			return (BaseDriver) obj;
		} catch (Exception e) {
			logger.severe(String.format("Cannot create new %s driver instance. %s", property.getDriverType().toString(),
					Throwables.getStackTraceAsString(e)));
			return null;
		}
	}

}
