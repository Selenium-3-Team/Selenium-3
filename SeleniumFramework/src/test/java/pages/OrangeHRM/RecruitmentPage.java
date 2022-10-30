package pages.OrangeHRM;

public class RecruitmentPage extends GeneralPage {

	private static RecruitmentPage instance;

	public static RecruitmentPage newInstance() {
		if (RecruitmentPage.instance == null)
			RecruitmentPage.instance = new RecruitmentPage();
		return RecruitmentPage.instance;
	}

}
