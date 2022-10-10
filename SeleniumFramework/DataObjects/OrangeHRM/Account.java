package OrangeHRM;

import org.json.simple.JSONObject;

import Common.Utilities;

public class Account {
	private String username;
	private String password;

	public Account(String accountFileName) {
		JSONObject data = (JSONObject) Utilities.getDataFromJsonFile("AccountData", accountFileName);
		if (data != null) {
			this.username = data.get("username").toString();
			this.password = data.get("password").toString();
		}
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