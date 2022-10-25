package pages.OrangeHRM;

public class ViewSystemUsersPage extends AdminPage {

	private static ViewSystemUsersPage instance;

	public static ViewSystemUsersPage newInstance() {
		if (ViewSystemUsersPage.instance == null)
			ViewSystemUsersPage.instance = new ViewSystemUsersPage();
		return ViewSystemUsersPage.instance;
	}

}
