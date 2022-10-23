package dataObject.OrangeHRM;

import com.google.gson.JsonObject;

import core.helper.JsonHelper;
import utils.constant.Constant;

public class LeaveTicket {

	private String leaveType;
	private String fromDate;
	private String toDate;

	public LeaveTicket(String key) {
		JsonObject leaveTicket = JsonHelper.getJsonObject(Constant.LEAVE_DATA);
		this.leaveType = leaveTicket.get(key).getAsJsonObject().get("leavetype").getAsString();
		this.fromDate = leaveTicket.get(key).getAsJsonObject().get("fromdate").getAsString();
		this.toDate = leaveTicket.get(key).getAsJsonObject().get("todate").getAsString();
	}

	public LeaveTicket(String leaveType, String fromDate, String toDate) {
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
