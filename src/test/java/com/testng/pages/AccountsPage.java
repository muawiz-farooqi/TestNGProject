package com.testng.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.testng.base.BasePage;

//elements of corresponding screen/page
//its action for the elements
// Automation
// 	- finding element
//	- entering data
//	- performing action
//	---- sequencing them ----

public class AccountsPage extends BasePage {
	WebDriver driver;
	
	@FindBy(xpath="//h1[@class='pageType']")
	WebElement pageHeading;
	
	@FindBy(name="new")
	WebElement newButton;
	
	@FindBy(id="acc2")
	WebElement accountNameBox;
	
	@FindBy(id="acc6")
	WebElement typeSelection;
	
	@FindBy(id="00Nak000006gZ9C")
	WebElement prioritySelection;
	
	@FindBy(xpath="(//input[@value=' Save '])[1]")
	WebElement saveAccButton;
	
	@FindBy(xpath="//h2[@class='topName']")
	WebElement accNameHeading;
	
	
	public AccountsPage(WebDriver driver) {
		super(driver);
	}

	public boolean isAccountsPageDisplayed() {
		try {
			waitForElement(pageHeading);
			if (pageHeading.getText().equalsIgnoreCase("Accounts"))
			{
				return true;
			}
			else {
				return false;
			}
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
	

	public void clickNewButton() {
		waitForElement(newButton);
		newButton.click();
	}

	public void enterAccountName(String accName) {
		waitForElement(accountNameBox);
		accountNameBox.sendKeys(accName);
	}

	public void selectType(String type) {
		waitForElement(typeSelection);
		new Select(typeSelection).selectByValue(type);
	}

	public void selectCustomerPriority(String priority) {
		waitForElement(prioritySelection);
		new Select(prioritySelection).selectByValue(priority);
	}

	public void clickSaveButton() {
		waitForElement(saveAccButton);
		saveAccButton.click();
	}

	public boolean isAccountCreated(String accName) {
		try {
			waitForElement(accNameHeading);
			if (accNameHeading.getText().equals(accName)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
}
