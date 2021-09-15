package pages.wms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import framework.CommonPage;

public class CreateNewSitePage extends CommonPage {

	public CreateNewSitePage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/
	
	@FindBy(id =  "site_name")
	WebElement siteName_by;
	
	@FindBy(id = "site_code")
	WebElement siteCode_by;
	
	@FindBy(id = "line1")
	WebElement line1_by;
	
	@FindBy(id = "line2")
	WebElement line2_by;
	
	@FindBy(id = "email")
	WebElement email_by;
	
	@FindBy(id = "city_name")
	WebElement cityName_by;
	
	@FindBy(id = "state_name")
	WebElement state_by;
	
	@FindBy(id = "country_name")
	WebElement country_by;
	
	@FindBy(id = "postalcode")
	WebElement postalCode_by;
	
	@FindBy(id = "access_date")
	WebElement firstAccessDate_by;
	
	@FindBy(id = "estimated_date")
	WebElement expectedEndDate_by;
	
	@FindBy(id = "client_company_name")
	WebElement clientCompanyName_by;
	
	@FindBy(id = "onsite_name")
	WebElement clientOnsiteLeadName_by;
	
	@FindBy(id = "onsite_email")
	WebElement clientOnsiteLeadEmail_by;
	
	@FindBy(id = "onsite_number")
	WebElement clientOnsiteNumber_by;
	
	@FindBy(id = "client_name")
	WebElement clientProcurementName_by;
	
	@FindBy(id = "procurment_email")
	WebElement clientProcurementEmail_by;
	
	@FindBy(id = "procurment_phone")
	WebElement clientProcurementPhone_by;
	
	@FindBy(id = "add_name")
	WebElement clientAdditionalName_by;
	
	@FindBy(id = "add_email")
	WebElement clientAdditionalEmail_by;
	
	@FindBy(id = "add_number")
	WebElement clientAdditionalPhoneNumber_by;
	
	@FindBy(id = "role")
	WebElement clientAdditionalPhoneRole_by;
	
	/************ actions ****************/
	
	public void setSiteName(String siteName) {
		sendKeys(siteName_by, siteName);
	}
	
	public void setSiteCode(String siteCode) {
		sendKeys(siteCode_by, siteCode);
	}
	
	public void setAddressLine1(String line1) {
		sendKeys(line1_by, line1);
	}
	
	public void setAddressLine2(String line2) {
		sendKeys(line2_by, line2);
	}
	
	public void setCity(String city) {
		sendKeys(cityName_by, city);
	}
	
	public void setState(String state) {
		sendKeys(state_by, state);
	}
	
	public void setCountry(String country) {
		sendKeys(country_by, country);
	}
	
	public void setPostalCode(String postalCode) {
		sendKeys(postalCode_by, postalCode);
	}
	
	public void setClientCompananyName(String companyName) {
		sendKeys(clientCompanyName_by, companyName);
	}
	
	public void setClientOnSiteLeadName(String clientOnsiteLeadName) {
		sendKeys(clientOnsiteLeadName_by, clientOnsiteLeadName);
	}
	
	public void setClientOnSiteLeadEmail(String clientOnsiteLeadEmail) {
		sendKeys(clientOnsiteLeadEmail_by, clientOnsiteLeadEmail);
	}
	
	public void setClientOnSiteLeadPhone(String clientOnsiteLeadPhone) {
		sendKeys(clientOnsiteNumber_by, clientOnsiteLeadPhone);
	}
	
	public void setClientProcurementContactName(String contactName) {
		sendKeys(clientProcurementName_by, contactName);
	}
	
	public void setClientProcurementContactEmail(String contactEmail) {
		sendKeys(clientProcurementEmail_by, contactEmail);
	}
	
	public void setClientProcurementContactPhone(String contactPhone) {
		sendKeys(clientProcurementPhone_by, contactPhone);
	}
	
	public void setAdditionalContactName(String additionalContactName) {
		sendKeys(clientAdditionalName_by, additionalContactName);
	}
	
	public void setAdditionalContactEmail(String additionalContactEmail) {
		sendKeys(clientAdditionalEmail_by, additionalContactEmail);
	}
	
	public void setAdditionalContactPhone(String additionalContactPhone) {
		sendKeys(clientAdditionalPhoneNumber_by, additionalContactPhone);
	}
	
	public void setAdditionalContactRole(String additionalContactRole) {
		sendKeys(clientAdditionalPhoneRole_by, additionalContactRole);
	}
	
	public void setFirstAccessDate(String date) {
		sendKeys(firstAccessDate_by, date);
	}
	
	public void setExpectedEndDate(String date) {
		sendKeys(expectedEndDate_by, date);
	}

	/************ accessors **************/
	
	/************ validations ************/
}
