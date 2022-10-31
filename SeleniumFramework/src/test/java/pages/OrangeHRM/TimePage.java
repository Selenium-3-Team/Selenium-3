package pages.OrangeHRM;

public class TimePage extends GeneralPage {

	private static TimePage instance;

	public static TimePage newInstance() {
		if (TimePage.instance == null)
			TimePage.instance = new TimePage();
		return TimePage.instance;
	}

}
