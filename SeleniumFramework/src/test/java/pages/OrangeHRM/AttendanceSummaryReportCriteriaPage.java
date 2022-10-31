package pages.OrangeHRM;

import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import utils.constant.Constant;

public class AttendanceSummaryReportCriteriaPage extends TimePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, AttendanceSummaryReportCriteriaPage.class);

	private final TextBox txtFrom = new TextBox(locator.getLocator("txtFrom"));
	private final TextBox txtTo = new TextBox(locator.getLocator("txtTo"));

	private static AttendanceSummaryReportCriteriaPage instance;

	public static AttendanceSummaryReportCriteriaPage newInstance() {
		if (AttendanceSummaryReportCriteriaPage.instance == null)
			AttendanceSummaryReportCriteriaPage.instance = new AttendanceSummaryReportCriteriaPage();
		return AttendanceSummaryReportCriteriaPage.instance;
	}

	public AttendanceSummaryReportCriteriaPage enterFrom(String fromDate) {
		txtFrom.sendKeys(fromDate);
		return this;
	}

	public AttendanceSummaryReportCriteriaPage enterTo(String toDate) {
		txtTo.sendKeys(toDate);
		return this;
	}

}
