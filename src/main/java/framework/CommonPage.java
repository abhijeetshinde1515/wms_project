package framework;

import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;
import pages.wms.Tier1DashBoardPage;
import reports.ExtentTestManager;
import utils.ReportUtils;

public class CommonPage extends BaseFragment {

	public CommonPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	/************ locators ***************/
	
	@FindBy(css = ".page-title")
	WebElement pageTitle_by;
	
	@FindBy(css = "div.message.success.success.message")
	WebElement success_message_by;

	@FindBy(css = "div.message.error.error.message")
	WebElement error_message_by;
	
	@FindBy(css = "div.message.notice.notice.message")
	WebElement notice_message_by;
	
	@FindBy(id = "maincontent")
	WebElement content_by;
	
	/************ actions ****************/
	/************ accessors **************/
	
	public boolean getPageContent(String content) {
		return content_by.getText().contains(content);
	}
	
	public Tier1DashBoardPage clickFreedomLogo() {
		logStep("STEP - Clicking FREEDOM LOGISTICS to Return On Dashboard Page...");
		findElement(By.cssSelector("a.gx-site-logo")).click();
		return new Tier1DashBoardPage(driver);
	}
	
	/************ validations ************/
	
	public boolean isPageTitleDisplayed(String title) {
		return pageTitle_by.isDisplayed() && pageTitle_by.getText().contains(title);
	}
	
	
	public boolean isSuccessMessageDisplayed(String message) {
		return success_message_by.isDisplayed() && success_message_by.getText().contains(message);
	}

	public boolean isErrorMessageDisplayed(String message) {
		return error_message_by.isDisplayed() && error_message_by.getText().contains(message);
	}
	
	public boolean isNoticeMessageDisplayed(String message) {
		return notice_message_by.isDisplayed() && notice_message_by.getText().contains(message);
	}
	
	/************* helpers ******************/

	public static void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() >= 400) {
				ExtentTestManager.getTest().log(Status.FAIL, linkUrl + " - " + httpURLConnect.getResponseMessage() + " IS A BROKEN LINK...");
				Reporter.log(linkUrl + " - " + httpURLConnect.getResponseMessage() + " IS A BROKEN LINK...");
			}

			// Fetching and Printing the response code obtained
			else {
				ReportUtils.logVerify(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
		}
	}
}
