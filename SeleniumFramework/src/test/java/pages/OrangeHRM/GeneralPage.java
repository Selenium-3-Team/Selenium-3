package pages.OrangeHRM;

import java.util.List;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Image;
import core.element.wrapper.Label;
import core.element.wrapper.TextBox;
import core.helper.LocatorHelper;
import dataObject.OrangeHRM.Candidate;
import dataObject.OrangeHRM.CustomField;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.DropdownTitle;
import dataType.OrangeHRM.InfoRecordColumnTitle;
import dataType.OrangeHRM.LeftPanelMenuItem;
import dataType.OrangeHRM.TextBoxTitle;
import dataType.OrangeHRM.TopBarMenuItem;
import dataType.OrangeHRM.UserDrpOption;
import io.qameta.allure.Step;
import utils.constant.Constant;
import utils.helper.Utilities;

public class GeneralPage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_FOLDER_PATH, GeneralPage.class);

	protected final Label lblCopyRight = new Label(locator.getLocator("lblCopyRight"));
	protected final Label lblVersion = new Label(locator.getLocator("lblVersion"));
	protected final Button btnSearch = new Button(locator.getLocator("btnSearch"));
	protected final Button btnAdd = new Button(locator.getLocator("btnAdd"));
	protected final Button btnSave = new Button(locator.getLocator("btnSave"));
	protected final Element iconLoading = new Element(locator.getLocator("iconLoading"));
	protected final Button btnApply = new Button(locator.getLocator("btnApply"));
	protected final TextBox txtOption = new TextBox(locator.getLocator("txtOption"));
	protected final Label lblFrameTitle = new Label(locator.getLocator("lblFrameTitle"));

	// Topbar header
	protected final Label lblHeaderTitle = new Label(locator.getLocator("lblHeaderTitle"));
	protected final Element drpUser = new Element(locator.getLocator("drpUser"));
	protected final Label lblUserDrpOption = new Label(locator.getLocator("lblUserDrpOption"));
	// Topbar menu
	protected final Element topBarMenuItem = new Element(locator.getLocator("topBarMenuItem"));
	// Left panel
	protected final Element leftPanel = new Element(locator.getLocator("leftPanel"));
	// Toast message
	protected final Label lblSavedSuccessMessage = new Label(locator.getLocator("lblSavedSuccessMessage"));
	protected final Label lblUpdatedSuccessMessage = new Label(locator.getLocator("lblUpdatedSuccessMessage"));
	protected final Element drpOption = new Element(locator.getLocator("drpOption"));
	protected final Label lblOption = new Label(locator.getLocator("lblOption"));
	protected final Label lblNoRecordsFound = new Label(locator.getLocator("lblNoRecordsFound"));
	protected final Element infoRecordRow = new Element(locator.getLocator("infoRecordRow"));
	protected final Label lblCellFollowingIndex = new Label(locator.getLocator("lblCellFollowingIndex"));
	protected final Label lblInforRecordColumns = new Label(locator.getLocator("lblInforRecordColumns"));
	protected final Button btnConfirmDelete = new Button(locator.getLocator("btnConfirmDelete"));
	protected final Image employeeImage = new Image(locator.getLocator("employeeImage"));
	protected final Button btnEditRecord = new Button(
			infoRecordRow.getLocatorAsString() + locator.getLocator("btnEditRecord"));
	protected final Button btnDeleteRecord = new Button(
			infoRecordRow.getLocatorAsString() + locator.getLocator("btnDeleteRecord"));
	protected final Label lblSelectedOption = new Label(
			drpOption.getLocatorAsString() + locator.getLocator("lblSelectedOption"));
	protected final Button btnView = new Button(locator.getLocator("btnView"));
	protected final Label lblCandidateName = new Label(locator.getLocator("lblCandidateName"));

	private static GeneralPage instance;

	public static GeneralPage newInstance() {
		if (GeneralPage.instance == null)
			GeneralPage.instance = new GeneralPage();
		return GeneralPage.instance;
	}

	// Wait methods
	@Step("Wait for loading icon dispappear if it is displayed")
	public GeneralPage waitForLoadingIconDisappear() {
		if (iconLoading.isDisplayed(Constant.VERY_SHORT_TIMEOUT)) {
			iconLoading.waitForNotPresent(Constant.VERY_SHORT_TIMEOUT);
		}
		return this;
	}

	// Enter methods
	@Step("Enter {1} value into textbox")
	public GeneralPage enterValueToTextboxOption(TextBoxTitle textBoxTitle, String value) {
		txtOption.generateDynamic(textBoxTitle.getValue());
		txtOption.clearByHotKeys();
		txtOption.sendKeys(value);
		return this;
	}

	// Click methods
	@Step("Click tab on Left panel menu")
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T clickTabOnLeftPanel(LeftPanelMenuItem item) {
		leftPanel.generateDynamic(item.getValue());
		leftPanel.click();
		waitForLoadingIconDisappear();
		switch (item) {
		case ADMIN:
			return (T) new ViewSystemUsersPage();
		case PIM:
			return (T) new ViewEmployeeListPage();
		case LEAVE:
			return (T) new ViewLeaveListPage();
		case DIRECTORY:
			return (T) new ViewDirectoryPage();
		case TIME:
			return (T) new ViewEmployeeTimesheetPage();
		case RECRUITMENT:
			return (T) new ViewCandidatesPage();
		default:
			return null;
		}
	}

	@Step("Click item on TopBar menu")
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T clickTopBarMenuItem(String pagePath, String delimiter) {
		String[] pageNames = Utilities.splitString(pagePath, delimiter);
		int length = pageNames.length;
		for (int i = 0; i < length; i++) {
			topBarMenuItem.generateDynamic(pageNames[i]);
			topBarMenuItem.waitForPresent(Constant.VERY_SHORT_TIMEOUT);
			topBarMenuItem.click();
		}
		waitForLoadingIconDisappear();
		if (pageNames[length - 1].equals(TopBarMenuItem.CUSTOM_FIELDS.getValue())) {
			return (T) new ListCustomFieldsPage();
		} else if (pageNames[length - 1].equals(TopBarMenuItem.ATTENDANCE_SUMMARY.getValue())) {
			return (T) new AttendanceSummaryReportCriteriaPage();
		} else {
			return null;
		}
	}

	@Step("Click item on TopBar menu")
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T clickTopBarMenuItem(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		topBarMenuItem.click();
		waitForLoadingIconDisappear();
		switch (menuItem) {
		case ADD_EMPLOYEE:
			return (T) new AddEmployeePage();
		case EMPLOYEE_LIST:
			return (T) new ViewEmployeeListPage();
		case CUSTOM_FIELDS:
			return (T) new ListCustomFieldsPage();
		case APPLY:
			return (T) new ApplyLeavePage();
		case MYLEAVE:
			return (T) new ViewMyLeaveListPage();
		case VACANCIES:
			return (T) new ViewJobVacancyPage();
		case CANDIDATES:
			return (T) new ViewCandidatesPage();
		default:
			return null;
		}
	}

	@Step("Click User dropdown")
	public GeneralPage clickUserDropdown() {
		drpUser.click();
		return this;
	}

	@Step("Click Employee image")
	public ViewPhotographPage clickEmployeeImage() {
		employeeImage.click();
		return new ViewPhotographPage();
	}

	@Step("Click Edit info record button")
	public ViewPersonalDetailsPage clickEditInfoRecord(Employee employee) {
		return clickEditInfoRecord(employee.getEmloyeeId(),
				String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
	}

	@Step("Click Delete info record button")
	public GeneralPage clickDeleteInfoRecord(String firstCellValue, String secondCellValue, String thridCellValue) {
		btnDeleteRecord.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		btnDeleteRecord.click();
		return this;
	}

	@Step("Delete info record on Custom field")
	public GeneralPage clickDeleteInfoRecord(Candidate candidate) {
		clickDeleteInfoRecord(candidate.getVacancy(), candidate.getFullName(), candidate.getDateOfApplication());
		clickConfirmDeleteButton();
		return this;
	}

	@Step("Click Confirm button on Delete form")
	public GeneralPage clickConfirmDeleteButton() {
		btnConfirmDelete.click();
		return this;
	}

	@Step("Click {0} dropdown option")
	public GeneralPage clickDropdownOption(String drpName) {
		drpOption.generateDynamic(drpName);
		drpOption.click();
		return this;
	}

	@Step("Click Apply button")
	public GeneralPage clickApplyButton() {
		btnApply.click();
		return this;
	}

	@Step("Click Add button")
	public GeneralPage clickAddButton() {
		btnAdd.click();
		return this;
	}

	@Step("Click Save button")
	public GeneralPage clickSaveButton() {
		btnSave.click();
		return this;
	}

	@Step("Click Search button")
	public GeneralPage clickSearchButton() {
		btnSearch.click();
		return this;
	}

	@Step("Click View button")
	public GeneralPage clickViewButton() {
		btnView.click();
		waitForLoadingIconDisappear();
		return this;
	}

	// Select methods
	@Step("Select {0} option in User dropdown")
	public GeneralPage selectOptionInUserDrp(String optionName) {
		lblUserDrpOption.generateDynamic(optionName);
		lblUserDrpOption.click();
		return this;
	}

	public UpdatePasswordPage selectChangePasswordOption() {
		selectOptionInUserDrp(UserDrpOption.CHANGE_PASSWORD.getValue());
		return new UpdatePasswordPage();
	}

	public LoginPage selectLogoutOption() {
		selectOptionInUserDrp(UserDrpOption.LOGOUT.getValue());
		return new LoginPage();
	}

	@Step("Select {0} label option")
	public GeneralPage selectLblOption(String optionName) {
		lblOption.generateDynamic(optionName);
		lblOption.click();
		return this;
	}

	// Get methods
	@Step("Get label option")
	public String getSelectedOption(DropdownTitle title) {
		lblSelectedOption.generateDynamic(title.getValue());
		return lblSelectedOption.getLabelText();
	}

	@Step("Get cell value of {0} column")
	public String getCellValueOfColumn(String title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getText();
	}

	@Step("Get all info record column title")
	public List<String> getAllInforRecordColumnTitle() {
		return lblInforRecordColumns.getAllTexts();
	}

	@Step("Get all cell value of {0} column")
	public List<String> getAllCellValueOfColumn(String title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getAllTexts();
	}

	@Step("Get frame title")
	public String getFrameTitleDisplayed() {
		return lblFrameTitle.getText();
	}

	// Multiple action methods
	@Step("Logout OrangeHRM page")
	public LoginPage logoutOrangeHRM() {
		clickUserDropdown();
		selectLogoutOption();
		return new LoginPage();
	}

	@Step("Delete info record on Custom field")
	public GeneralPage clickDeleteInfoRecord(CustomField customField) {
		clickDeleteInfoRecord(customField.getFieldName(), customField.getScreen(), customField.getType());
		clickConfirmDeleteButton();
		return this;
	}

	@Step("Edit info record on Custom field")
	public ViewPersonalDetailsPage clickEditInfoRecord(String firstCellValue, String secondCellValue,
			String thridCellValue) {
		btnEditRecord.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		btnEditRecord.click();
		waitForLoadingIconDisappear();
		return new ViewPersonalDetailsPage();
	}

	public GeneralPage selectOption(DropdownTitle dropdownTitle, String value) {
		clickDropdownOption(dropdownTitle.getValue());
		selectLblOption(value);
		return this;
	}

	@Step("Find index of {0} info record column")
	public int findInforRecordColumnIndex(String title) {
		int index = 1;
		for (int i = 0; i < getAllInforRecordColumnTitle().size(); i++) {
			if (getAllInforRecordColumnTitle().get(i).equals(title)) {
				index = i + 1;
				break;
			} else {
				index = -1;
			}
		}
		return index;
	}

	// Verify methods
	@Step("Check top bar menu item is displayed")
	public boolean isTopBarMenuItemDisplayed(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		return topBarMenuItem.isDisplayed();
	}

	@Step("Check top bar menu item is actived")
	public boolean isTopBarMenuItemActived(TopBarMenuItem menuItem) {
		topBarMenuItem.generateDynamic(menuItem.getValue());
		return topBarMenuItem.isAttributeValueDisplayed("class", "--visited");
	}

	@Step("Check toast saved success message is displayed")
	public boolean isSavedSuccessMessageDisplayed() {
		return lblSavedSuccessMessage.isDisplayed();
	}

	@Step("Check toast updated success message is displayed")
	public boolean isUpdatedSuccessMessageDisplayed() {
		return lblUpdatedSuccessMessage.isDisplayed();
	}

	@Step("Check text CopyRight includes {0} and {1}")
	public boolean isCopyRightTextDisplayed(String companyName, String appVersion) {
		lblCopyRight.generateDynamic(companyName);
		lblVersion.generateDynamic(appVersion);
		return lblCopyRight.isDisplayed() && lblVersion.isDisplayed();
	}

	@Step("Check header title {0} is displayed")
	public boolean isHeaderTitleDisplayed(LeftPanelMenuItem item) {
		lblHeaderTitle.generateDynamic(item.getValue());
		return lblHeaderTitle.isDisplayed();
	}

	@Step("Check all cell value of column sorted alphabet")
	public boolean isAllCellValueOfColumnSortedAlphabet(InfoRecordColumnTitle title) {
		boolean isSorted = true;
		for (int i = 0; i < getAllCellValueOfColumn(title.getValue()).size() - 1; i++) {
			if (getAllCellValueOfColumn(title.getValue()).get(i)
					.compareToIgnoreCase(getAllCellValueOfColumn(title.getValue()).get(i + 1)) > 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	@Step("Check info record displayed in record list")
	private boolean isInfoRecordDisplayedInRecordList(String firstCellValue, String secondCellValue,
			String thridCellValue) {
		infoRecordRow.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		return infoRecordRow.isDisplayed();
	}

	public boolean isInfoRecordDisplayedInRecordList(CustomField customField) {
		return isInfoRecordDisplayedInRecordList(customField.getFieldName(), customField.getScreen(),
				customField.getType());
	}

	public boolean isInfoRecordDisplayedInRecordList(Employee employee) {
		return isInfoRecordDisplayedInRecordList(employee.getEmloyeeId(),
				String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
	}

	public boolean isDisplayedCadidateNameInRecordList(Candidate candidate) {
		lblCandidateName.generateDynamic(candidate.getFirstName(), candidate.getMiddleName(), candidate.getLastName());
		return lblCandidateName.isDisplayed();
	}

	@Step("Check \"No Records Found\" label displayed")
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}

	@Step("Check frame title displayed")
	public boolean isFrameTitleDisplayed() {
		return lblFrameTitle.isDisplayed();
	}

}
