package OrangeHRM;

import com.google.gson.JsonObject;

import Common.Utilities;
import Constant.Constant;
import Enum.OrangeHRM.UserRole;
import core.helper.JsonHelper;

public class Account {
	private String username;
	private String password;

	public Account(UserRole userRole) {
		JsonObject account = JsonHelper.getJsonObject(Utilities.getProjectPath() + Constant.ACCOUNT_DATA);
		this.username = account.get(userRole.getUserRole()).getAsJsonObject().get("username").toString();
		this.password = account.get(userRole.getUserRole()).getAsJsonObject().get("password").toString();
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