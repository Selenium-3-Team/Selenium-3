package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import utils.constant.Constant;

public class Candidate {

	private String firstName;
	private String middleName;
	private String lastName;
	private String vacancy;
	private String email;

	public Candidate(String key) {
		JsonObject candidate = JsonHelper.getJsonObject(Constant.CANDIDATE_DATA);
		this.firstName = candidate.get(key).getAsJsonObject().get("firstName").getAsString();
		this.middleName = candidate.get(key).getAsJsonObject().get("middleName").getAsString();
		this.lastName = candidate.get(key).getAsJsonObject().get("lastName").getAsString();
		this.vacancy = candidate.get(key).getAsJsonObject().get("vacancy").getAsString();
		this.email = candidate.get(key).getAsJsonObject().get("email").getAsString();
	}

	public Candidate(String firstName, String middleName, String lastName, String vacancy, String email) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.vacancy = vacancy;
		this.email = email;
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

}
