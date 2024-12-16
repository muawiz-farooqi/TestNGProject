package com.testng.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.testng.base.BaseTest;
import com.testng.pages.AccountsPage;
import com.testng.pages.HomePage;
import com.testng.pages.LoginPage;
import com.testng.utilities.ScreenshotUtilities;

//sequence of steps

public class AccountsTest extends BaseTest
{
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	AccountsPage accountsPage;
	ScreenshotUtilities screenshot;
	
	@BeforeMethod
	public void beforeMethod()
	{		
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		accountsPage = new AccountsPage(driver);
		
		screenshot = new ScreenshotUtilities();
	}
	
//	TC10
	@Test
	public void TC10_createAccount() {
		loginPage.enterIntoUsername("muawiz@developer.com");
        loginPage.enterIntoPassword("Farooqi$123");
        loginPage.clickLoginButton();
		homePage.waitForLogin();
        Assert.assertEquals(driver.getTitle(), "Home Page ~ Salesforce - Developer Edition", "Login failed");
		
		homePage.clickAccountsLink();
		Assert.assertTrue(accountsPage.isAccountsPageDisplayed(), "Accounts Page is not displayed");
		
		accountsPage.clickNewButton();
		accountsPage.enterAccountName("TestAccount321");
		accountsPage.selectType("Technology Partner");
		accountsPage.selectCustomerPriority("High");
		accountsPage.clickSaveButton();
		Assert.assertTrue(accountsPage.isAccountCreated("TestAccount321"), "Account creation failed");
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
	    screenshot.takeScreenshot(driver, result.getMethod().getMethodName());
	    closeBrowser();
	}
}
