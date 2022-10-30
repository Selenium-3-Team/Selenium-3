package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import core.helper.RandomHelper;
import utils.constant.Constant;

public class ContactDetails {

	// Address
	protected String street1;
	protected String street2;
	protected String city;
	protected String province;
	protected String postalCode;
	protected String country;
	// Telephone
	protected String homePhone;
	protected String mobilePhone;
	protected String workPhone;
	// Email
	protected String workEmail;
	protected String otherEmail;

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public String getOtherEmail() {
		return otherEmail;
	}

	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}

	public ContactDetails(String key) {
		JsonObject jsonObject = JsonHelper.getJsonObject(Constant.CONTACT_DETAILS_DATA);
		this.street1 = jsonObject.get(key).getAsJsonObject().get("street1").getAsString();
		this.street2 = jsonObject.get(key).getAsJsonObject().get("street2").getAsString();
		this.city = jsonObject.get(key).getAsJsonObject().get("city").getAsString();
		this.province = jsonObject.get(key).getAsJsonObject().get("province").getAsString();
		this.postalCode = jsonObject.get(key).getAsJsonObject().get("postalCode").getAsString();
		this.country = jsonObject.get(key).getAsJsonObject().get("country").getAsString();
		this.homePhone = jsonObject.get(key).getAsJsonObject().get("homePhone").getAsString();
		this.mobilePhone = jsonObject.get(key).getAsJsonObject().get("mobilePhone").getAsString();
		this.workPhone = jsonObject.get(key).getAsJsonObject().get("workPhone").getAsString();
		this.workEmail = RandomHelper.getRandomString("") + jsonObject.get(key).getAsJsonObject().get("workEmail").getAsString();
		this.otherEmail = RandomHelper.getRandomString("") + jsonObject.get(key).getAsJsonObject().get("otherEmail").getAsString();
	}

}
