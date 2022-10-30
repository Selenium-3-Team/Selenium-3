package pages.OrangeHRM;

import java.util.List;

import core.helper.RandomHelper;
import dataType.OrangeHRM.InfoRecordColumnTitle;
import io.qameta.allure.Step;

public class ViewEmployeeListPage extends PIMPage {

	private static ViewEmployeeListPage instance;

	public static ViewEmployeeListPage newInstance() {
		if (ViewEmployeeListPage.instance == null)
			ViewEmployeeListPage.instance = new ViewEmployeeListPage();
		return ViewEmployeeListPage.instance;
	}

	@Step("Get random value according to column title in View Employee List page")
	public String getRandomValueOfColumn(InfoRecordColumnTitle title) {
		List<String> directoryList = getAllCellValueOfColumn(title.getValue());
		String result = "";
		while (result.trim().isEmpty()) {
			int randomIndex = RandomHelper.getRandomNumber(0, directoryList.size());
			result = directoryList.get(randomIndex);
		}
		return result;
	}

	@Step("Get random employee name in Employee data")
	public String getRandomEmployeeNameInList() {
		return getRandomValueOfColumn(InfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME);
	}

	@Step("Get random job title option in Job title data")
	public String getRandomJobTitleInList() {
		return getRandomValueOfColumn(InfoRecordColumnTitle.JOB_TITLE);
	}

}
