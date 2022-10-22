package pages.OrangeHRM;

import dataType.OrangeHRM.LeftPanelMenuItem;
import frames.OrangeHRM.LeaveListFrame;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class LeavePage extends GeneralPage {

	// Frames
	private final LeaveListFrame viewLeaveListFrame = new LeaveListFrame();

	private static LeavePage instance;

	public static LeavePage newInstance() {
		if (LeavePage.instance == null)
			LeavePage.instance = new LeavePage();
		return LeavePage.instance;
	}

	@Step("Wait for Leave page displayed")
	public LeavePage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.LEAVE);
		lblHeaderTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		viewLeaveListFrame.waitForLoading();
		return this;
	}

}
