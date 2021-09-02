package baseTestScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;
import pages.wms.HomePage;
import reports.ExtentTestManager;
import utils.AssertUtil;

public class TestNGBaseTest extends AssertUtil {

	protected RemoteWebDriver driver;
	
	public HomePage navigateToWMSWebsite(boolean keepBrowserOpen) throws Exception {
		
		logStep("Launch Browser Successfully...");
		
		if(keepBrowserOpen) {
			System.setProperty("webdriver.chrome.driver", TestData.driver_location);
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			logStep("Navigate to WMS Website Home Page");
			driver.get(TestData.WMS_url);
			assertEquals(driver.getTitle(), TestData.WMS_HomepageTitle, "WMS Website Opened Successfully...");
		}else {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu");
			options.addArguments("window-size=1280x768");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", TestData.driver_location);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			logStep("Navigate to WMS Website Home Page.");
			driver.get(TestData.WMS_url);
			assertEquals(driver.getTitle(), TestData.WMS_HomepageTitle, "WMS Website Opened Successfully...");
		}
		
		return new HomePage(driver);
	}
	
	public void closeBrowser() {
		logStep("Closing Browser Successfully...");
		driver.quit();
	}
	
	public static String logStep(String logs) {
		ExtentTestManager.getTest().log(Status.INFO, "STEP - "+logs);
		Reporter.log("STEP - "+logs, true);
		return null;
	}
	
	public static String logTestDocumentations(String testNumber, String coverage, String createDate) {
		ExtentTestManager.getTest().log(Status.INFO, "TestNumber - "+testNumber);
		Reporter.log("TestNumber - "+testNumber, true);
		
		ExtentTestManager.getTest().log(Status.INFO, "Coverage - "+coverage);
		Reporter.log("Coverage - "+coverage, true);
		
		ExtentTestManager.getTest().log(Status.INFO, "CreateDate - "+createDate);
		Reporter.log("CreateDate - "+createDate, true);
		return null;
	}
}