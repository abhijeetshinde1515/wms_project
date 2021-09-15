package framework;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	@FindBy(css = "div.ant-card-head-title")
	WebElement pageTitle_by;
	
	@FindBy(css = "div[role='alert']")
	WebElement alertMessage_by;
	
	@FindBy(css = "button[type='submit']")
	WebElement submitButton_by;
	
	@FindBy(css = "button[type='button']")
	WebElement cancelButton_by;
	
	@FindBy(css = "div.ant-empty-description")
	WebElement noDataFound_by;
	
	@FindBy(css = "div.breadcrumb a span")
	WebElement breadCrumbNavigation_by;
	
	/************ actions ****************/
	
	public void navigateUsingBreadCrumb(String link) {
		List<WebElement> links = driver.findElements(By.cssSelector("div.breadcrumb a span"));
		for (WebElement listItem : links)
			if (listItem.getText().contains(link)) {
				click(listItem);
				break;
			}
	}
	
	/************ accessors **************/
	
	public Tier1DashBoardPage clickFreedomLogo() {
		logStep("STEP - Clicking FREEDOM LOGISTICS to Return On Dashboard Page...");
		findElement(By.cssSelector("a.gx-site-logo")).click();
		return new Tier1DashBoardPage(driver);
	}
	
	public void clickSubmit() {
		click(submitButton_by);
		hardWait(3000);
	}
	
	public void clickCancel() {
		click(cancelButton_by);
		hardWait(3000);
	}
	
	/************ validations ************/
	
	public boolean isSectionPageTitleDisplayed(String title) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pageTitle_by));
		return pageTitle_by.isDisplayed() && pageTitle_by.getText().contains(title);
	}
	
	public String getAlertMessage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertMessage_by));
		return alertMessage_by.getText();
	}
	
	public boolean getAlertMessages(String alerMessage) {
		List<WebElement> orders = driver.findElements(By.cssSelector("div[role='alert']"));
		for (WebElement listItem : orders)
			if (listItem.getText().contains(alerMessage)) {
				break;
			}
		return true;
	}
	
	public boolean isNoDataFoundMessageDisplayed() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(6000,50)");
		return noDataFound_by.isDisplayed() && noDataFound_by.getText().contains("No Data");
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
