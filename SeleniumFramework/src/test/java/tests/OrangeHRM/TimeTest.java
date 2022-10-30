package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.LeaveTicket;
import dataType.OrangeHRM.FrameTitle;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.AttendanceSummaryReportCriteriaPage;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewEmployeeTimesheetPage;
import tests.TestBase;

public class TimeTest extends TestBase {

	@Test
	@Description("Test case 13: User can view attendance summary report.")
	public void TC13() {

		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		AttendanceSummaryReportCriteriaPage attendanceSummaryReportCriteriaPage = AttendanceSummaryReportCriteriaPage.newInstance();
		ViewEmployeeTimesheetPage viewEmployeeTimesheetPage = ViewEmployeeTimesheetPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		LeaveTicket leaveTicket = new LeaveTicket("leaveTicket");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Select Time -> Report -> Select Attendance Summary.");
		viewEmployeeTimesheetPage = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.TIME);
		attendanceSummaryReportCriteriaPage = viewEmployeeTimesheetPage.clickTopBarMenuItem("Reports/Attendance Summary", "/");

		Logger.verify("VP. The \"Attendance Total Summary Report\" form should be displayed.");
		assertHelper.assertEquals(attendanceSummaryReportCriteriaPage.getFrameTitleDisplayed(), FrameTitle.ATTENDANCE_SUMMARY.getValue(),
				"The \"Attendance Total Summary Report\" form should be displayed.");

		Logger.info("Step 2: Enter from date on \"From date\" textbox.");
		attendanceSummaryReportCriteriaPage.enterFrom(leaveTicket.getFromDate());
		
		Logger.info("Step 3: Enter to date on \"To date\" textbox.");
		attendanceSummaryReportCriteriaPage.enterTo(leaveTicket.getToDate());

		Logger.info("Step 4: Click \"View\" button.");
		attendanceSummaryReportCriteriaPage.clickViewButton();

		Logger.verify("VP. At least 1 record should be displayed on the \"Record Found\" form.");
		assertHelper.assertFalse(attendanceSummaryReportCriteriaPage.isNoRecordsFoundLabelDisplayed(), "At least 1 record should be displayed on the \"Record Found\" form.");
		
	}

}
