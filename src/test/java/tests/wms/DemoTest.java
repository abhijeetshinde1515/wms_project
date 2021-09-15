package tests.wms;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import pages.wms.HomePage;
import pages.wms.Tier1DashBoardPage;
import pages.wms.UserProfilePage;

public class DemoTest extends TestNGBaseTest {

/*	@Test()
	public void testCreateEditAndDeleteUserFromManageUserSection() throws Exception {
		String testNumber = "TC_7";
		String coverage = "Verifies that Tier 1 user can create, edit and delete user from Manage Users Section.";
		String createDate = "03/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(true);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
		
		logStep("Go to Site Management");
		SubMenuSectionPage subMenuSectionPage = tier1DashBoardPage.clickOnMenuSection("Go to Site Management");
		SiteSelectionPage siteSelectionPage = subMenuSectionPage.getSiteSection();
		assertTrue(subMenuSectionPage.isSectionPageTitleDisplayed("Site Selection"), "Site Selection Page is Displayed");
		
	}
*/	
//	
//	@Test()
//	public void testTier1UserProfileSection() throws Exception {
//		String testNumber = "TC_4";
//		String coverage = "Verifies that Tier 1 user can see user profile from My profile section of Tier 1 Dashboard.";
//		String createDate = "02/09/2021";
//		logTestDocumentations(testNumber, coverage, createDate);
//		
//		HomePage homePage = navigateToWMSWebsite(true);
//		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
//		
//		logStep("Enter Valid Username, Password and Sign In");
//		homePage.setEmail(TestData.email);
//		homePage.setPassword(TestData.password);
//		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
//		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
//	
//		logStep("Navigate to My Profile Section");
//		UserProfilePage userProfilePage = tier1DashBoardPage.clickMyProfile();
//		userProfilePage.validateAllHyperLinks();
//		
//		assertTrue(userProfilePage.getName(TestData.user_fullname), "Name is Correct");
//		assertTrue(userProfilePage.getDropDownValue(TestData.userRole), "Role is Correct");
//		assertTrue(userProfilePage.getDropDownValue(TestData.userTemplate), "User Template is Correct");
//		assertTrue(userProfilePage.getEmail(TestData.email), "User Email is Correct");
//		assertTrue(userProfilePage.getPhoneNumber(TestData.userPhone), "User Phone is Correct");
//		
//		tier1DashBoardPage = userProfilePage.clickFreedomLogo();
//		tier1DashBoardPage.clickLogOut();
//		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
//		closeBrowser();
//	}
}