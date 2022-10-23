package pages.OrangeHRM;

import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.LeaveItem;
import dataType.OrangeHRM.LeftPanelMenuItem;
import frames.OrangeHRM.ApplyLeaveFrame;
import frames.OrangeHRM.ViewLeaveListFrame;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class LeavePage extends GeneralPage {

	// Frames
	private final ViewLeaveListFrame viewLeaveListFrame = new ViewLeaveListFrame();
	private final ApplyLeaveFrame applyLeaveFrame = new ApplyLeaveFrame();

	private static LeavePage instance;

	public static LeavePage newInstance() {
		if (LeavePage.instance == null)
			LeavePage.instance = new LeavePage();
		return LeavePage.instance;
	}

	@Step("Wait for Leave page displayed")
	public LeavePage waitForPageLoad() {
		lblHeaderTitle.generateDynamic(LeftPanelMenuItem.LEAVE.getValue());
		lblHeaderTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		viewLeaveListFrame.waitForLoading();
		return this;
	}

	@Step("Enter fromDate {0}")
	public LeavePage enterFromDate(String fromDate) {
		applyLeaveFrame.enterValueToTextboxOption(LeaveItem.FROMDATE.getValue(), fromDate);
		return this;
	}

	@Step("Enter toDate {0}")
	public LeavePage enterToDate(String toDate) {
		applyLeaveFrame.enterValueToTextboxOption(LeaveItem.TODATE.getValue(), toDate);
		return this;
	}

	@Step("Click {0} dropdown on the Apply Leave")
	public LeavePage clickDropdownOnApplyLeave(String drpName) {
		applyLeaveFrame.clickDropdownOption(drpName);
		return this;
	}

	@Step("Select {0} option On Apply Leave")
	public LeavePage selectOption(String optionName) {
		applyLeaveFrame.selectOption(optionName);
		return this;
	}

	@Step("Select option on the User Role dropdown")
	public LeavePage selectOptionOnApplyLeave(LeaveItem leaveItem, String value) {
		clickDropdownOnApplyLeave(leaveItem.getValue());
		selectOption(value);
		return this;
	}

	@Step("Enter all required information on Apply Leave Form")
	public LeavePage enterAllRequiredOnApplyLeaveForm(String leaveType, String fromDate, String toDate) {
		selectOptionOnApplyLeave(LeaveItem.LEAVETYPE, leaveType);
		enterFromDate(fromDate);
		enterToDate(toDate);
		return this;
	}

	@Step("Enter all required information on Apply Leave Form")
	public LeavePage enterAllRequiredOnApplyLeaveForm(LeaveTicket leaveTicket) {
		return enterAllRequiredOnApplyLeaveForm(leaveTicket.getLeaveType(), leaveTicket.getFromDate(),
				leaveTicket.getToDate());
	}
	
	@Step("Click on apply button")
	public LeavePage clickApplyButtonLeavePage() {
		clickApplyButton();
		return this;
	}

}
