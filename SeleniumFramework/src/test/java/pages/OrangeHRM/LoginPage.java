package pages.OrangeHRM;

import core.driver.manager.Driver;
import core.element.base.Element;
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

	// Methods
	@Step("Navigate to {0}")
	public LoginPage open(String url) {
		Driver.getDriver().navigate().to(url);
		return this;
	}

	@Step("Login to OrangeHRM page")
	public PIMPage loginOrangeHRM(Account account) {
		txtUsername.sendKeys(account.getUsername());
		txtPassword.sendKeys(account.getPassword());
		btnLogin.click();
		return new PIMPage();
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
		return (btnLogin.isDisplayed() && txtUsername.isDisplayed());
	}

	@Step("Check if invalid credentials alert is displayed")
	public boolean isInvalidCredentialsAlertDisplayed() {
		return alertInvalidCredentials.isDisplayed();
	}

}
