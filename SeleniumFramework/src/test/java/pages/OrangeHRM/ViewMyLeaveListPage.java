package pages.OrangeHRM;

public class ViewMyLeaveListPage extends LeavePage{

	private static ViewMyLeaveListPage instance;

	public static ViewMyLeaveListPage newInstance() {
		if (ViewMyLeaveListPage.instance == null)
			ViewMyLeaveListPage.instance = new ViewMyLeaveListPage();
		return ViewMyLeaveListPage.instance;
	}
	
}
