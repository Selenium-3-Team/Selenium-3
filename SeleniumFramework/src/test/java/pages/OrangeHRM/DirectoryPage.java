package pages.OrangeHRM;

public class DirectoryPage extends GeneralPage {

	private static DirectoryPage instance;

	public static DirectoryPage newInstance() {
		if (DirectoryPage.instance == null)
			DirectoryPage.instance = new DirectoryPage();
		return DirectoryPage.instance;
	}

}
