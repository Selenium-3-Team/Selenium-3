package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.helper.LocatorHelper;
import core.utilities.Utilities;
import utils.constant.Constant;

public class ViewPhotographPage extends PIMPage{

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewPhotographPage.class);
	
	private final Button btnPlus = new Button(locator.getLocator("btnPlus"));
	private final Label lblErrorMsg = new Label(locator.getLocator("lblErrorMsg"));
	
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
