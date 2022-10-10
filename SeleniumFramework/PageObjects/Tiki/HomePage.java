package Tiki;

import core.driver.manager.Driver;
import io.qameta.allure.Step;

public class HomePage extends GeneralPage{

	// Methods
	@Step("Navigate to {0}")
	public HomePage open(String url) {
		Driver.getDriver().navigate().to(url);
		return this;
	}
		
}
