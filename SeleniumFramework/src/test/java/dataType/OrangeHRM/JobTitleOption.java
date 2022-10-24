package dataType.OrangeHRM;

import core.helper.RandomHelper;

public enum JobTitleOption {

	DEFAULT("-- Select --"), ACCOUNT_ASSISTANT("Account Assistant"), CHIEF_EXECUTIVE_OFFICER("Chief Executive Officer"),
	CHIEF_FINANCIAL_OFFICER("Chief Financial Officer"), CHIEF_TECHNICAL_OFFICER("Chief Technical Officer"),
	CONTENT_SPECIALIST("Content Specialist"), CUSTOMER_SUCCESS_MANAGER("Customer Success Manager"),
	DATABASE_ADMINISTRATOR("Database Administrator"), FINANCE_MANAGER("Finance Manager"),
	FINANCIAL_ANALYST("Financial Analyst"), HEAD_OF_SUPPORT("Head of Support"), HR_ASSOCIATE("HR Associate"),
	HR_MANAGER("HR Manager"), IT_MANAGER("IT Manager"), NETWORK_ADMINISTRATOR("Network Administrator"),
	PAROLL_ADMINISTRATOR("Payroll Administrator"), PRE_SALES_CORRDINATOR("Pre-Sales Coordinator"),
	QA_ENGINEER("QA Engineer"), QA_LEAD("QA Lead"), SALES_REPRESENTATIVE("Sales Representative"),
	SOCIAL_MEDIA_Marketer("Social Media Marketer"), SOFTWARE_ARCHITECT("Software Architect"),
	SOFTWARE_ENGINEER("Software Engineer"), SUPPORT_SPECIALIST("Support Specialist"),
	VP_CLIENT_SERVICES("VP - Client Services"), VP_SALES_AND_MARKETING("VP - Sales & Marketing");

	private final String value;

	JobTitleOption(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String randomLocationOption() {
		JobTitleOption[] jobTitleOptions = values();
		return jobTitleOptions[RandomHelper.getRandomNumber(1, jobTitleOptions.length - 1)].getValue();
	}
}
