package pages.OrangeHRM;

public class ApplyLeavePage extends LeavePage{

	private static ApplyLeavePage instance;

	public static ApplyLeavePage newInstance() {
		if (ApplyLeavePage.instance == null)
			ApplyLeavePage.instance = new ApplyLeavePage();
		return ApplyLeavePage.instance;
	}
	
}
