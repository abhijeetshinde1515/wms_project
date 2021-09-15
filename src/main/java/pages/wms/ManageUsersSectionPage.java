package pages.wms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageUsersSectionPage extends SubMenuSectionPage {

	public ManageUsersSectionPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	
	/************ locators ***************/
	
	@FindBy(xpath = "//button[normalize-space()='Create New User']")
	WebElement createNewUser_by;
	
	@FindBy(css = "input[placeholder='Search...']")
	WebElement searchBox_by;
	
	@FindBy(css = "span[aria-label='search']")
	WebElement searchIcon_by;
	
	@FindBy(css = "span.anticon-edit")
	WebElement editButton_by;
	
	@FindBy(css = "span.anticon-delete")
	WebElement deleteButton_by;
	
	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement deleteUserConfirmation_by;
	
	/************ actions ****************/
	
	public CreateNewUserPage clickCreateNewUser() {
		click(createNewUser_by);
		return new CreateNewUserPage(driver);
	}
	
	public CreateNewUserPage clickEditUser() {
		click(editButton_by);
		return new CreateNewUserPage(driver);
	}
	
	public ManageUsersSectionPage clickDeleteUser() {
		click(deleteButton_by);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteUserConfirmation_by));
		clickUsingJSExecutor(deleteUserConfirmation_by);
		return new ManageUsersSectionPage(driver);
	}
	
	public ManageUsersSectionPage clickSearchTextAs(String text) {
		logStep("Searching User using seach text as "+text);
		sendKeys(searchBox_by, text);
		clickUsingJSExecutor(searchIcon_by);
		hardWait(5000);
		return new ManageUsersSectionPage(driver);
	}
	
	/************ accessors **************/
	
	public boolean isUserDetailDisplayed(String detail) {
		List<WebElement> orders = driver.findElements(By.cssSelector("td.ant-table-cell"));
		for (WebElement listItem : orders)
			if (listItem.getText().contains(detail)) {
				break;
			}
		return true;
	}
	
	/************ validations ************/
}
