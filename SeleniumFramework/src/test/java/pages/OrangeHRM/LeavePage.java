package pages.OrangeHRM;

import core.element.wrapper.Button;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class LeavePage extends GeneralPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, LeavePage.class);

	private final Button btnCancelLeaveTicket = new Button(locator.getLocator("btnCancelLeaveTicket"));

	private static LeavePage instance;

	public static LeavePage newInstance() {
		if (LeavePage.instance == null)
			LeavePage.instance = new LeavePage();
		return LeavePage.instance;
	}

	@Step("Enter all required information {0} for leaveType, {1} for fromDate, {2} for toDate on Apply Leave Form")
	public LeavePage enterAllRequiredOnApplyLeaveForm(String leaveType, String fromDate, String toDate) {
		selectOption(DropdownTitle.LEAVETYPE, leaveType);
		enterValueToTextboxOption(TextBoxTitle.FROMDATE, fromDate);
		enterValueToTextboxOption(TextBoxTitle.TODATE, toDate);
		return this;
	}

	public LeavePage enterAllRequiredOnApplyLeaveForm(LeaveTicket leaveTicket) {
		return enterAllRequiredOnApplyLeaveForm(leaveTicket.getLeaveType(), leaveTicket.getFromDate(), leaveTicket.getToDate());
	}

	@Step("Cancel a leave ticket")
	public ViewPersonalDetailsPage cancelLeaveTicket(LeaveTicket leaveTicket) {
		btnCancelLeaveTicket.generateDynamic(leaveTicket.getFromDate(), leaveTicket.getToDate());
		btnCancelLeaveTicket.click();
		return new ViewPersonalDetailsPage();
	}

}
