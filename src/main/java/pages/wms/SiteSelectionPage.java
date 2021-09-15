package pages.wms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.CommonPage;

public class SiteSelectionPage extends CommonPage {

	public SiteSelectionPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/
	
	@FindBy(xpath = "//button[normalize-space()='Go']")
	WebElement goButton_by;
	
	@FindBy(id = "select_site")
	WebElement selectSite_by;
	
	/************ actions ****************/
	public void clickGoButton() {
		clickUsingJSExecutor(goButton_by);
	}
	
	public void selectSite(String site) {
		clickUsingJSExecutor(selectSite_by);
		
		logStep("Select "+site+" Section");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.ant-select-item-option-content")));
		List<WebElement> listItems = driver.findElements(By.cssSelector("div.ant-select-item-option-content"));
		for (WebElement listItem : listItems) {
			if(listItem.getText().contains(site)) {
				clickUsingJSExecutor(listItem);
				break;
			}
		}
	}
	
	public void selectFirstSite() {
		logStep("Select First Site in List");
		clickUsingJSExecutor(selectSite_by);
		selectSite_by.sendKeys(Keys.ARROW_DOWN);
		selectSite_by.sendKeys(Keys.ENTER);
		waituntilPageLoads();
		clickGoButton();
	}
	/************ accessors **************/
	
	/************ validations ************/
}
