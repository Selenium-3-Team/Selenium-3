package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Account;
import utils.constant.Constant;
import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {
	
	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, LoginPage.class);
	
	private final TextBox txtUsername = new TextBox(locator.getLocator("txtUsername"));
	private final TextBox txtPassword = new TextBox(locator.getLocator("txtPassword"));
	private final Button btnLogin = new Button(locator.getLocator("btnLogin"));
	private final Label lblErrorMessage = new Label(locator.getLocator("lblErrorMessage"));

	private static LoginPage instance;

	public static LoginPage newInstance() {
		if (LoginPage.instance == null)
			LoginPage.instance = new LoginPage();
		return LoginPage.instance;
	}
	
	@Step("Wait for Login page displayed")
	public LoginPage waitForPageLoad() {
		txtUsername.waitForDisplayed();
		txtPassword.waitForDisplayed();
		btnLogin.waitForDisplayed();
		return this;
	}

	@Step("Enter username {0} on Login form")
	public LoginPage enterUsername(String username) {
		txtUsername.sendKeys(username);
		return this;
	}

	@Step("Enter password {0} on Login form")
	public LoginPage enterPassword(String password) {
		txtPassword.sendKeys(password);
		return this;
	}

	@Step("Click Login button on Login form")
	public void clickLoginBtn() {
		btnLogin.click();
	}

	@Step("Login to OrangeHRM page")
	public ViewEmployeeListPage loginOrangeHRM(Account account) {
		enterUsername(account.getUsername());
		enterPassword(account.getPassword());
		clickLoginBtn();
		return new ViewEmployeeListPage();
	}

	@Step("Get the error message")
	public String getErrorMessage() {
		return lblErrorMessage.getLabelText();
	}
	
	@Step("Check Login button is displayed")
	public boolean isLoginButtonDisplayed() {
		return btnLogin.isDisplayed();
	}

	@Step("Check Login page is displayed")
	public boolean isDisplayed() {
		return btnLogin.isDisplayed() && txtUsername.isDisplayed() && txtPassword.isDisplayed();
	}

}
