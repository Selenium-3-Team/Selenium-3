package frames.OrangeHRM;

import core.element.wrapper.Label;

public class ViewSystemUsersFrame extends GeneralFrame{

	private final Label lblSystemUsers = new Label("//h5[text()='System Users']");
	
	public boolean isSystemUsersLabelDisplayed() {
		return lblSystemUsers.isDisplayed();
	}

}
