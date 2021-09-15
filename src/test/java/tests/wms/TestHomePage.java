package tests.wms;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import pages.wms.HomePage;
import pages.wms.Tier1DashBoardPage;
import pages.wms.UserProfilePage;

public class TestHomePage extends TestNGBaseTest {

	@Test()
	public void testCustomerLogIn() throws Exception {
		String testNumber = "TC_1";
		String coverage = "Verifies that a authorised client User can log in and see profile.";
		String createDate = "01/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		String alertMessage1 = "The input is not valid E-mail!";
		String alertMessage2 = "Please input your Password!";
		
		String errorMessage1 = "Your email is invalid";
		String errorMessage2 = "Your email address or password is invalid";
		
		logStep("Enter blank Username, Set Password and Sign In");
		homePage.setEmail("");
		homePage.setPassword("");
		homePage.clickSignIn();
		assertTrue(homePage.getAlertMessage().contains(alertMessage1), alertMessage1+" - Message is Displayed");
		
		homePage.refreshPage();
		
		logStep("Enter Valid Username, blank Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword("");
		homePage.clickSignIn();
		assertTrue(homePage.getAlertMessage().contains(alertMessage2), alertMessage2+" - Message is Displayed");
		
		homePage.refreshPage();
		
		logStep("Enter Invalid Username, Password and Sign In");
		homePage.setEmail("abc@test.com");
		homePage.setPassword(TestData.password);
		homePage.clickSignIn();
		assertTrue(homePage.getErrorMessage().contains(errorMessage1), errorMessage1+" - Message is Displayed");

		homePage.refreshPage();
		
		logStep("Enter Valid Username, Invalid Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword("TestPassword");
		homePage.clickSignIn();
		assertTrue(homePage.getErrorMessage().contains(errorMessage2), errorMessage2+" - Message is Displayed");
		
		homePage.refreshPage();
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
		closeBrowser();
	}
	
	@Test()
	public void testForgotPassword() throws Exception {
		String testNumber = "TC_2";
		String coverage = "Verifies that only authenticated user will be able to reset his password using valid email."; 
		String createDate = "01/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);

		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		String alertMessage1 = "Please input your E-mail!";
		String alertMessage2 = "The input is not valid E-mail!";
		
		logStep("Navigate to Forgot Password");
		homePage.clickLink("Forgot Password?");
		assertTrue(homePage.isPageTitleDisplayed("Forgotten Password"), "Forgotten Password is Displayed");
		
		logStep("Enter blank Username and Submit");
		homePage.setEmail("");
		homePage.clickSignIn();
		assertTrue(homePage.getAlertMessage().contains(alertMessage1), alertMessage1+" - Message is Displayed");
		
		logStep("Enter Invalid Username and Submit");
		homePage.setEmail("abc");
		assertTrue(homePage.getAlertMessage().contains(alertMessage2), alertMessage2+" - Message is Displayed");
		
		logStep("Navigate Back to Log In");
		homePage.clickLink("Back to login");
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		closeBrowser();
	}
	
	@Test
	public void testVerifyTier1DashboardLinks() throws Exception {
		String testNumber = "TC_3";
		String coverage = "Verifies that Tier 1 Dashboard hyperlinks are accessible.";
		String createDate = "01/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
		
		tier1DashBoardPage.validateAllHyperLinks();
		closeBrowser();
	}
	
	@Test()
	public void testTier1UserProfileSection() throws Exception {
		String testNumber = "TC_4";
		String coverage = "Verifies that Tier 1 user can see user profile from My profile section of Tier 1 Dashboard.";
		String createDate = "02/09/2021";
		logTestDocumentations(testNumber, coverage, createDate);
		
		HomePage homePage = navigateToWMSWebsite(false);
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		
		logStep("Enter Valid Username, Password and Sign In");
		homePage.setEmail(TestData.email);
		homePage.setPassword(TestData.password);
		Tier1DashBoardPage tier1DashBoardPage = homePage.clickSignIn();
		assertTrue(tier1DashBoardPage.getWelcomeMessage().contains("Welcome Automation User!"), "Welcome Automation User! - Welcome Message is Displayed");
	
		logStep("Navigate to My Profile Section");
		UserProfilePage userProfilePage = tier1DashBoardPage.clickMyProfile();
		userProfilePage.validateAllHyperLinks();
		
		assertTrue(userProfilePage.getName(TestData.user_fullname), "Name is Correct");
		assertTrue(userProfilePage.getDropDownValue(TestData.userRole), "Role is Correct");
		assertTrue(userProfilePage.getDropDownValue("template 1"), "User Template is Correct");
		assertTrue(userProfilePage.getEmail(TestData.email), "User Email is Correct");
		assertTrue(userProfilePage.getPhoneNumber(TestData.userPhone), "User Phone is Correct");
		
		tier1DashBoardPage = userProfilePage.clickFreedomLogo();
		tier1DashBoardPage.clickLogOut();
		assertTrue(homePage.isPageTitleDisplayed("Login"), "Login Page is Displayed");
		closeBrowser();
	}

}
