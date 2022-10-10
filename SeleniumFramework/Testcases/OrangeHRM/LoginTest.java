package OrangeHRM;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import Common.Logger;
import Constant.Constant;

public class LoginTest extends TestBase {

	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();

	@Test
	public void TC01() {
		Logger.info("Test case 01: User can log in successfully with a valid username and a valid password");

		Account account = new Account("AdminAccount");
		Logger.info("Step 1: Navigate to OrangeHRM");
		loginPage.open(Constant.ORANGEHRM_URL);

		Logger.verify("1.1. The login form should be displayed.");
		assertTrue(loginPage.isLoginButtonDisplayed(), "The login form should be displayed.");

		Logger.verify("1.2. OrangeHRM copyright text should be displayed.");
		assertTrue(loginPage.isCopyRightTextDisplayed(), "OrangeHRM copyright text should be displayed.");

		Logger.info("Step 2: Enter a valid username in the \"Username\" textbox");
		Logger.info("Step 3: Enter a valid password in the \"Password\" textbox");
		Logger.info("Step 4: Click the \"Login\" button");
		homePage = loginPage.loginOrangeHRM(account);
		
		Logger.verify("4.1. VP. The PIM page should be displayed.");
		Logger.verify("4.2. The currently selected tab is \"Employee List\".");
		Logger.verify("4.3 OrangeHRM copyright text should be displayed.");
		assertTrue(loginPage.isCopyRightTextDisplayed(), "OrangeHRM copyright text should be displayed.");

	}

}
