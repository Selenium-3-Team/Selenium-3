package frames.OrangeHRM;

import core.element.base.Element;
import core.element.wrapper.Button;
import core.element.wrapper.Label;
import core.element.wrapper.Table;
import core.element.wrapper.TextBox;
import dataObject.OrangeHRM.Employee;
import dataType.OrangeHRM.EmployeeInformation;
import io.qameta.allure.Step;
import utils.constant.Constant;

public class GeneralFrame {
	
	protected final Table employeeInfoRecord = new Table("//div[@class='orangehrm-container']");
	protected final Button btnSearch = new Button("//button[contains(.,'Search')]");
	protected final Button btnAdd = new Button("//button[contains(.,'Add')]");
	protected final Button btnSave = new Button("//button[contains(.,'Save')]");
	protected final Element drpOption = new Element("//div[.='%s']//following-sibling::div");
	protected final Label lblOption = new Label("//div[@class='oxd-select-option']//span[text()='%s']");
	protected final Label lblNoRecordsFound = new Label("//span[text()='No Records Found']");
	protected final TextBox txtInEmployeeInformation = new TextBox("//label[normalize-space(text())='%s']/parent::div/following-sibling::div//input");
	protected final Element employeeInfoRow = new Element("//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-cell') and normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']/following-sibling::div[normalize-space(.)='%s']");
	
	@Step("Wait for frame loading")
	public void waitForLoading() {
		try {
			btnSearch.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
			btnAdd.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
			employeeInfoRecord.waitForDisplayed(Constant.DEFAULT_TIMEOUT);
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
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
	
	public boolean isNoRecordsFoundLabelDisplayed() {
		return lblNoRecordsFound.isDisplayed();
	}
	
	public void clickSaveButton() {
		btnSave.click();
	}
	
	
	public void enterValueToEmployeeInformationTextbox(EmployeeInformation title, String value) {
		txtInEmployeeInformation.generateDynamic(title.getValue());
		txtInEmployeeInformation.sendKeys(value);
	}
	
	public boolean isEmployeeDisplayedInEmployeeList(Employee employee) {
		employeeInfoRow.generateDynamic(employee.getId(), String.format("%s %s", employee.getFirstName(), employee.getMiddleName()), employee.getLastName());
		return employeeInfoRow.isDisplayed();
	}
	
}
