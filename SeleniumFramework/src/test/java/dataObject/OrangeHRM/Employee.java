package dataObject.OrangeHRM;

import core.helper.RandomHelper;

public class Employee {

	private String firstName;
	private String middleName;
	private String lastName;
	private String id;

	public Employee() {
		this.firstName = RandomHelper.getRandomString("first-name");
		this.middleName = RandomHelper.getRandomString("middle-name");
		this.lastName = RandomHelper.getRandomString("last-name");
		this.id = RandomHelper.getRandomString("id");
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
