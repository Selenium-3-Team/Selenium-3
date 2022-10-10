package core.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * Modal is class for browser alert pop-up.
 */
public class AlertModal {
	
	private Alert alert;

	/**
	 * Create a Modal for browser alert pop-up.
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
	 * Click confirm button on pop-up.
	 *
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code modal.confirm();} //Click on confirm button
	 */
	public void confirm() {
		alert.accept();
	}

	/**
	 * Enter text to pop-up.
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
	 * Click confirm dismiss on pop-up.
	 *
	 * @example {@code Modal newPopUp = new Modal(Driver.getDriver());} //Create an instance Modal
	 *          {@code modal.dismiss();} //Click Cancel or Dismiss button on Pop-Up
	 */
	public void dismiss() {
		alert.dismiss();
	}

	/**
	 * Get Alert message on pop-up.
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
