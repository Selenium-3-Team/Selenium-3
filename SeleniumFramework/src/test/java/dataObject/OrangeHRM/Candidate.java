package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import utils.constant.Constant;

public class Candidate extends PersonalInfo {

	private String vacancy;
	private String email;
	private String contactNumber;
	private String resume;
	private String keywords;
	private String dateOfApplication;
	private String notes;
	private String consentToKeepData;

	public Candidate(String key) {
		JsonObject candidate = JsonHelper.getJsonObject(Constant.CANDIDATE_DATA);
		super.setFirstName(candidate.get(key).getAsJsonObject().get("firstName").getAsString());
		super.setMiddleName(candidate.get(key).getAsJsonObject().get("middleName").getAsString());
		super.setLastName(candidate.get(key).getAsJsonObject().get("lastName").getAsString());
		super.setFullName(super.generateFullName());
		this.vacancy = candidate.get(key).getAsJsonObject().get("vacancy").getAsString();
		this.email = candidate.get(key).getAsJsonObject().get("email").getAsString();
		this.contactNumber = candidate.get(key).getAsJsonObject().get("contactNumber").getAsString();
		this.resume = candidate.get(key).getAsJsonObject().get("resume").getAsString();
		this.keywords = candidate.get(key).getAsJsonObject().get("keywords").getAsString();
		this.dateOfApplication = candidate.get(key).getAsJsonObject().get("dateOfApplication").getAsString();
		this.notes = candidate.get(key).getAsJsonObject().get("notes").getAsString();
		this.consentToKeepData = candidate.get(key).getAsJsonObject().get("consentToKeepData").getAsString();
	}

	public Candidate(String firstName, String middleName, String lastName, String vacancy, String email) {
		super.setFirstName(firstName);
		super.setMiddleName(middleName);
		super.setLastName(lastName);
		this.vacancy = vacancy;
		this.email = email;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(String dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getConsentToKeepData() {
		return consentToKeepData;
	}

	public void setConsentToKeepData(String consentToKeepData) {
		this.consentToKeepData = consentToKeepData;
	}

}
