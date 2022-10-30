package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import core.helper.RandomHelper;
import dataType.OrangeHRM.UserRoleOption;
import utils.constant.Constant;

public class Account {

	private String username;
	private String password;
	private String role = "";

	public Account() {
		this.username = RandomHelper.randomString();
		this.password = Constant.STRONG_PASSWORD;
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account(UserRoleOption userRole) {
		JsonObject account = JsonHelper.getJsonObject(Constant.ACCOUNT_DATA);
		String key = userRole.getValue().toLowerCase();
		this.username = account.get(key).getAsJsonObject().get("username").getAsString();
		this.password = account.get(key).getAsJsonObject().get("password").getAsString();
		this.role = key;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}