package pages.OrangeHRM;

public class ViewJobVacancyPage extends RecruitmentPage{

	private static ViewJobVacancyPage instance;

	public static ViewJobVacancyPage newInstance() {
		if (ViewJobVacancyPage.instance == null)
			ViewJobVacancyPage.instance = new ViewJobVacancyPage();
		return ViewJobVacancyPage.instance;
	}
	
}
