package frames.OrangeHRM;

import java.util.List;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Image;
import core.element.wrapper.Label;
import core.element.wrapper.Table;
import core.element.wrapper.TextBox;
import io.qameta.allure.Step;

public class GeneralFrame {

	protected final Table employeeInfoRecord = new Table("//div[@class='orangehrm-container']");
	protected final Element drpOption = new Element("//div[.='%s']//following-sibling::div");
	protected final Label lblSelectedOption = new Label(
			drpOption.getLocatorAsString() + "//div[@class='oxd-select-text-input']");
	protected final Label lblOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	protected final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");
	protected final Element infoRecordRow = new Element(
			"//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");
	protected final Label lblFrameTitle = new Label(
			"//*[contains(@class,'-title') and contains(@class,'oxd-text') and not(contains(@class,'sub-title'))]");
	protected final TextBox txtOption = new TextBox(
			"//label[normalize-space(.)='%s']/parent::div/following-sibling::div//input");
	protected final Label lblInforRecordColumns = new Label("//div[@role='columnheader']");
	protected final Label lblCellFollowingIndex = new Label(
			"//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][%s]/div");
	protected final Button btnDeleteRecord = new Button(
			infoRecordRow.getLocatorAsString() + "/following-sibling::div//button/i[contains(@class,'bi-trash')]");
	protected final Button btnEditRecord = new Button(infoRecordRow.getLocatorAsString()
			+ "/following-sibling::div//button/i[contains(@class,'bi-pencil-fill')]");
	protected final Image employeeImage = new Image("//div[@class='orangehrm-edit-employee-image-wrapper']//img");
	private final Button btnCancelDelete = new Button(
			"//div[@class='orangehrm-modal-footer']//button[contains(@class,'text ')]");
	private final Button btnConfirmDelete = new Button(
			"//div[@class='orangehrm-modal-footer']//button[contains(@class,'label-danger')]");

	public List<String> getAllInforRecordColumnTitle() {
		return lblInforRecordColumns.getAllTexts();
	}

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

	@Step("Get all cell value of column record table with {0} title")
	public List<String> getAllCellValueOfColumn(String title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getAllTexts();
	}

	@Step("Get all cell value of column record table with {0} title")
	public String getCellValueOfColumn(String title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getText();
	}

	@Step("Check if all cell of {0} column is sorted in alphabel")
	public boolean isAllCellValueOfColumnSortedAlphabet(String title) {
		boolean isSorted = true;
		for (int i = 0; i < getAllCellValueOfColumn(title).size() - 1; i++) {
			if (getAllCellValueOfColumn(title).get(i)
					.compareToIgnoreCase(getAllCellValueOfColumn(title).get(i + 1)) > 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public String getSelectedOption(String drpName) {
		lblSelectedOption.generateDynamic(drpName);
		return lblSelectedOption.getLabelText();
	}

	// Wait methods
	public void waitForLoading() {
		try {
			lblFrameTitle.waitForDisplayed();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void waitForFrameTitleDisplayed() {
		lblFrameTitle.waitForDisplayed();
	}

	// Click|Select methods
	public void clickDropdownOption(String drpName) {
		drpOption.generateDynamic(drpName);
		drpOption.click();
	}

	public void selectOption(String optionName) {
		lblOption.generateDynamic(optionName);
		lblOption.click();
	}

	public void clickDeleteInfoRecord(String firstCellValue, String secondCellValue, String thridCellValue) {
		btnDeleteRecord.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		btnDeleteRecord.click();
	}

	public void clickEditInfoRecord(String firstCellValue, String secondCellValue, String thridCellValue) {
		btnEditRecord.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		btnEditRecord.click();
	}

	public void clickEmployeeImage() {
		employeeImage.click();
	}

	public void clickConfirmDeleteButton() {
		btnConfirmDelete.click();
	}

	public void clickCancelDeleteButton() {
		btnCancelDelete.click();
	}

	// Enter methods
	public void enterValueToTextboxOption(String txtName, String value) {
		txtOption.generateDynamic(txtName);
		txtOption.clearByHotKeys();
		txtOption.sendKeys(value);
	}

	// Verify methods
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}

	public boolean isInfoRecordDisplayedInRecordList(String firstCellValue, String secondCellValue,
			String thridCellValue) {
		infoRecordRow.generateDynamic(firstCellValue, secondCellValue, thridCellValue);
		return infoRecordRow.isDisplayed();
	}

	public boolean isFrameTitleDisplayed() {
		return lblFrameTitle.isDisplayed();
	}

}
