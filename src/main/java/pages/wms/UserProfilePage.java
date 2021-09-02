package pages.wms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import framework.CommonPage;
import utils.ReportUtils;

public class UserProfilePage extends CommonPage {

	public UserProfilePage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	
	/************ locators ***************/
	
	@FindBy(css = "input#name")
	WebElement name_by;
	
	@FindBy(css = "input#email")
	WebElement email_by;
	
	@FindBy(css = "input#phone_number")
	WebElement phoneNumber_by;
	
	/************ actions ****************/
	
	/************ accessors **************/
	
	public boolean getName(String name) {
		return name_by.getAttribute("value").contains(name);
	}
	
	public boolean getEmail(String email) {
		return email_by.getAttribute("value").contains(email);
	}
	
	public boolean getPhoneNumber(String phoneNumber) {
		return phoneNumber_by.getAttribute("value").contains(phoneNumber);
	}
	
	public boolean getDropDownValue(String name) {
		List<WebElement> listItems = driver.findElements(By.cssSelector("span.ant-select-selection-item"));
		for (WebElement listItem : listItems) {
			if(listItem.getText().contains(name)) {
				return true;
			}
		}
		return false;
	}

	/************ validations ************/
	
	public UserProfilePage validateAllHyperLinks() {
		List<WebElement> listItems = driver.findElements(By.cssSelector("div.ant-col  a"));
		for (WebElement listItem : listItems) {
			ReportUtils.logVerify(listItem.getAttribute("title") + " - " + listItem.getAttribute("href"));
			String url = listItem.getAttribute("href");
			verifyLinks(url);
		}
		
		return new UserProfilePage(driver);
	}
}
