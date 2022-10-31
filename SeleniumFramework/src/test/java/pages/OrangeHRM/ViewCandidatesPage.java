package pages.OrangeHRM;

import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Candidate;
import utils.constant.Constant;

public class ViewCandidatesPage extends RecruitmentPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, ViewCandidatesPage.class);

	private final TextBox txtFirstName = new TextBox(locator.getLocator("txtFirstName"));
	private final TextBox txtMiddleName = new TextBox(locator.getLocator("txtMiddleName"));
	private final TextBox txtLastName = new TextBox(locator.getLocator("txtLastName"));

	private static ViewCandidatesPage instance;

	public static ViewCandidatesPage newInstance() {
		if (ViewCandidatesPage.instance == null)
			ViewCandidatesPage.instance = new ViewCandidatesPage();
		return ViewCandidatesPage.instance;
	}

	public ViewCandidatesPage enterFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
		return this;
	}

	public ViewCandidatesPage enterMiddleName(String middleName) {
		txtMiddleName.sendKeys(middleName);
		return this;
	}

	public ViewCandidatesPage enterLastName(String lastName) {
		txtLastName.sendKeys(lastName);
		return this;
	}

	public ViewCandidatesPage enterFullName(String firstName, String middleName, String lastName) {
		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);
		return this;
	}

	public ViewCandidatesPage enterFullName(Candidate candidate) {
		return enterFullName(candidate.getFirstName(), candidate.getMiddleName(), candidate.getLastName());
	}

}
