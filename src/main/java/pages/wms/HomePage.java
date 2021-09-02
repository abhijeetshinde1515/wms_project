package pages.wms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.CommonPage;

public class HomePage extends CommonPage {

	public HomePage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	
	/************ locators ***************/
	
	@FindBy(css = ".ant-typography")
	WebElement pageTitle_by;
	
	@FindBy(id = "basic_email")
	WebElement email_by;
	
	@FindBy(id = "basic_password")
	WebElement password_by;
	
	@FindBy(css = "button[type='submit']")
	WebElement submitButton_by;
	
	@FindBy(css = "div[role='alert']")
	WebElement alertMessage_by;
	
	@FindBy(css = ".error")
	WebElement errorMessage_by;
	
	/************ actions ****************/
	
	public void setEmail(String email) {
		email_by.clear();
		sendKeys(email_by, email);
	}
	
	public void setPassword(String password) {
		password_by.clear();
		sendKeys(password_by, password);
	}
	
	public Tier1DashBoardPage clickSignIn() {
		click(submitButton_by);
		hardWait(3000);
		return new Tier1DashBoardPage(driver);
	}
	
	/************ accessors **************/
	
	public String getAlertMessage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertMessage_by));
		return alertMessage_by.getText();
	}

	public String getErrorMessage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errorMessage_by));
		return errorMessage_by.getText();
	}
	
	/************ validations ************/
	
	public boolean isPageTitleDisplayed(String title) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pageTitle_by));
		return pageTitle_by.isDisplayed() && pageTitle_by.getText().contains(title);
	}
}
