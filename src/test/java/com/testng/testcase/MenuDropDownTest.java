package com.testng.testcase;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testng.base.BaseTest;
import com.testng.pages.HomePage;
import com.testng.pages.LoginPage;
import com.testng.pages.ProfilePage;
import com.testng.pages.SettingsPage;
import com.testng.utilities.ScreenshotUtilities;

public class MenuDropDownTest extends BaseTest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ProfilePage profilePage;
	SettingsPage settingsPage;
	ScreenshotUtilities screenshot;
	
	@BeforeMethod
	public void beforeMethod()
	{
		driver = getDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		profilePage = new ProfilePage(driver);
		settingsPage = new SettingsPage(driver);
		
		screenshot = new ScreenshotUtilities();
	}
	
//	TC05
	@Test
	public void TC05_selectUserMenu() {
		loginPage.enterIntoUsername("muawiz@developer.com");
		loginPage.enterIntoPassword("Farooqi$123");
		loginPage.clickLoginButton();
		
		homePage.clickUserMenuDropdown();
		
		List<String> expectedList = List.of("My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience", "Logout");
		List<String> actualList = homePage.getUserMenuItemsList();
		
		Assert.assertEquals(actualList, expectedList, "User menu dropdown is not visible!");
	}
	
//	TC06
	@Test
	public void TC06_editMyProfile() {
		loginPage.enterIntoUsername("muawiz@developer.com");
        loginPage.enterIntoPassword("Farooqi$123");
        loginPage.clickLoginButton();

        homePage.clickUserMenuDropdown();
        homePage.clickMyProfileLink();

        profilePage.clickEditContactInfoButton();
        profilePage.clickAboutTab(driver);
        profilePage.restoreLastName("xyz", driver); // for debugging only
//        profilePage.enterEditLastname("xyz", driver);
        profilePage.clickSaveAllProfileChanges(driver);

        Assert.assertTrue(profilePage.getProfileName().contains("xyz"), "Last name was not updated successfully!");
        
        profilePage.clickPostLink();
        profilePage.enterPostText("Hello from Selenium!", driver);
        profilePage.clickShareButton();
        profilePage.waitForPost();

        Assert.assertTrue(profilePage.isPostPresent("Hello from Selenium!"), "Post was not shared successfully!");

        profilePage.clickFileLink(driver);
        profilePage.clickUploadLocalFileButton();
        profilePage.uploadSelectedFile("C:\\UTA\\Docs\\Resume_8_7_.pdf");
        profilePage.clickShareButton();
        profilePage.waitForFileOverlay(driver);
        profilePage.waitForPost();

        Assert.assertTrue(profilePage.isFilePresent("Resume_8_7_"), "File was not uploaded successfully!");

        profilePage.hoverOverProfilePic();
        profilePage.clickAddPhotoLink();
        profilePage.uploadNewProfilePic("C:\\Users\\muawi\\OneDrive\\Pictures\\portrait\\SEIR (square).JPG", driver);
        profilePage.clickUploadButton(driver);
        profilePage.cropProfilePicture();
        profilePage.clickSaveProfilePicButton(driver);

        Assert.assertTrue(profilePage.isProfilePicUpdated(), "Profile picture was not updated successfully!");
    
	}
	
//	TC07
	@Test
	public void TC07_editMySettings() {
		loginPage.enterIntoUsername("muawiz@developer.com");
        loginPage.enterIntoPassword("Farooqi$123");
        loginPage.clickLoginButton();

        homePage.clickUserMenuDropdown();
        homePage.clickMySettingsLink();

        settingsPage.clickPersonalLink();
        settingsPage.clickLoginHistoryLink();
        settingsPage.downloadLoginHistory();

        Assert.assertTrue(settingsPage.isLoginHistoryDownloaded(), "Login history was not downloaded!");

        settingsPage.clickDisplayLayoutLink();
        settingsPage.clickCustomizeTabsLink();
        settingsPage.selectSalesforceChatter();
        settingsPage.selectReportsTab();
        settingsPage.clickAddButtonInTabs();
        settingsPage.clickSaveButton();

        Assert.assertTrue(settingsPage.isTabAdded("Reports"), "Reports tab was not added!");
        homePage.clickUserMenuDropdown();
        homePage.clickMySettingsLink();

        settingsPage.clickEmailLink();
        settingsPage.clickEmailSettings();
        settingsPage.enterEmailName("Muhammad Muawiz Farooqi");
        settingsPage.enterEmailAddress("muawiz31@gmail.com");
        settingsPage.checkAutoBCC();
        settingsPage.clickSaveButton();

        Assert.assertEquals(settingsPage.getEmailMsgText(), "Your settings have been successfully saved.", "Error occurred in changing name/email!");

        settingsPage.clickCalendarRemindersLink();
        settingsPage.clickActivityRemindersLink();
        settingsPage.clickOpenNewTestReminder();

        Assert.assertEquals(settingsPage.getWindowHeader(driver), "  Sample Event.", "Reminder window did not open!");
    }
	
//	TC08
	@Test
	public void TC08_devConsole() {
		loginPage.enterIntoUsername("muawiz@developer.com");
        loginPage.enterIntoPassword("Farooqi$123");
        loginPage.clickLoginButton();

        homePage.clickUserMenuDropdown();
        
		Assert.assertEquals(homePage.getUserMenuItemsList(),
				List.of("My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience", "Logout"),
				"User menu dropdown is not visible!");

        homePage.clickDevConsoleLink();
        Assert.assertEquals(homePage.getDevConsoleWindowTitle(driver), "Developer Console", "Developer console is not open!");

        homePage.closeDevConsole(driver);
        Assert.assertTrue(homePage.checkDevConsoleClosed(driver), "Developer console is still open!");
    }
	
//	TC09
	@Test
	public void TC09_logout() {
		loginPage.enterIntoUsername("muawiz@developer.com");
        loginPage.enterIntoPassword("Farooqi$123");
        loginPage.clickLoginButton();

        homePage.clickUserMenuDropdown();
        homePage.clickLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not visible after logout!");
    }
	
	@AfterMethod
	public void teardown(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
        screenshot.takeScreenshot(driver, methodName);
        closeBrowser();
	}
}
