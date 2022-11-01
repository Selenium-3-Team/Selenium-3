package dataObject.OrangeHRM;

import core.helper.RandomHelper;

public class PersonalInfo {

	protected String fullName;
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String nickName;
	protected String otherId;
	protected String driverLicenseNumber;
	protected String licenseExpiryDate;
	protected String ssnNumber;
	protected String sinNumber;
	protected String nationality;
	protected String maritalStatus;
	protected String dateOfBirth;
	protected String gender;
	protected String militaryService;
	protected boolean smoker;
	protected String bloodType;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}

	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}

	public String getSsnNumber() {
		return ssnNumber;
	}

	public void setSsnNumber(String ssnNumber) {
		this.ssnNumber = ssnNumber;
	}

	public String getSinNumber() {
		return sinNumber;
	}

	public void setSinNumber(String sinNumber) {
		this.sinNumber = sinNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(String militaryService) {
		this.militaryService = militaryService;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String generateFullName() {
		String fullName = "";
		fullName += this.firstName.isEmpty() ? "" : this.firstName;
		fullName += this.middleName.isEmpty() ? "" : " " + this.middleName;
		fullName += this.lastName.isEmpty() ? "" : " " + this.lastName;
		return fullName.trim();
	}

	public PersonalInfo() {
		this.firstName = RandomHelper.getRandomString("first");
		this.middleName = RandomHelper.getRandomString("middle");
		this.lastName = RandomHelper.getRandomString("last");
		this.fullName = this.generateFullName();
	}

	public PersonalInfo(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.fullName = this.generateFullName();
	}

}
