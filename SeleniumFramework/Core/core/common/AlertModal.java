package core.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * <p>
 * Modal is class for browser alert pop-up.
 * </p>
 */
public class AlertModal {
	
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
		alert.accept();
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
		if (inputText != null)
			alert.sendKeys(inputText);
		alert.accept();
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
		alert.dismiss();
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
		return alert.getText();
	}
}
