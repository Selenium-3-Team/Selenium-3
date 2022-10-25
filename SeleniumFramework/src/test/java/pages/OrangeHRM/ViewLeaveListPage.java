package pages.OrangeHRM;

public class ViewLeaveListPage extends LeavePage{

	private static ViewLeaveListPage instance;

	public static ViewLeaveListPage newInstance() {
		if (ViewLeaveListPage.instance == null)
			ViewLeaveListPage.instance = new ViewLeaveListPage();
		return ViewLeaveListPage.instance;
	}
	
}
