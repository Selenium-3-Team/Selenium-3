package core.driver.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import com.google.common.base.Throwables;

import core.common.Constant;
import core.driver.base.BaseDriver;
import core.driver.setting.DriverProperty;

public class DriverFactory {

	private static final Logger logger = Constant.createLogger(DriverFactory.class.getName());
	private static final String DRIVER_CLASS_FULLNAME = "core.driver.resource.%sDriver";
	private static final String METHOD_NAME = "create%sDriver";
	
	public static BaseDriver newInstance(DriverProperty property) {
		String classFullName = String.format(DRIVER_CLASS_FULLNAME, property.getDriverType());
		String methodName = String.format(METHOD_NAME, property.getMode());
		
		try {
			Method method;
			Class<?> cla = Class.forName(classFullName);
			Constructor<?> cons = cla.getDeclaredConstructor(new Class[] {DriverProperty.class});
			Object obj = cons.newInstance(property);
			
			// Create driver
			method = cla.getDeclaredMethod(methodName);
			method.invoke(obj);
			return (BaseDriver) obj;
		} catch(Exception e) {
			logger.severe(String.format("Cannot create new %s driver instance. %s", property.getDriverType().toString(), Throwables.getStackTraceAsString(e)));
			return null;
		}
	}
	
}
