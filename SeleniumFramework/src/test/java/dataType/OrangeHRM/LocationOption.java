package dataType.OrangeHRM;

import core.helper.RandomHelper;

public enum LocationOption {

	DEFAULT("-- Select --"), ABCD("Abcd"), CANADIAN_REGIONAL_HQ("Canadian Regional HQ"), HQ_CA_USA("HQ - CA, USA"),
	NEW_YORK_SALES_OFFICE("New York Sales Office"), TEXAS_R_AND_D("Texas R&D");

	private final String value;

	LocationOption(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String randomLocationOption() {
		LocationOption[] locations = values();
		return locations[RandomHelper.getRandomNumber(1, locations.length)].getValue();
	}

}
