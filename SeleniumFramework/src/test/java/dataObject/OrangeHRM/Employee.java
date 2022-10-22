package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import core.helper.RandomHelper;
import dataType.OrangeHRM.EmployeeData;
import dataType.OrangeHRM.UserRole;
import utils.constant.Constant;
import utils.helper.Utilities;
import core.utilities.CoreUtilities;

public class Employee {

	private String firstName;
	private String middleName;
	private String lastName;
	private String id;

	public Employee() {
		this.firstName = RandomHelper.getRandomString("first");
		this.middleName = RandomHelper.getRandomString("middle");
		this.lastName = RandomHelper.getRandomString("last");
		this.id = Utilities.generateEmployeeId();
	}

	public Employee(EmployeeData employeeData) {
		JsonObject employee = JsonHelper.getJsonObject(CoreUtilities.getProjectPath() + Constant.EMPLOYEE_DATA);
		String key = employeeData.getValue().toLowerCase();
		this.firstName = employee.get(key).getAsJsonObject().get("firstName").getAsString();
		this.middleName = employee.get(key).getAsJsonObject().get("middleName").getAsString();
		this.lastName = employee.get(key).getAsJsonObject().get("lastName").getAsString();
		this.id = employee.get(key).getAsJsonObject().get("id").getAsString();
	}

	public Employee(String firstName, String middleName, String lastName, String id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
