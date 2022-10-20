package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import core.utilities.Utilities;
import dataType.OrangeHRM.UserRole;
import utils.constant.Constant;

public class Account {
	private String username;
	private String password;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account(UserRole userRole) {
		JsonObject account = JsonHelper.getJsonObject(Utilities.getProjectPath() + Constant.ACCOUNT_DATA);
		String key = userRole.getUserRole().toLowerCase();
		this.username = account.get(key).getAsJsonObject().get("username").getAsString();
		this.password = account.get(key).getAsJsonObject().get("password").getAsString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}