package utils.constant;

import core.utilities.Utilities;

public class Constant {

	public static final String PROJECT_PATH = Utilities.getProjectPath();
	public static final String ORANGEHRM_URL = "https://opensource-demo.orangehrmlive.com/";
	public static final String DRIVER_SETTING_FILE = PROJECT_PATH
			+ "\\src\\test\\resource\\driver.setting.properties.json";
	public static final String ACCOUNT_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\Account.json";
	public static final String EMPLOYEE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\Employee.json";
	public static final String LEAVE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\LeaveTicket.json";

	// Application info
	public static final String COMPANY = "OrangeHRM, Inc";
	public static final String VERSION = "OrangeHRM OS 5.1";
	public static final String ERROR_LOGIN_MESSAGE = "Invalid credentials";

	// Timeout
	public static final int DEFAULT_TIMEOUT = 30;

}