package frames.OrangeHRM;

import dataType.OrangeHRM.UpdatePasswordForm;

public class UpdatePasswordFrame extends GeneralFrame {

	public void enterCurrentPassword(String currentPassword) {
		enterValueToTextboxOption(UpdatePasswordForm.CURRENT_PASSWORD.getValue(), currentPassword);
	}

	public void enterNewPassword(String currentPassword) {
		enterValueToTextboxOption(UpdatePasswordForm.NEW_PASSWORD.getValue(), currentPassword);
	}

	public void enterConfirmPassword(String currentPassword) {
		enterValueToTextboxOption(UpdatePasswordForm.CONFIRM_PASSWORD.getValue(), currentPassword);
	}
}
