package com.testng.pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.testng.base.BasePage;

public class SettingsPage extends BasePage {
	WebDriver driver;

	public SettingsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@href=\"javascript:HTMLTreeNode.prototype.toggleHTMLTree('PersonalInfo');\"]")
	WebElement personalLink;

	@FindBy(id="LoginHistory_font")
	WebElement loginHistoryLink;

	@FindBy(xpath="//a[contains(text(),'Download login history')]")
	WebElement downloadHistoryButton;

	@FindBy(xpath="//a[@href=\"javascript:HTMLTreeNode.prototype.toggleHTMLTree('DisplayAndLayout');\"]")
	WebElement displayLayoutLink;

	@FindBy(id="CustomizeTabs_font")
	WebElement customizeTabsLink;

	@FindBy(id="p4")
	WebElement selectCustomApp;

	@FindBy(xpath="//option[text()='Reports']")
	WebElement reportsTab;

	@FindBy(id="duel_select_0_right")
	WebElement addButtonTabs;

	@FindBy(xpath="//a[@href=\"javascript:HTMLTreeNode.prototype.toggleHTMLTree('EmailSetup');\"]")
	WebElement emailLink;

	@FindBy(id="EmailSettings_font")
	WebElement emailSettings;

	@FindBy(id="sender_name")
	WebElement emailNameBox;

	@FindBy(id="sender_email")
	WebElement emailAddressBox;

	@FindBy(id="auto_bcc1")
	WebElement autoBCCBox;

	@FindBy(name="save")
	WebElement saveButton;

	@FindBy(xpath="//a[@href=\"javascript:HTMLTreeNode.prototype.toggleHTMLTree('CalendarAndReminders');\"]")
	WebElement calendarRemindersLink;

	@FindBy(id="Reminders_font")
	WebElement activityRemindersLink;
	
	@FindBy(id="testbtn")
	WebElement openNewTestReminderButton;
	
	@FindBy(xpath="//span[@id='tsidLabel']")
	WebElement menuButton;
	
	@FindBy(xpath="//a[text()='Salesforce Chatter']")
	WebElement salesforceChatterButton;
	
	@FindBy(xpath="//a[text()='Sales']")
	WebElement salesButton;
	
	@FindBy(xpath="//ul[@id='tabBar']/li")
	List<WebElement> salesforceChatterTabs;
	
	@FindBy(xpath="//div[@class='messageText']")
	WebElement emailMsgText;
	
	@FindBy(xpath="//div[@id='summary0']/div")
	WebElement windowDivHeader;

	public void clickPersonalLink() {
		waitForElement(personalLink);
		personalLink.click();
	}
	public void clickLoginHistoryLink() {
		waitForElement(loginHistoryLink);
		loginHistoryLink.click();
	}
	public void downloadLoginHistory() {
		waitForElement(downloadHistoryButton);
		downloadHistoryButton.click();
	}
	public void clickDisplayLayoutLink() {
		waitForElement(displayLayoutLink);
		displayLayoutLink.click();
	}
	public void clickCustomizeTabsLink() {
		waitForElement(customizeTabsLink);
		customizeTabsLink.click();
	}
	public void selectSalesforceChatter() {
		waitForElement(selectCustomApp);
		new Select(selectCustomApp).selectByVisibleText("Salesforce Chatter");
	}
	public void selectReportsTab() {
		waitForElement(reportsTab);
		reportsTab.click();
	}
	public void clickAddButtonInTabs() {
		waitForElement(addButtonTabs);
		addButtonTabs.click();
	}
	public void clickEmailLink() {
		waitForElement(emailLink);
		emailLink.click();
	}
	public void clickEmailSettings() {
		waitForElement(emailSettings);
		emailSettings.click();
	}
	public void enterEmailName(String emailName) {
		waitForElement(emailNameBox);
		emailAddressBox.clear();
		emailNameBox.sendKeys(emailName);
	}
	public void enterEmailAddress(String emailAddress) {
		waitForElement(emailAddressBox);
		emailAddressBox.clear();
		emailAddressBox.sendKeys(emailAddress);
	}
	public void checkAutoBCC() {
		waitForElement(autoBCCBox);
		autoBCCBox.click();
	}
	public void clickSaveButton() {
		waitForElement(saveButton);
		saveButton.click();
	}
	public void clickCalendarRemindersLink() {
		waitForElement(calendarRemindersLink);
		calendarRemindersLink.click();
	}
	public void clickActivityRemindersLink() {
		waitForElement(activityRemindersLink);
		activityRemindersLink.click();
	}
	public void clickOpenNewTestReminder() {
		waitForElement(openNewTestReminderButton);
		openNewTestReminderButton.click();
	}
	public boolean isLoginHistoryDownloaded() {
		String expectedFileName = "LoginHistory";
        File downloadFolder = new File("C:\\Users\\muawi\\Downloads");

        int timeoutSeconds = 30;
        int waited = 0;

        while (waited < timeoutSeconds) {
            File[] files = downloadFolder.listFiles((dir, name) -> name.contains(expectedFileName));
            if (files != null && files.length > 0) {
                return true;
            }
            try {
                Thread.sleep(1000);
                waited++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
	}
	public boolean isTabAdded(String tabName) {
		waitForElement(menuButton);
		String original_tab = menuButton.getText();
		if (!original_tab.equalsIgnoreCase("Salesforce Chatter"))
		{
			menuButton.click();
			
			waitForElement(salesforceChatterButton);
			salesforceChatterButton.click();
		}
		
		waitForElement(salesforceChatterTabs.get(0));

		boolean output = false;
		
		for (WebElement tab : salesforceChatterTabs)
		{
			if (tab.getText().equalsIgnoreCase(tabName))
			{				
				output = true;
				break;
			}
		}
		
		menuButton.click();
		waitForElement(salesButton);
		salesButton.click();
		
		return output;
	}
	public String getEmailMsgText() {
		waitForElement(emailMsgText);
		return emailMsgText.getText();
	}
	public String getWindowHeader(WebDriver driv) throws TimeoutException {
		this.driver = driv;
		
		String parentHandle = driver.getWindowHandle();
		
		waitForWindows(2);
		
		for (String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
		}
		
		String headerText = windowDivHeader.getText();
		
		driver.close();
		driver.switchTo().window(parentHandle);
		
		return headerText;
	}
}
