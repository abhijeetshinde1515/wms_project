package tests.wms;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import pages.wms.CreateNewUserPage;
import pages.wms.HomePage;
import pages.wms.ManageUsersSectionPage;
import pages.wms.SiteManagementPage;
import pages.wms.SiteSelectionPage;
import pages.wms.SubMenuSectionPage;
import pages.wms.Tier1DashBoardPage;
import utils.GeneratorUtils;

public class TestSiteManagementSection extends TestNGBaseTest {

	@Test()
	public void testCreateEditAndDeleteUserFromSiteManagementSection() throws Exception {
		String testNumber = "TC_8";
		String coverage = "Verifies that Tier 1 user can create, edit and delete user from Manage Users Section.";
		String createDate = "08/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
		
		logStep("Go to Site Management");
		tier1DashBoardPage.clickLink("Go to Site Management");
		
		SubMenuSectionPage subMenuSectionPage = new SubMenuSectionPage(driver);
		SiteSelectionPage siteSelectionPage = subMenuSectionPage.getSiteSection();
		siteSelectionPage.selectFirstSite();
		
		tier1DashBoardPage.clickLink("Site Management");
		SiteManagementPage siteManagementPage = new SiteManagementPage(driver);
		ManageUsersSectionPage manageUsersSectionPage = new ManageUsersSectionPage(driver);
//		ManageUsersSectionPage manageUsersSectionPage = siteManagementPage.clickTotalMembers();
		assertTrue(manageUsersSectionPage.isSectionPageTitleDisplayed("Users List"), "Users List Page is Displayed");
		
		logStep("Create new user"); 
		CreateNewUserPage createNewUserPage = manageUsersSectionPage.clickCreateNewUser();
		assertTrue(createNewUserPage.isSectionPageTitleDisplayed("CREATE NEW USER"), "CREATE NEW USER Page is Displayed");
		
		String alertMessage1 = "Please enter your First name!";
		String alertMessage2 = "Please input your Last name!";
		String alertMessage3 = "Please select the Role!";
		String alertMessage4 = "Please select the Template!";
		String alertMessage5 = "The input is not valid E-mail!";
		String alertMessage6 = "Please enter your Phone Number!";
		String alertMessage7 = "Please enter the shift!";
		
		logStep("Create User with Blank Data");
		createNewUserPage.clickSubmit();
		assertTrue(homePage.getAlertMessages(alertMessage1), alertMessage1+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage2), alertMessage2+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage3), alertMessage3+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage4), alertMessage4+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage5), alertMessage5+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage6), alertMessage6+" - Message is Displayed");
		assertTrue(homePage.getAlertMessages(alertMessage7), alertMessage7+" - Message is Displayed");
		
		createNewUserPage.clickCancel();
		assertTrue(manageUsersSectionPage.isSectionPageTitleDisplayed("Users List"), "Users List Page is Displayed");
		
		logStep("Create new user"); 
		createNewUserPage = manageUsersSectionPage.clickCreateNewUser();
		assertTrue(createNewUserPage.isSectionPageTitleDisplayed("CREATE NEW USER"), "CREATE NEW USER Page is Displayed");
		
		String email = GeneratorUtils.generateUniqueEmail();
		String firstName = "Test_Automation_FirstName";
		String lastName = "Test_Automation_LastName";
		String mobileNumber = GeneratorUtils.generateUniqueMobileNumber();
		
		logStep("Fill new user create data");
		createNewUserPage.setFirstName(firstName);
		createNewUserPage.setLastName(lastName);
		createNewUserPage.setRole("Tier1");
		createNewUserPage.setEmail(email);
		createNewUserPage.setPhone(mobileNumber);
		createNewUserPage.setDayShift();
		createNewUserPage.setPermissionTemplate("Template 1");
		createNewUserPage.clickSubmit();
		
		logStep("Validate newly created User Details");
		manageUsersSectionPage = new ManageUsersSectionPage(driver);
		assertTrue(manageUsersSectionPage.isSectionPageTitleDisplayed("Users List"), "Users List Page is Displayed");
		
		manageUsersSectionPage.clickSearchTextAs(email);	
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed(firstName), firstName+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed(lastName), lastName+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed("Tier1"), "Tier1 Role is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed(email), email+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed(mobileNumber), mobileNumber+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed("Day"), "Day Shift"+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed("Template1"), "Template1"+" is Displayed Successfully");
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed("Active"), "Active Status"+" is Displayed Successfully");
		
		logStep("Edit User Details");
		createNewUserPage = manageUsersSectionPage.clickEditUser();
		assertTrue(createNewUserPage.isSectionPageTitleDisplayed("EDIT PROFILE"), "EDIT PROFILE Page is Displayed");
		
		String alertMessage8 = "Please enter note!";
		
		logStep("Change User Status as Inactive");
		createNewUserPage.setInactiveStatus();
		createNewUserPage.clickSubmit();
		assertTrue(homePage.getAlertMessages(alertMessage8), alertMessage8+" - Message is Displayed");
		
		logStep("Change User Status as Suspended");
		createNewUserPage.setSuspendedStatus();
		createNewUserPage.clickSubmit();
		assertTrue(homePage.getAlertMessages(alertMessage8), alertMessage8+" - Message is Displayed");
		
		logStep("Enter Note");
		createNewUserPage.setNote("Setting Note before Inactivating account");
		createNewUserPage.setInactiveStatus();
		createNewUserPage.clickSubmit();
		
		logStep("Validate newly edited User Details");
		manageUsersSectionPage = new ManageUsersSectionPage(driver);
		assertTrue(manageUsersSectionPage.isSectionPageTitleDisplayed("Users List"), "Users List Page is Displayed");
		
		manageUsersSectionPage.clickSearchTextAs(email);
		assertTrue(manageUsersSectionPage.isUserDetailDisplayed("Inactive"), "Inactive Status"+" is Displayed Successfully");
		
		logStep("Delete newly created user");
		manageUsersSectionPage.clickDeleteUser();
		
		manageUsersSectionPage.refreshPage();
		manageUsersSectionPage.clickSearchTextAs(email);
		assertTrue(manageUsersSectionPage.isNoDataFoundMessageDisplayed(), "No Data Found Message is Displayed");
		closeBrowser();
	}
}
