package pages.wms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReportUtils;

public class Tier1DashBoardPage extends HomePage {

	public Tier1DashBoardPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	
	/************ locators ***************/
	
	@FindBy(css = "span.gx-avatar-name")
	WebElement welcomeMessage_by;
	
	@FindBy(css = "i.icon.icon-chevron-down")
	WebElement myProfile_by;
	
	/************ actions ****************/
	
	public UserProfilePage clickMyProfile() {
		Actions action = new Actions(driver);
		action.moveToElement(myProfile_by).perform();
		clickLink("My Profile");
		return new UserProfilePage(driver);
	}
	
	public HomePage clickLogOut() {
		logStep("Logging Out Successfully...");
		Actions action = new Actions(driver);
		action.moveToElement(myProfile_by).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul.gx-user-popover li")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("ul.gx-user-popover li"));
		for (WebElement listItem : listItems) {
			logStep((listItem.getText()));
			if(listItem.getText().contains("Logout")) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
		return new HomePage(driver);
	}
	
	public ManageUsersSectionPage clickOnMenuSection(String menu) {
		logStep("Open "+menu+" Section");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.ant-menu-title-content")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("span.ant-menu-title-content"));
		for (WebElement listItem : listItems) {
			if(listItem.getText().contains(menu)) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
		return new ManageUsersSectionPage(driver);
	}
	
	public SubMenuSectionPage clickOnSubMenuSection(String menu, String subMenu) {
		clickOnMenuSection(menu);
		
		logStep("Open "+subMenu+" Section");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.ant-menu-title-content a span")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("span.ant-menu-title-content a span"));
		for (WebElement listItem : listItems) {
			if(listItem.getText().contains(subMenu)) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
		return new SubMenuSectionPage(driver);
	}
	
	/************ accessors **************/
	
	public String getWelcomeMessage() {
		return welcomeMessage_by.getText();
	}
	
	/************ validations ************/
	
	public Tier1DashBoardPage validateAllHyperLinks() {
		List<WebElement> listItems = driver.findElements(By.tagName("a"));
		for (WebElement listItem : listItems) {
			ReportUtils.logVerify(listItem.getAttribute("title") + " - " + listItem.getAttribute("href"));
			String url = listItem.getAttribute("href");
			verifyLinks(url);
		}
		
		return new Tier1DashBoardPage(driver);
	}
}
