package pages.OrangeHRM;

import core.element.base.Element;
import dataType.OrangeHRM.LeftPanelMenuItem;

public class HomePage extends GeneralPage {

	protected final Element sidePanelMenuItem = new Element("//nav[@aria-label='Sidepanel']//span[text()='%s']");
	protected final Element topBarMenuButton = new Element("//nav[@aria-label='Topbar Menu']//a[text()='%s']");

	public void clickSidePanelMenuItem(LeftPanelMenuItem item) throws Exception {
		sidePanelMenuItem.generateDynamic(item.getName());
		sidePanelMenuItem.click();
	}

}
