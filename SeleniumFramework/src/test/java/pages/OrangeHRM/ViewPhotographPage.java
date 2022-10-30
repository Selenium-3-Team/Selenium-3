package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.helper.LocatorHelper;
import core.utilities.Utilities;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class ViewPhotographPage extends PIMPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewPhotographPage.class);

	private final Button btnPlus = new Button(locator.getLocator("btnPlus"));
	private final Label lblErrorMsg = new Label(locator.getLocator("lblErrorMsg"));

	private static ViewPhotographPage instance;

	public static ViewPhotographPage newInstance() {
		if (ViewPhotographPage.instance == null)
			ViewPhotographPage.instance = new ViewPhotographPage();
		return ViewPhotographPage.instance;
	}

	@Step("Click Add image button on View Photograph page")
	public ViewPhotographPage clickAddImage() {
		btnPlus.click();
		return this;
	}

	@Step("Upload image by file path {0}")
	public ViewPhotographPage uploadImage(String filePath) {
		Utilities.uploadFile(filePath);
		return this;
	}

	@Step("Check error message label is displayed")
	public boolean isLblErrorMessageDisplayed() {
		return lblErrorMsg.isDisplayed();
	}

	@Step("Get error message")
	public String getLblErrorMessage() {
		return lblErrorMsg.getLabelText();
	}

}
