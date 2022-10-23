package dataObject.OrangeHRM;

import core.helper.RandomHelper;
import utils.helper.Utilities;

public class Employee {

	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String id;
	private String jobTitle;
	private String employeeStatus;
	private String subUnit;
	private String supervior;
	private String nationality;
	private String officeLocation;
	private String maritalStatus;
	private String dateOfBirth;
	private String gender;

	public Employee() {
		this.firstName = RandomHelper.getRandomString("first");
		this.middleName = RandomHelper.getRandomString("middle");
		this.lastName = RandomHelper.getRandomString("last");
		this.id = Utilities.generateEmployeeId();
		this.fullName = String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
	}

	public Employee(String firstName, String middleName, String lastName, String id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.id = id;
		this.fullName = String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
	}

	public Employee(String fullName, String jobTitle, String officeLocation) {
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.officeLocation = officeLocation;
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

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getSubUnit() {
		return subUnit;
	}

	public void setSubUnit(String subUnit) {
		this.subUnit = subUnit;
	}

	public String getSupervior() {
		return supervior;
	}

	public void setSupervior(String supervior) {
		this.supervior = supervior;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
