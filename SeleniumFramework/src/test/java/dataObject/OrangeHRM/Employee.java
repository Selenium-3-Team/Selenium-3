package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import utils.constant.Constant;
import utils.helper.Utilities;

public class Employee extends PersonalInfo {

	private String emloyeeId;
	private String supervior;
	private Job job = new Job();

	public Employee() {
		super();
		this.emloyeeId = Utilities.generateEmployeeId();
	}

	public Employee(String key) {
		JsonObject employee = JsonHelper.getJsonObject(Constant.EMPLOYEE_DATA);
		super.setFirstName(employee.get(key).getAsJsonObject().get("firstName").getAsString());
		super.setMiddleName(employee.get(key).getAsJsonObject().get("middleName").getAsString());
		super.setLastName(employee.get(key).getAsJsonObject().get("lastName").getAsString());
		super.setFullName(String.format("%s %s %s", this.firstName, this.middleName, this.lastName));
		super.setNationality(employee.get(key).getAsJsonObject().get("nationality").getAsString());
		super.setMaritalStatus(employee.get(key).getAsJsonObject().get("maritalStatus").getAsString());
		super.setDateOfBirth(employee.get(key).getAsJsonObject().get("dateOfBirth").getAsString());
		super.setGender(employee.get(key).getAsJsonObject().get("gender").getAsString());
		this.emloyeeId = Utilities.generateEmployeeId();
		this.job.setJobTitle(employee.get(key).getAsJsonObject().get("jobTitle").getAsString());
		this.job.setEmployeeStatus(employee.get(key).getAsJsonObject().get("employeeStatus").getAsString());
		this.job.setSubUnit(employee.get(key).getAsJsonObject().get("subUnit").getAsString());
		this.job.setOfficeLocation(employee.get(key).getAsJsonObject().get("officeLocation").getAsString());
		this.supervior = employee.get(key).getAsJsonObject().get("supervior").getAsString();
	}

	public Employee(String fullName, String jobTitle, String officeLocation) {
		super.setFullName(fullName);
		this.job.setJobTitle(jobTitle);
		this.job.setOfficeLocation(officeLocation);
	}

	public String getEmloyeeId() {
		return emloyeeId;
	}

	public void setEmloyeeId(String emloyeeId) {
		this.emloyeeId = emloyeeId;
	}

	public String getJobTitle() {
		return this.job.getJobTitle();
	}

	public void setJobTitle(String jobTitle) {
		this.job.setJobTitle(jobTitle);
	}

	public String getEmployeeStatus() {
		return this.job.getEmployeeStatus();
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.job.setEmployeeStatus(employeeStatus);
	}

	public String getSubUnit() {
		return this.job.getSubUnit();
	}

	public void setSubUnit(String subUnit) {
		this.job.setSubUnit(subUnit);
	}

	public String getSupervior() {
		return supervior;
	}

	public void setSupervior(String supervior) {
		this.supervior = supervior;
	}

	public String getOfficeLocation() {
		return this.job.getOfficeLocation();
	}

	public void setOfficeLocation(String officeLocation) {
		this.job.setOfficeLocation(officeLocation);
	}

}
