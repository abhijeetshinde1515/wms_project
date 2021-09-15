package tests.wms;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import pages.wms.CreateNewSitePage;
import pages.wms.HomePage;
import pages.wms.ManageSiteSectionPage;
import pages.wms.SubMenuSectionPage;
import pages.wms.Tier1DashBoardPage;
import utils.GeneratorUtils;

public class TestSiteSection extends TestNGBaseTest {

	@Test()
	public void testCreateAndDeleteSite() throws Exception {
		String testNumber = "TC_6";
		String coverage = "Verifies that Tier1 User can create and delete site from Site section.";
		String createDate = "07/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
		
		logStep("Go to Manage Users Section");
		SubMenuSectionPage subMenuSectionPage = tier1DashBoardPage.clickOnSubMenuSection("Site", "Set-up Site");
		ManageSiteSectionPage manageSiteSectionPage = subMenuSectionPage.getManageSiteSection();
		assertTrue(manageSiteSectionPage.isSectionPageTitleDisplayed("Site List"), "Site List Page is Displayed");
		
		logStep("Create new site"); 
		CreateNewSitePage createNewSitePage = manageSiteSectionPage.clickCreateNewSite();
		assertTrue(createNewSitePage.isSectionPageTitleDisplayed("CREATE NEW SITE"), "CREATE NEW SITE Page is Displayed");
		
		String alertMessage1 = "Please enter Site name!";
		String alertMessage2 = "Please enter Site code!";
		String alertMessage3 = "Please enter Street Line 1!";
		String alertMessage4 = "Please enter Street Line 2!";
		String alertMessage5 = "Please enter city name!";
		String alertMessage6 = "Please enter state name!";
		String alertMessage7 = "Please enter country!";
		String alertMessage8 = "Please enter postal code!";
		String alertMessage9 = "Please select the date!";
		String alertMessage10 = "Please select the date!";
		String alertMessage11 = "Please enter company name!";
		String alertMessage12 = "Please enter Name!";
		String alertMessage13 = "Please enter Email!";
		String alertMessage14 = "Please enter Phone number!";
		String alertMessage15 = "Please enter Client Name!";
		String alertMessage16 = "Please enter Client Email!";
		String alertMessage17 = "Please enter Client Phone Number!";
		String alertMessage18 = "Please enter Email!";
		String alertMessage19 = "Please enter Phone number!";
		
		List<String> messages = Arrays.asList(alertMessage1, alertMessage2, alertMessage3, alertMessage4, alertMessage5, alertMessage6, alertMessage7, alertMessage8, alertMessage9, alertMessage10,
				alertMessage11, alertMessage12, alertMessage13, alertMessage14, alertMessage15, alertMessage16, alertMessage17, alertMessage18, alertMessage19);
		
		logStep("Create Site with Blank Data");
		createNewSitePage.clickSubmit();
		
		for (String content : messages) {
			assertTrue(homePage.getAlertMessages(content), content+" - Message is Displayed");
		}
		
		createNewSitePage.clickCancel();
		assertTrue(manageSiteSectionPage.isSectionPageTitleDisplayed("Site List"), "Site List Page is Displayed");
	
		logStep("Create new site"); 
		createNewSitePage = manageSiteSectionPage.clickCreateNewSite();
		assertTrue(createNewSitePage.isSectionPageTitleDisplayed("CREATE NEW SITE"), "CREATE NEW SITE Page is Displayed");
		
		String siteName = GeneratorUtils.generateUniqueId("Automation_Site");
		String siteCode = GeneratorUtils.generateUniqueId("Automation_SiteCode");
		String line1 = "Line1 Address";
		String line2 = "Line2 Address";
		String city = "Pune City";
		String state = "Maharashtra State";
		String country = "INDIA";
		String postalCode = "412308";
		String clientCompanyName = GeneratorUtils.generateUniqueId("CompanyName");
		String onsiteLeadName = "OnsiteLead";
		String onsiteLeadEmail = GeneratorUtils.generateUniqueEmail();
		String onsiteLeadPhone = GeneratorUtils.generateUniqueMobileNumber();
		String clientProcurementName = "ClientProcurementName";
		String clientProcurementEmail = GeneratorUtils.generateUniqueEmail();
		String clientProcurementPhone = GeneratorUtils.generateUniqueMobileNumber();
		String additionalContactName = "AdditionalContactName";
		String additionalContactEmail = GeneratorUtils.generateUniqueEmail();
		String additionalContactPhone = GeneratorUtils.generateUniqueMobileNumber();
		String today = GeneratorUtils.generateTodaysDate();
		
		logStep("Fill new site data");
		createNewSitePage.setSiteName(siteName);
		createNewSitePage.setSiteCode(siteCode);
		createNewSitePage.setAddressLine1(line1);
		createNewSitePage.setAddressLine2(line2);
		createNewSitePage.setCity(city);
		createNewSitePage.setState(state);
		createNewSitePage.setCountry(country);
		createNewSitePage.setPostalCode(postalCode);
		createNewSitePage.setFirstAccessDate(today);
		createNewSitePage.setExpectedEndDate(today);
		createNewSitePage.setClientCompananyName(clientCompanyName);
		createNewSitePage.setClientOnSiteLeadName(onsiteLeadName);
		createNewSitePage.setClientOnSiteLeadEmail(onsiteLeadEmail);
		createNewSitePage.setClientOnSiteLeadPhone(onsiteLeadPhone);
		createNewSitePage.setClientProcurementContactName(clientProcurementName);
		createNewSitePage.setClientProcurementContactEmail(clientProcurementEmail);
		createNewSitePage.setClientProcurementContactPhone(clientProcurementPhone);
		createNewSitePage.setAdditionalContactName(additionalContactName);
		createNewSitePage.setAdditionalContactEmail(additionalContactEmail);
		createNewSitePage.setAdditionalContactPhone(additionalContactPhone);
		createNewSitePage.setAdditionalContactRole("Admin Role");
		createNewSitePage.clickSubmit();

		logStep("Validate newly created Site Details");
		manageSiteSectionPage = new ManageSiteSectionPage(driver);
		assertTrue(manageSiteSectionPage.isSectionPageTitleDisplayed("Site List"), "Site List Page is Displayed");
	
		manageSiteSectionPage.clickSearchTextAs(siteName);
		assertTrue(manageSiteSectionPage.isDetailDisplayed(siteName), siteName+" is Displayed Successfully");
		assertTrue(manageSiteSectionPage.isDetailDisplayed(clientCompanyName), clientCompanyName+" is Displayed Successfully");
		assertTrue(manageSiteSectionPage.isDetailDisplayed(clientCompanyName), clientCompanyName+" is Displayed Successfully");
		
		logStep("Delete newly created site");
		manageSiteSectionPage.clickDeleteSite();
		manageSiteSectionPage.refreshPage();
		manageSiteSectionPage.clickSearchTextAs(siteName);
		assertTrue(manageSiteSectionPage.isNoDataFoundMessageDisplayed(), "No Data Found Message is Displayed");
		
		logStep("Validate deleted site in Deleted Site List Page");
		manageSiteSectionPage = manageSiteSectionPage.clickDeletedSitesButton();
		assertTrue(manageSiteSectionPage.isSectionPageTitleDisplayed("Deleted Site List"), "Deleted Site List Page is Displayed");
		
		manageSiteSectionPage.clickSearchTextAs(siteName);
		assertTrue(manageSiteSectionPage.isDetailDisplayed(siteName), siteName+" is Displayed Successfully");
		assertTrue(manageSiteSectionPage.isDetailDisplayed(clientCompanyName), clientCompanyName+" is Displayed Successfully");
		assertTrue(manageSiteSectionPage.isDetailDisplayed(clientCompanyName), clientCompanyName+" is Displayed Successfully");
		
		logStep("Navigate back using breadcrumb link as Site List");
		manageSiteSectionPage.navigateUsingBreadCrumb("Site List");
		assertTrue(manageSiteSectionPage.isSectionPageTitleDisplayed("Site List"), "Site List Page is Displayed");
		closeBrowser();
	}
}