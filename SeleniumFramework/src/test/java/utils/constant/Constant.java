package utils.constant;

import core.utilities.Utilities;

public class Constant {

	public static final String PROJECT_PATH = Utilities.getProjectPath();
	public static final String ORANGEHRM_URL = "https://opensource-demo.orangehrmlive.com/";
	public static final String DRIVER_SETTING_FILE = PROJECT_PATH
			+ "\\src\\test\\resource\\driver.setting.properties.json";
	public static final String LOCATOR_FOLDER_PATH = PROJECT_PATH + "\\src\\test\\resource\\locator\\OrangeHRM\\";
	public static final String ACCOUNT_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\Account.json";
	public static final String EMPLOYEE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\Employee.json";
	public static final String LEAVE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\LeaveTicket.json";
	public static final String CUSTOM_FIELD_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\CustomField.json";
	public static final String IMAGE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\Images\\Test.jpg";
	public static final String CANDIDATE_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\Candidate.json";
	public static final String CONTACT_DETAILS_DATA = PROJECT_PATH + "\\src\\test\\resource\\data\\OrangeHRM\\ContactDetails.json";

	// Application info
	public static final String COMPANY = "OrangeHRM, Inc";
	public static final String VERSION = "OrangeHRM OS 5.1";
	public static final String ERROR_LOGIN_MESSAGE = "Invalid credentials";
	public static final String STRONG_PASSWORD = "Logigear123!!";

	// Application Error Message
	public static final String UPLOAD_IMAGE_ERROR_MESSAGE = "Attachment Size Exceeded";
	
	// Timeout
	public static final int DEFAULT_TIMEOUT = 30;
	public static final int VERY_SHORT_TIMEOUT = 10;

}