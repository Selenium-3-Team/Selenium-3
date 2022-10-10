package OrangeHRM;

import core.driver.manager.Driver;
import core.element.base.Element;
import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {

	private final Element txtUsername = new Element("//input[@name='username']");
	private final Element txtPassword = new Element("//input[@name='password']");
	private final Element btnLogin = new Element("//button[contains(@class,'orangehrm-login-button')]");
	

	// Methods
	@Step("Navigate to {0}")
	public LoginPage open(String url) {
		Driver.getDriver().navigate().to(url);
		return this;
	}

	@Step("Login to OrangeHRM page")
	public HomePage loginOrangeHRM(Account account) {
		System.out.println(account.getUsername());
		System.out.println(account.getPassword());
		txtUsername.sendKeys(account.getUsername());
		txtPassword.sendKeys(account.getPassword());
		btnLogin.click();
		return new HomePage();
	}
	
	@Step("Check if login button is displayed")
	public boolean isLoginButtonDisplayed() {
		return btnLogin.isDisplayed();
	}
	
	
}
