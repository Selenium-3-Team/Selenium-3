package ElementSetting;

public enum FindBy {

	CSS_SELECTOR("cssSelector"), ID("id"), CLASS_NAME("class"), LINK_TEXT("linkText"), NAME("name"),
	PARTIAL_LINK_TEXT("partialLinkText"), TAG_NAME("tagName"), XPATH("xpath");

	private String by;

	FindBy(String by) {
		this.by = by;
	}

	public String getValue() {
		return by;
	}

	public static FindBy getByLocator(String by) {
		for (FindBy item : FindBy.values()) {
			if (item.getValue().equalsIgnoreCase(by))
				return item;
		}
		return FindBy.XPATH;
	}

}
