package pages.OrangeHRM;

public class ViewJobDetailsPage extends PIMPage{

	private static ViewJobDetailsPage instance;

	public static ViewJobDetailsPage newInstance() {
		if (ViewJobDetailsPage.instance == null)
			ViewJobDetailsPage.instance = new ViewJobDetailsPage();
		return ViewJobDetailsPage.instance;
	}
	
}
