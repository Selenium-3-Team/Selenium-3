package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.element.wrapper.TextBox;
import dataType.OrangeHRM.Account;
import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {

	private final TextBox txtUsername = new TextBox("//input[@name='username']");
	private final TextBox txtPassword = new TextBox("//input[@name='password']");
	private final Button btnLogin = new Button("//button[contains(@class,'orangehrm-login-button')]");
	private final Label lblErrorMessage = new Label("//div[@role='alert']//p");

	private static LoginPage instance;

	public static LoginPage newInstance() {
		if (LoginPage.instance == null)
			LoginPage.instance = new LoginPage();
		return LoginPage.instance;
	}

	@Step("Enter username {0}")
	public LoginPage enterUsername(String username) {
		txtUsername.sendKeys(username);
		return this;
	}

	@Step("Enter password {0}")
	public LoginPage enterPassword(String password) {
		txtPassword.sendKeys(password);
		return this;
	}

	@Step("Click Login button")
	public PIMPage clickLoginBtn() {
		btnLogin.click();
		return new PIMPage();
	}

	@Step("Login to OrangeHRM page")
	public PIMPage loginOrangeHRM(Account account) {
		enterUsername(account.getUsername());
		enterPassword(account.getPassword());
		return clickLoginBtn();
	}

	@Step("Login failed to OrangeHRM page")
	public LoginPage loginOrangeHRM(String username, String password) {
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		return this;
	}

	@Step("Check if login button is displayed")
	public boolean isLoginButtonDisplayed() {
		return btnLogin.isDisplayed();
	}

	@Step("Check if login page is displayed")
	public boolean isDisplayed() {
		return btnLogin.isDisplayed() && txtUsername.isDisplayed() && txtPassword.isDisplayed();
	}

	@Step("Get the error message")
	public String getErrorMessage() {
		return lblErrorMessage.getLabelText();
	}

}
