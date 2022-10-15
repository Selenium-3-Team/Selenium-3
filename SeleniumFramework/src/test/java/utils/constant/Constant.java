package utils.constant;

import utils.helper.Utilities;

public class Constant {

	public static final String TIKI_URL = "https://tiki.vn/";
	public static final String ORANGEHRM_URL = "https://opensource-demo.orangehrmlive.com/";
	public static final String BREAD_CRUMB_ITEM_REGEX = ">";
	public static final String DEFAULT_DELEMITER = ",";
	public static final String UPPER_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWER_CHARS = "abcdefghijklmnopqrstuvwxyz";
	public static final String DRIVER_SETTING_FILE = Utilities.getProjectPath()
			+ "\\src\\test\\resource\\driver.setting.properties.json";
	public static final String ACCOUNT_DATA = "\\src\\test\\resource\\data\\OrangeHRM\\Account.json";

	// Timeout
	public static final int DEFAULT_TIMEOUT = 30;

}