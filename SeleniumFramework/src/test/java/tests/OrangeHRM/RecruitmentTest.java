package tests.OrangeHRM;

import org.testng.annotations.Test;

import core.helper.AssertHelper;
import core.report.Logger;
import dataObject.OrangeHRM.Account;
import dataObject.OrangeHRM.Candidate;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserRoleOption;
import io.qameta.allure.Description;
import pages.OrangeHRM.LoginPage;
import pages.OrangeHRM.ViewCandidatesPage;
import pages.OrangeHRM.ViewEmployeeListPage;
import pages.OrangeHRM.ViewJobVacancyPage;
import tests.TestBase;

public class RecruitmentTest extends TestBase {

	@Test
	@Description("Test case 14: User can search vacancies by vacancy.")
	public void TC14() {

		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ViewJobVacancyPage viewJobVacancy = ViewJobVacancyPage.newInstance();
		ViewCandidatesPage viewCandidates = ViewCandidatesPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Candidate candidate = new Candidate("candidate");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Select Recruitment -> Vacancies.");
		viewCandidates = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.RECRUITMENT);
		viewJobVacancy = viewCandidates.clickTopBarMenuItem(TopBarMenuItem.VACANCIES);

		Logger.info("Step 2: Select vacancy on \"Vacancy\" dropbox list.");
		viewJobVacancy.selectOption(DropdownTitle.VACANCY, candidate.getVacancy());

		Logger.info("Step 3: Click \"Search\" button.");
		viewJobVacancy.clickSearchButton();

		Logger.verify("VP. At least 1 record should be displayed on the \"Record Found\" form.");
		assertHelper.assertFalse(viewJobVacancy.isNoRecordsFoundLabelDisplayed(),
				"At least 1 record should be displayed on the \"Record Found\" form.");
	}

	@Test
	@Description("Test case 15: User can add a candidate.")
	public void TC15() {

		LoginPage loginPage = LoginPage.newInstance();
		ViewEmployeeListPage viewEmployeeListPage = ViewEmployeeListPage.newInstance();
		ViewCandidatesPage viewCandidates = ViewCandidatesPage.newInstance();

		AssertHelper assertHelper = new AssertHelper();
		Account account = new Account(UserRoleOption.ADMIN);
		Candidate candidate = new Candidate("candidate");

		Logger.info("Precondition: Login successfully with a valid account.");
		viewEmployeeListPage = loginPage.loginOrangeHRM(account);

		Logger.info("Step 1: Select Recruitment -> Candidates.");
		viewCandidates = viewEmployeeListPage.clickTabOnLeftPanel(LeftPanelMenuItem.RECRUITMENT);

		Logger.info("Step 2: Click \"Add\" button.");
		viewCandidates.clickAddButton();

		Logger.info("Step 3: Enter fullname on \"Full Name\" textbox.");
		viewCandidates.enterFullName(candidate);

		Logger.info("Step 4: Select vacancy on \"Vacancy\" dropbox list.");
		viewCandidates.selectOption(DropdownTitle.VACANCY, candidate.getVacancy());

		Logger.info("Step 5: Enter email on \"Email\" textbox.");
		viewCandidates.enterValueToTextboxOption(TextBoxTitle.EMAIL, candidate.getEmail());

		Logger.info("Step 6: Click \"Save\" button.");
		viewCandidates.clickSaveButton();

		Logger.verify("VP. Success popup is displyed.");
		assertHelper.assertTrue(viewCandidates.isToastSuccessMessageDisplayed(), "Success popup is displyed.");

		Logger.info("Step 7: Click on \"Candidates\" tab.");
		viewCandidates.clickTopBarMenuItem(TopBarMenuItem.CANDIDATES);

		Logger.verify("VP. The cadidate is displayed in record list.");
		assertHelper.assertTrue(viewCandidates.isDisplayedCadidateNameInRecordList(candidate), "The cadidate is displayed on \"Candidates\" table.");
		
		Logger.info("Post-condition: Delete the candidate created.");
		viewCandidates.deleteCadidate(candidate);

	}

}
