package pages.OrangeHRM;

import java.util.List;

import core.helper.RandomHelper;
import dataType.OrangeHRM.InfoRecordColumnTitle;

public class ViewEmployeeListPage extends PIMPage{

	private static ViewEmployeeListPage instance;

	public static ViewEmployeeListPage newInstance() {
		if (ViewEmployeeListPage.instance == null)
			ViewEmployeeListPage.instance = new ViewEmployeeListPage();
		return ViewEmployeeListPage.instance;
	}
	
	public String getRandomValueOfColumn(InfoRecordColumnTitle title) {
		List<String> directoryList = getAllCellValueOfColumn(title.getValue());
		String result = "";
		while (result.trim().isEmpty()) {
			int randomIndex = RandomHelper.getRandomNumber(0, directoryList.size());
			result = directoryList.get(randomIndex);
		}
		return result;
	}

	public String getRandomEmployeeNameInList() {
		return getRandomValueOfColumn(InfoRecordColumnTitle.FIRST_AND_MIDDILE_NAME);
	}

	public String getRandomJobTitleInList() {
		return getRandomValueOfColumn(InfoRecordColumnTitle.JOB_TITLE);
	}
	
}
