package frames.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.element.wrapper.Table;
import core.element.wrapper.TextBox;
import dataObject.OrangeHRM.Employee;
import utils.constant.Constant;

public class GeneralFrame {

	protected final Table employeeInfoRecord = new Table("//div[@class='orangehrm-container']");
	protected final Button btnSearch = new Button("//button[contains(.,'Search')]");
	protected final Button btnAdd = new Button("//button[contains(.,'Add')]");
	protected final Button btnSave = new Button("//button[contains(.,'Save')]");
	protected final Element drpOption = new Element("//div[.='%s']//following-sibling::div");
	protected final Label lblOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	protected final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");
	protected final Element employeeInfoRow = new Element("//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");
	protected final Element iconLoading = new Element("//div[@class='oxd-loading-spinner']");
	protected final Label lblFrameTitle = new Label("//*[contains(@class,'-title') and contains(@class,'oxd-text') and not(contains(@class,'sub-title'))]");
	protected final TextBox txtOption = new TextBox("//label[normalize-space(.)='%s']/parent::div/following-sibling::div//input");

	// Wait methods
	public void waitForLoading() {
		try {
			waitForLoadingIconDisappear();
			lblFrameTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void waitForFrameTitleDisplayed() {
		lblFrameTitle.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
	}
	
	public void waitForLoadingIconDisappear() {
		if (iconLoading.isDisplayed()) {
			iconLoading.waitForNotPresent(Constant.DEFAULT_TIMEOUT);
		}
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
	
	public void clickSearchButton() {
		btnSearch.click();
	}
	
	public void clickSaveButton() {
		btnSave.click();
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
		employeeInfoRow.generateDynamic(employee.getId(),String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		return employeeInfoRow.isDisplayed();
	}

	public boolean isFrameTitleDisplayed() {
		return lblFrameTitle.isDisplayed();
	}

}
