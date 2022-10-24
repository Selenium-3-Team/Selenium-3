package frames.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.utilities.Utilities;

public class ViewPhotographFrame extends GeneralFrame {

	private final Button btnPlus = new Button("//button[contains(@class,'employee-image-action')]");
	private final Label lblErrorMsg = new Label(
			"//div[@class='orangehrm-employee-picture']//span[contains(@class,'oxd-input-field-error-message')]");

	public void clickAddImage() {
		btnPlus.click();
	}

	public void uploadPicture(String filePath) {
		Utilities.uploadFile(filePath);
	}

	public boolean isLblErrorMessageDisplayed() {
		return lblErrorMsg.isDisplayed();
	}

	public String getLblErrorMessage() {
		return lblErrorMsg.getLabelText();
	}

}
