package pages.OrangeHRM;

public class UpdatePasswordPage extends PIMPage{

	private static UpdatePasswordPage instance;

	public static UpdatePasswordPage newInstance() {
		if (UpdatePasswordPage.instance == null)
			UpdatePasswordPage.instance = new UpdatePasswordPage();
		return UpdatePasswordPage.instance;
	}	
	
}
