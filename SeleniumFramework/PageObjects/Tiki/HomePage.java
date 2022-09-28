package Tiki;

import DriverWrapper.DriverManagement;
import io.qameta.allure.Step;

public class HomePage extends GeneralPage{

	// Methods
	@Step("Navigate to {0}")
	public HomePage open(String url) {
		DriverManagement.getDriver().navigate().to(url);
		return this;
	}
		
}
