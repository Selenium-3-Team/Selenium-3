package pages.OrangeHRM;

public class AdminPage extends GeneralPage {

	private static AdminPage instance;

	public static AdminPage newInstance() {
		if (AdminPage.instance == null)
			AdminPage.instance = new AdminPage();
		return AdminPage.instance;
	}
	
}
