package tests.OrangeHRM;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import core.report.Logger;
import dataType.OrangeHRM.Account;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRole;
import io.qameta.allure.Description;
import pages.OrangeHRM.AdminPage;
import pages.OrangeHRM.HomePage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.PIMPage;
import tests.TestBase;

public class AdminTest extends TestBase {

	LoginPage loginPage = LoginPage.newInstance();
	HomePage homePage = HomePage.newInstance();
	PIMPage pimPage = PIMPage.newInstance();
	AdminPage adminPage = AdminPage.newInstance();

	@Test
	@Description("Test case 05: Admin User can search employees successfully by \"User Role\".")
	public void TC05() {

		Account account = new Account(UserRole.ADMIN);

		Logger.info("Precondition: Login successfully with a valid account");
		pimPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Click the \"Admin\" tab on the Left Menu");
		adminPage = pimPage.gotoAdminPage();

		Logger.verify("1.1. The \"User Management\" page should be displayed.");
		assertTrue(adminPage.isTopBarMenuItemActived(TopBarMenuItem.USERMANAGEMENT), "The \"User Management\" page should be displayed.");

		Logger.verify("1.2. The \"System Users\" form should be displayed.");
		assertTrue(adminPage.isSystemUsersLabelDisplayed(), "The \"System Users\" form should be displayed.");

		Logger.info("Step 2: Select \"Admin\" on the \"User Role\" dropdown");
		adminPage.selectItemOnUserRoleDropdown("Admin");

		Logger.info("Step 3: Click on the \"Search\" button");
		adminPage.clickSearchButton();

		Logger.verify("3. At least 1 record should be displayed on the \"Record Found\" form.");
		assertTrue(adminPage.isNoRecordsFoundLabelNotDisplayed(), "At least 1 record should be displayed on the \\\"Record Found\\\" form.");

	}

}
