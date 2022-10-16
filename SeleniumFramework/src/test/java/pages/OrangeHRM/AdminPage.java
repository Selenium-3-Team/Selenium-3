package pages.OrangeHRM;

import core.driver.manager.Driver;
import core.element.base.Element;
import dataType.OrangeHRM.Account;
import io.qameta.allure.Step;

public class AdminPage extends GeneralPage {

	private static AdminPage instance;

	public static AdminPage newInstance() {
		if (AdminPage.instance == null)
			AdminPage.instance = new AdminPage();
		return AdminPage.instance;
	}

	
	
}
