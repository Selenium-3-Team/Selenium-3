package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.utilities.Utilities;

public class ViewPhotographPage extends PIMPage{

	private final Button btnPlus = new Button("//button[contains(@class,'employee-image-action')]");
	private final Label lblErrorMsg = new Label(
			"//div[@class='orangehrm-employee-picture']//span[contains(@class,'oxd-input-field-error-message')]");
	
	private static ViewPhotographPage instance;

	public static ViewPhotographPage newInstance() {
		if (ViewPhotographPage.instance == null)
			ViewPhotographPage.instance = new ViewPhotographPage();
		return ViewPhotographPage.instance;
	}
	
	public ViewPhotographPage clickAddImage() {
		btnPlus.click();
		return this;
	}
	
	public ViewPhotographPage uploadImage(String filePath) {
		Utilities.uploadFile(filePath);
		return this;
	}
	
	public boolean isLblErrorMessageDisplayed() {
		return lblErrorMsg.isDisplayed();
	}

	public String getLblErrorMessage() {
		return lblErrorMsg.getLabelText();
	}
	
}
