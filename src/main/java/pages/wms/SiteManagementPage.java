package pages.wms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import framework.CommonPage;

public class SiteManagementPage extends CommonPage {

	public SiteManagementPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/
	
	@FindBy(xpath = "//p[normalize-space()='Total Members']")
	WebElement totalMembers_by;
	
	/************ actions ****************/
	
	public ManageUsersSectionPage clickTotalMembers() {
		clickUsingJSExecutor(totalMembers_by);
		waituntilPageLoads();
		return new ManageUsersSectionPage(driver);
	}
	
	/************ accessors **************/
	
	/************ validations ************/
}
