package pages.OrangeHRM;

import core.element.base.Element;
import core.utilities.Utilities;
import dataType.OrangeHRM.Account;
import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {

	private final Element txtUsername = new Element("//input[@name='username']");
	private final Element txtPassword = new Element("//input[@name='password']");
	private final Element btnLogin = new Element("//button[contains(@class,'orangehrm-login-button')]");
	private final Element alertInvalidCredentials = new Element(
			"//div[@role='alert']//p[text()='Invalid credentials']");

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
		boolean result = true;
		result &= Utilities.check(btnLogin.isDisplayed(), "Login button should be displayed.");
		result &= Utilities.check(txtUsername.isDisplayed(), "Username textbox should be displayed.");
		result &= Utilities.check(txtPassword.isDisplayed(), "Password textbox should be displayed.");
		return result;
	}

	@Step("Check if invalid credentials alert is displayed")
	public boolean isInvalidCredentialsAlertDisplayed() {
		return alertInvalidCredentials.isDisplayed();
	}

}
