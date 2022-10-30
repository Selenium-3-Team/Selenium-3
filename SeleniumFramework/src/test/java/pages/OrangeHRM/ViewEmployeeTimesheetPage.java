package pages.OrangeHRM;

public class ViewEmployeeTimesheetPage extends TimePage{

	private static ViewEmployeeTimesheetPage instance;

	public static ViewEmployeeTimesheetPage newInstance() {
		if (ViewEmployeeTimesheetPage.instance == null)
			ViewEmployeeTimesheetPage.instance = new ViewEmployeeTimesheetPage();
		return ViewEmployeeTimesheetPage.instance;
	}
	
}
