package framework;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;
import reports.ExtentTestManager;

public class BaseFragment {

	protected final RemoteWebDriver driver;
	
	public BaseFragment(RemoteWebDriver webDriver) {
		driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	public <T extends BaseFragment> T getInstance(Class<T> clazz) {
		T instance = null;
		try {
			instance = clazz.getConstructor(RemoteWebDriver.class).newInstance(driver);
		} catch (NoSuchMethodException e) {
			Reporter.log(String.format("Class %s did not have a getContructor() method defined: %s, Stack Trace: %s",
					clazz.getSimpleName(), e.getMessage(), Arrays.toString(e.getStackTrace())));
		} catch (InstantiationException e) {
			Reporter.log(String.format("Class %s failed to instantiate: %s, Stack Trace: %s", clazz.getSimpleName(),
					e.getMessage(), Arrays.toString(e.getStackTrace())));
		} catch (IllegalAccessException e) {
			Reporter.log(String.format("Class %s did not have access to constructor: %s, Stack Trace: %s",
					clazz.getSimpleName(), e.getMessage(), Arrays.toString(e.getStackTrace())));
		} catch (InvocationTargetException e) {
			Reporter.log(String.format("Class %s invalid invocation target: %s, Stack Trace: %s", clazz.getSimpleName(),
					e.getMessage(), Arrays.toString(e.getStackTrace())));
		}
		return instance;
	}

	public void click(WebElement webElement) {
		logStep("Click - "+webElement.getText());
		webElement.click();
	}
	
	public void clickUsingJSExecutor(WebElement webElement) {
		logStep("Click - "+webElement.getText());
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", webElement);
	}
	
	public void sendKeys(WebElement webElement, String text) {
		logStep("Set - "+ text);
		webElement.sendKeys(text);
	}
	
	public void clickWhenClickable(WebElement element) {
		logStep("Click - "+element.getText());
		Actions actions = new Actions(driver);
		actions.moveToElement(new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(element))).click().build().perform();
	}
	
	public void clickLink(String linkText) {
		logStep("Click Link As - "+linkText);
		findElement(By.linkText(linkText)).click();
	}
	
	public void clickPartialLink(String partialLinkText) {
		logStep("Click Partial Link As - "+partialLinkText);
		findElement(By.partialLinkText(partialLinkText)).click();
	}

	protected WebElement findElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			logStep("Unable to find Element '{}'");
			throw e;
		}
	}

	public void waituntilPageLoads() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void waitExplicitlyFor(String element) {
		new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void hardWait(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String generateUniqueId(String prefix) {
		return prefix + "_" + generateUniqueId();
	}

	public static String generateUniqueId() {
		SimpleDateFormat format = new SimpleDateFormat("MMddkkmmssSSS");
		return format.format(new Date());
	}
	
	public static String logStep(String logs) {
		ExtentTestManager.getTest().log(Status.INFO, "STEP - "+logs);
		Reporter.log("STEP - "+logs, true);
		return null;
	}
}