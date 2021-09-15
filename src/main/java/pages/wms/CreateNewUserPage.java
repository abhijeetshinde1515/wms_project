package pages.wms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.CommonPage;

public class CreateNewUserPage extends CommonPage {

	public CreateNewUserPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/
	
	@FindBy(id =  "first_name")
	WebElement firstName_by;
	
	@FindBy(id = "last_name")
	WebElement lastName_by;
	
	@FindBy()
	WebElement role_by;
	
	@FindBy()
	WebElement permissionTemplate_by;
	
	@FindBy(id = "email")
	WebElement email_by;
	
	@FindBy(id = "phone_number")
	WebElement phone_by;
	
	@FindBy(css = "div#shift input[value='0']")
	WebElement dayShift_by;
	
	@FindBy(css = "div#shift input[value='1']")
	WebElement nightShift_by;
	
	@FindBy(css = "div#status input[value='0']")
	WebElement activeStatus_by;
	
	@FindBy(css = "div#status input[value='1']")
	WebElement inactiveStatus_by;
	
	@FindBy(css = "div#status input[value='2']")
	WebElement suspendedStatus_by;
	
	@FindBy(id = "note")
	WebElement note_by;
	
	/************ actions ****************/
	
	public void setFirstName(String firstName) {
		sendKeys(firstName_by, firstName);
	}
	
	public void setLastName(String lastName) {
		sendKeys(lastName_by, lastName);
	}
	
	public void setEmail(String email) {
		sendKeys(email_by, email);
	}
	
	public void setPhone(String phone) {
		sendKeys(phone_by, phone);
	}

	public void setNote(String note) {
		sendKeys(note_by, note);
	}
	
	public void setDayShift() {
		clickUsingJSExecutor(dayShift_by);
	}
	
	public void setNightShift() {
		clickUsingJSExecutor(nightShift_by);
	}
	
	public void setActiveStatus() {
		clickUsingJSExecutor(activeStatus_by);
	}
	
	public void setInactiveStatus() {
		clickUsingJSExecutor(inactiveStatus_by);
	}
	
	public void setSuspendedStatus() {
		clickUsingJSExecutor(suspendedStatus_by);
	}
	
	public void setRole(String role) {
		click(findElement(By.id("role_id")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.ant-select-item-option-content")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("div.ant-select-item-option-content"));
		for (WebElement listItem : listItems) {
			if (listItem.getText().contains(role)) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
	}
	
	public void setPermissionTemplate(String template) {
		click(findElement(By.id("permission_template_id")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='"+template+"'] div[class='ant-select-item-option-content']")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("div[title='"+template+"'] div[class='ant-select-item-option-content']"));
		for (WebElement listItem : listItems) {
			if (listItem.getText().contains(template)) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
	}
	
	/************ accessors **************/
	
	/************ validations ************/
}
