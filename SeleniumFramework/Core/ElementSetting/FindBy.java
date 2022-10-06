package ElementSetting;

public enum FindBy {

	/**
	 * Declare constants for locator type
	 */
	CSS_SELECTOR("cssSelector"), ID("id"), CLASS_NAME("class"), LINK_TEXT("linkText"), NAME("name"),
	PARTIAL_LINK_TEXT("partialLinkText"), TAG_NAME("tagName"), XPATH("xpath");

	/**
	 * Type of locator
	 */
	private String by;

	/**
	 * Initializes find type of locator with given string of by 
	 * @param by - Find type of locator
	 */
	FindBy(String by) {
		this.by = by;
	}

	/**
	 * Get value find type
	 * @return type of locator
	 */
	public String getValue() {
		return by;
	}

	/**
	 * Get find type of locator
	 * @param by - Find type of locator
	 * @return this method will run a loop that check if the parameter by is equal one of those constanted types
	 * it will return find by that type otherwise it will default return find by xpath
	 */
	public static FindBy getByLocator(String by) {
		for (FindBy item : FindBy.values()) {
			if (item.getValue().equalsIgnoreCase(by))
				return item;
		}
		return FindBy.XPATH;
	}

}
