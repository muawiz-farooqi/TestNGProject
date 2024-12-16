package com.testng.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testng.base.BaseTest;
import com.testng.pages.HomePage;
import com.testng.pages.LoginPage;
import com.testng.utilities.ScreenshotUtilities;

//sequence of steps

public class LoginTest extends BaseTest
{
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ScreenshotUtilities screenshot;
	
	@BeforeMethod
	public void beforeMethod()
	{		
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		screenshot = new ScreenshotUtilities();
	}
	
//	TC01
	@Test
	public void TC01_loginErrorMessage() {
		loginPage.enterIntoUsername("muawiz@developer.com");
		loginPage.clearPassword();
		loginPage.clickLoginButton();
		
		String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Please enter your password.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
	}
	
//	TC02
	@Test
	public void TC02_validLogin() {
		loginPage.enterIntoUsername("muawiz@developer.com");
		loginPage.enterIntoPassword("Farooqi$123");
		loginPage.clickLoginButton();
		
		homePage.waitForLogin();
		
		String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
        Assert.assertEquals(actualTitle, expectedTitle, "Login failed");
    }
	
//	TC03
	@Test
	public void TC03_rememberMe() {
		loginPage.enterIntoUsername("muawiz@developer.com");
		loginPage.enterIntoPassword("Farooqi$123");
		loginPage.checkRememberMe();
		loginPage.clickLoginButton();
		
		homePage.clickUserMenuDropdown();
		homePage.clickLogout();
		
		loginPage.waitForPage();
		
        boolean isRememberMeChecked = loginPage.isRememberMeChecked();
        Assert.assertTrue(isRememberMeChecked, "'Remember Me' checkbox is not selected!");
	}
	
//	TC04-A
	@Test
	public void TC04A_forgotPassword() {
		loginPage.clickForgotPassword();
		loginPage.enterForgottenUsername("muawiz@developer.com");
		loginPage.clickContinue();
		
		String actualMessage = loginPage.getPasswordResetConfirmation();
        String expectedMessage = "Check Your Email";
        Assert.assertEquals(actualMessage, expectedMessage, "Password reset confirmation mismatch!");
	}
	
//	TC04-B
	@Test
	public void TC04B_validateLoginErrorMsg() {
		loginPage.enterIntoUsername("123");
		loginPage.enterIntoPassword("22131");
		loginPage.clickLoginButton();

		String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for invalid login mismatch!");
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
	    screenshot.takeScreenshot(driver, result.getMethod().getMethodName());
	    closeBrowser();
	}
}
