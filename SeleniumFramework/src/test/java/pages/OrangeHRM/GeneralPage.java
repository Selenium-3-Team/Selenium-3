package pages.OrangeHRM;

import java.util.List;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Image;
import core.element.wrapper.Label;
import core.element.wrapper.TextBox;
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

	// Element type
	protected final Element iconLoading = new Element("//div[@class='oxd-loading-spinner']");
	protected final Element drpUser = new Element("//li[@class='oxd-userdropdown']");
	protected final Element topBarMenuItem = new Element(
			"//nav[@aria-label='Topbar Menu']//li[normalize-space(.)='%s']");
	protected final Element leftPanel = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");
	protected final Element drpOption = new Element("//div[.='%s']//following-sibling::div");
	protected final Element infoRecordRow = new Element(
			"//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");

	// Label type
	protected final Label lblCopyRight = new Label("//p[contains(@class,'copyright') and contains(.,'%s')]");
	protected final Label lblVersion = new Label("//p[contains(@class,'copyright') and text()='%s']");
	protected final Label lblHeaderTitle = new Label("//div[@class='oxd-topbar-header-title']//h6[contains(.,'%s')]");
	protected final Label lblUserDrpOption = new Label("//a[@role='menuitem' and text()='%s']");
	protected final Label lblOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	protected final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");
	protected final Label lblInforRecordColumns = new Label("//div[@role='columnheader']");
	protected final Label lblSelectedOption = new Label(
			drpOption.getLocatorAsString() + "//div[@class='oxd-select-text-input']");
	protected final Label lblCellFollowingIndex = new Label(
			"//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][%s]/div");
	protected final Label lblFrameTitle = new Label(
			"//*[contains(@class,'-title') and contains(@class,'oxd-text') and not(contains(@class,'sub-title'))]");
	protected final Label lblToastSuccessMessage = new Label(
			"//p[contains(@class,'toast-message') and .='Successfully Saved']/preceding-sibling::p[contains(@class, 'toast-title') and .='Success']/parent::div[contains(@class, 'toast-content--success')]");

	// Button type
	protected final Button btnSearch = new Button("//button[contains(.,'Search')]");
	protected final Button btnAdd = new Button("//button[contains(.,'Add')]");
	protected final Button btnSave = new Button("//button[contains(.,'Save')]");
	protected final Button btnApply = new Button("//button[contains(.,'Apply')]");
	protected final Button btnEditRecord = new Button(infoRecordRow.getLocatorAsString()
			+ "/following-sibling::div//button/i[contains(@class,'bi-pencil-fill')]");
	protected final Button btnDeleteRecord = new Button(
			infoRecordRow.getLocatorAsString() + "/following-sibling::div//button/i[contains(@class,'bi-trash')]");
	private final Button btnConfirmDelete = new Button(
			"//div[@class='orangehrm-modal-footer']//button[contains(@class,'label-danger')]");

	// TextBox type
	protected final TextBox txtOption = new TextBox(
			"//label[normalize-space(.)='%s']/parent::div/following-sibling::div//input");

	// Image type
	protected final Image employeeImage = new Image("//div[@class='orangehrm-edit-employee-image-wrapper']//img");

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
	public <T> T clickTabOnLeftPanel(LeftPanelMenuItem item) {
		leftPanel.generateDynamic(item.getValue());
		leftPanel.click();
		switch (item) {
		case ADMIN:
			return (T) new ViewSystemUsersPage();
		case PIM:
			return (T) new ViewEmployeeListPage();
		case LEAVE:
			return (T) new ViewLeaveListPage();
		case DIRECTORY:
			return (T) new ViewDirectoryPage();
		default:
			return null;
		}
	}

	@Step("Click item on TopBar menu")
	@SuppressWarnings("unchecked")
	public <T> T clickTopBarMenuItem(String pagePath, String delimiter) {
		String[] pageNames = Utilities.splitString(pagePath, delimiter);
		int length = pageNames.length;
		for (int i = 0; i < length; i++) {
			topBarMenuItem.generateDynamic(pageNames[i]);
			topBarMenuItem.waitForPresent(Constant.VERY_SHORT_TIMEOUT);
			topBarMenuItem.click();
		}
		if (pageNames[length - 1].equals(TopBarMenuItem.CUSTOM_FIELDS.getValue())) {
			return (T) new ListCustomFieldsPage();
		} else {
			return null;
		}
	}

	@Step("Click item on TopBar menu")
	@SuppressWarnings("unchecked")
	public <T> T clickTopBarMenuItem(TopBarMenuItem menuItem) {
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

	@Step("Check toast success message is displayed")
	public boolean isToastSuccessMessageDisplayed() {
		return lblToastSuccessMessage.isDisplayed();
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

	@Step("Check \"No Records Found\" label displayed")
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}

	@Step("Check frame title displayed")
	public boolean isFrameTitleDisplayed() {
		return lblFrameTitle.isDisplayed();
	}

}
