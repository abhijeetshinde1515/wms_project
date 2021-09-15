package pages.wms;

import org.openqa.selenium.remote.RemoteWebDriver;
import framework.CommonPage;

public class SubMenuSectionPage extends CommonPage {

	public SubMenuSectionPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
		hardWait(3000);
	}
	
	/************ locators ***************/
	
	/************ actions ****************/
	
	/************ accessors **************/
	
	public ManageUsersSectionPage getManageUsersSection() {
		return new ManageUsersSectionPage(driver);
	}
	
	public ManageSiteSectionPage getManageSiteSection() {
		return new ManageSiteSectionPage(driver);
	}
	
	public SiteSelectionPage getSiteSection() {
		return new SiteSelectionPage(driver);
	}
	
	public SiteManagementPage getSiteManagementSection() {
		return new SiteManagementPage(driver);
	}
	
	/************ validations ************/
}
