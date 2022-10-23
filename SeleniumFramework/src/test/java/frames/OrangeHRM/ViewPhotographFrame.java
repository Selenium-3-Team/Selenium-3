package frames.OrangeHRM;

import core.element.wrapper.Button;

public class ViewPhotographFrame extends GeneralFrame{

	private final Button btnPlus = new Button("//button[contains(@class,'employee-image-action')]");
	
	public void clickAddPicture() {
		btnPlus.click();
	}
	
}
