package com.testng.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testng.base.BasePage;

public class HomePage extends BasePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="userNavLabel")
	WebElement userMenuDropdown;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logoutButton;
	
	@FindBy(xpath="//a[text()='My Profile']")
	WebElement myProfileButton;
	
	@FindBy(xpath="//a[text()='My Settings']")
	WebElement mySettingsButton;
	
	@FindBy(xpath="//a[text()='Developer Console']")
	WebElement devConsoleLink;
	
	@FindBy(xpath="//div[@id='userNav-menuItems']/a")
	List<WebElement> userMenuItems;
	
	@FindBy(xpath="//a[@href='/001/o']")
	WebElement accountsPageLink;

	public void clickUserMenuDropdown() {
		waitForElement(userMenuDropdown);
		userMenuDropdown.click();
	}

	public void clickLogout() {
		waitForElement(logoutButton);
		logoutButton.click();
	}

	public void clickMyProfileLink() {
		waitForElement(myProfileButton);
		myProfileButton.click();
	}

	public void clickMySettingsLink() {
		waitForElement(mySettingsButton);
		mySettingsButton.click();
	}

	public void clickDevConsoleLink() {
		waitForElement(devConsoleLink);
		devConsoleLink.click();
	}

	public void closeDevConsole(WebDriver driv) {
		this.driver = driv;
		String parentHandle = driver.getWindowHandle();

		waitForWindows(2);
		
		for (String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
		}
		
		driver.close();
		driver.switchTo().window(parentHandle);
	}

	public void waitForLogin() {
		waitForElement(userMenuDropdown);
	}

	public List<String> getUserMenuItemsList() {
		waitForElement(userMenuItems.get(0));
		
		List<String> stringList = new ArrayList<String>();
	    for (WebElement element : userMenuItems) {
	        stringList.add(element.getText());
	    }
	    return stringList;
	}

	public String getDevConsoleWindowTitle(WebDriver driv) {
		this.driver = driv;
		String parentHandle = driver.getWindowHandle();
			
		waitForWindows(2);
		
		for (String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
		}
		
		String windowTitle = driver.getTitle();
		driver.switchTo().window(parentHandle);
		
		return windowTitle;
	}
	
	public boolean checkDevConsoleClosed(WebDriver driv) {
		this.driver = driv;
		
		try {
			waitForWindows(1);
			return (!driver.getTitle().equals("Developer Console"));
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}

	public void clickAccountsLink() {
		waitForElement(accountsPageLink);
		accountsPageLink.click();
	}
}
