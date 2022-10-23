package frames.OrangeHRM;

import java.util.List;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Image;
import core.element.wrapper.Label;
import core.element.wrapper.Table;
import core.element.wrapper.TextBox;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInfoRecordColumnTitle;
import utils.constant.Constant;

public class GeneralFrame {

	protected final Table employeeInfoRecord = new Table("//div[@class='orangehrm-container']");
	protected final Element drpOption = new Element("//div[.='%s']//following-sibling::div");
	protected final	Label lblSelectedOption = new Label(drpOption.getLocatorAsString() + "//div[@class='oxd-select-text-input']");
	protected final Label lblOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	protected final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");
	protected final Element employeeInfoRow = new Element(
			"//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");
	protected final Label lblFrameTitle = new Label(
			"//*[contains(@class,'-title') and contains(@class,'oxd-text') and not(contains(@class,'sub-title'))]");
	protected final TextBox txtOption = new TextBox(
			"//label[normalize-space(.)='%s']/parent::div/following-sibling::div//input");
	protected final Label lblEmployeeInforRecordColumns = new Label("//div[@role='columnheader']");
	protected final Label lblCellFollowingIndex = new Label(
			"//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][%s]/div");
	protected final Button btnDeleteRecord = new Button(
			employeeInfoRow.getLocatorAsString() + "/following-sibling::div//button/i[contains(@class,'bi-trash')]");
	protected final Button btnEditRecord = new Button(employeeInfoRow.getLocatorAsString()
			+ "/following-sibling::div//button/i[contains(@class,'bi-pencil-fill')]");
	protected final Image employeeImage = new Image("//div[@class='orangehrm-edit-employee-image-wrapper']//img");

	public List<String> getAllEmployeeInforRecordColumnTitle() {
		return lblEmployeeInforRecordColumns.getAllTexts();
	}

	public int findEmployeeInforRecordColumnIndex(EmployeeInfoRecordColumnTitle title) {
		int index = 1;
		for (int i = 0; i < getAllEmployeeInforRecordColumnTitle().size(); i++) {
			if (getAllEmployeeInforRecordColumnTitle().get(i).equals(title.getValue())) {
				index = i + 1;
				break;
			} else {
				index = -1;
			}
		}
		return index;
	}

	public List<String> getAllCellValueOfColumn(EmployeeInfoRecordColumnTitle title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findEmployeeInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getAllTexts();
	}

	public String getCellValueOfColumn(EmployeeInfoRecordColumnTitle title) {
		lblCellFollowingIndex.generateDynamic(Integer.toString(findEmployeeInforRecordColumnIndex(title)));
		return lblCellFollowingIndex.getText();
	}

	public String getSelectedOption(String drpName) {
		lblSelectedOption.generateDynamic(drpName);
		return lblSelectedOption.getLabelText();
	}
	
	// Wait methods
	public void waitForLoading() {
		try {
			lblFrameTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void waitForFrameTitleDisplayed() {
		lblFrameTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
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

	public void clickDeleteEmployeeInfoRecord(Employee employee) {
		btnDeleteRecord.generateDynamic(employee.getId(),
				String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		btnDeleteRecord.click();
	}

	public void clickEditEmployeeInfoRecord(Employee employee) {
		btnEditRecord.generateDynamic(employee.getId(),
				String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		btnEditRecord.click();
	}

	public void clickEmployeeImage() {
		employeeImage.click();
	}
	
	// Enter methods
	public void enterValueToTextboxOption(String txtName, String value) {
		txtOption.generateDynamic(txtName);
		txtOption.sendKeys(value);
	}

	// Verify methods
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}

	public boolean isEmployeeDisplayedInEmployeeList(Employee employee) {
		employeeInfoRow.generateDynamic(employee.getId(),
				String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		return employeeInfoRow.isDisplayed();
	}

	public boolean isFrameTitleDisplayed() {
		return lblFrameTitle.isDisplayed();
	}

	public boolean isAllCellValueOfColumnSortedAlphabet(EmployeeInfoRecordColumnTitle title) {
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

}
