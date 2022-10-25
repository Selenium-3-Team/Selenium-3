package pages.OrangeHRM;

import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.TextBoxTitle;
import io.qameta.allure.Step;

public class LeavePage extends GeneralPage {

	private static LeavePage instance;

	public static LeavePage newInstance() {
		if (LeavePage.instance == null)
			LeavePage.instance = new LeavePage();
		return LeavePage.instance;
	}

	@Step("Enter all required information on Apply Leave Form")
	public LeavePage enterAllRequiredOnApplyLeaveForm(String leaveType, String fromDate, String toDate) {
		selectOption(DropdownTitle.LEAVETYPE, leaveType);
		enterValueToTextboxOption(TextBoxTitle.FROMDATE, fromDate);
		enterValueToTextboxOption(TextBoxTitle.TODATE, toDate);
		return this;
	}

	@Step("Enter all required information on Apply Leave Form")
	public LeavePage enterAllRequiredOnApplyLeaveForm(LeaveTicket leaveTicket) {
		return enterAllRequiredOnApplyLeaveForm(leaveTicket.getLeaveType(), leaveTicket.getFromDate(),
				leaveTicket.getToDate());
	}
	
}
