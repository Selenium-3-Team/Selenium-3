package core.utilities;

import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import core.common.Constant;

/**
 * <p>
 * Modal is class for browser alert pop-up.
 * </p>
 */
public class AlertModal {
	
	/**
	 * Contains log of the alert used
	 */
	private static final Logger logger = Constant.createLogger(AlertModal.class.getName());
	
	private Alert alert;

	/**
	 * <p>
	 * Create a Modal for browser alert pop-up.
	 * </p>
	 * 
	 * @param driver		the driver to be used
	 *
	 * @param driver the driver to be used
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 */
	public AlertModal(WebDriver driver) {
		this.alert = driver.switchTo().alert();
	}

	/**
	 * <p>
	 * Click confirm button on pop-up.
	 * </p>
	 *
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code modal.confirm();} //Click on confirm button
	 */
	public void confirm() {
		logger.info("Click accept alert button");
		try {
			alert.accept();
		}catch(Exception e) {
			logger.severe(String.format("Has error when click accept alert button: %s", e.getMessage()));
		}
		
	}

	/**
	 * <p>
	 * Enter text to pop-up.
	 * </p>
	 *
	 * @param inputText if not null, sets value in prompt dialog input
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code modal.prompt("Simple text to pop-up");} //Enter "Simple text to pop-up" to Pop-Up and click Ok/accept button
	 */
	public void prompt(String inputText) {
		logger.info(String.format("Enter %s into alert", inputText));
		try {
			if (inputText != null)
				alert.sendKeys(inputText);
			alert.accept();
		}catch(Exception e) {
			logger.severe(String.format("Has error when enter %s into alert text box: %s", inputText, e.getMessage()));
		}
		
	}

	/**
	 * <p>
	 * Click confirm dismiss on pop-up.
	 * </p>
	 *
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code modal.dismiss();} //Click Cancel or Dismiss button on Pop-Up
	 */
	public void dismiss() {
		logger.info("Click dismiss alert button");
		try {
			alert.dismiss();
		}catch(Exception e) {
			logger.severe(String.format("Has error when click dismiss alert button: %s", e.getMessage()));
		}
	}

	/**
	 * <p>
	 * Get Alert message on pop-up.
	 * </p>
	 * 
	 * @return String, the message on pop-up
	 *
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code String message = modal.getAlertText();} //Get Alert text to message String
	 */
	public String getAlertText() {
		logger.info("Get text alert pop-up");
		try {
			return alert.getText();
		}catch(Exception e) {
			logger.severe(String.format("Has error when get text alert pop-up: %s", e.getMessage()));
			return null;
		}
	}
}
