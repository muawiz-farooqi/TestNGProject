package com.testng.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testng.base.BasePage;

public class ProfilePage extends BasePage {
	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="(//img[@alt='Edit Profile'])[1]")
	WebElement editContactInfoButton;
	
	@FindBy(id="contactInfoContentId")
	WebElement iFrameEditContactInfo;
	
	@FindBy(xpath="//a[contains(text(),'About')]")
	WebElement aboutTab;
	
	@FindBy(id="lastName")
	WebElement newLastname;
	
	@FindBy(xpath="//input[@value='Save All']")
	WebElement saveAllProfileChangesButton;
	
	@FindBy(xpath="//span[text()='Post']")
	WebElement postLink;
	
	@FindBy(xpath="//iframe[@class='cke_wysiwyg_frame cke_reset']")
	WebElement iFramePost;
	
	@FindBy(xpath="//body[text()='Share an update, @mention someone...']")
	WebElement postTextBox;	
	
	@FindBy(id="publishersharebutton")
	WebElement shareButton;
	
	@FindBy(xpath="//span[text()='File']")
	WebElement fileLink;
	
	@FindBy(id="chatterUploadFileAction")
	WebElement localFileUploadLink;
	
	@FindBy(id="chatterFile")
	WebElement chooseFilePath;
	
	@FindBy(id="tailBreadcrumbNode")
	WebElement profileName;
	
	@FindBy(xpath="(//img[@alt='Muhammad Muawiz Farooqixyz'])[1]")
	WebElement profilePhoto;
	
	@FindBy(id="uploadLink")
	WebElement addPhotoLink;
	
	@FindBy(id="deletePhoto")
	WebElement deletePhotoLink;
	
	@FindBy(id="uploadPhotoContentId")
	WebElement uploadPhotoFrame;
	
	@FindBy(id="j_id0:uploadFileForm:uploadInputFile")
	WebElement chooseProfilePicButton;
	
	@FindBy(id="j_id0:uploadFileForm:uploadBtn")
	WebElement uploadPhotoButton;
	
	@FindBy(xpath="//div[@class='imgCrop_handle imgCrop_handleNE']")
	WebElement NEHandle;
	
	@FindBy(xpath="//div[@class='imgCrop_handle imgCrop_handleSE']")
	WebElement SEHandle;
	
	@FindBy(xpath="//div[@class='imgCrop_handle imgCrop_handleNW']")
	WebElement NWHandle;
	
	@FindBy(xpath="//div[@class='imgCrop_handle imgCrop_handleSW']")
	WebElement SWHandle;
	
	@FindBy(id="j_id0:j_id7:save")
	WebElement saveProfilePicButton;
	
	@FindBy(xpath="(//span[@class='feeditemtext cxfeeditemtext'])[1]")
	WebElement latestTextPost;
	
	@FindBy(xpath="//span[@class='contentTitleLink']")
	WebElement latestFilePost;
	
	@FindBy(id="uploadProgressDialog")
	WebElement fileUploadOverlay;
	
	
	public void clickEditContactInfoButton() {
		waitForElement(editContactInfoButton);
		editContactInfoButton.click();
	}

	public void clickAboutTab(WebDriver driv) {
		this.driver = driv;
		waitForElement(iFrameEditContactInfo);
		driver.switchTo().frame(iFrameEditContactInfo);
		
		waitForElement(aboutTab);
		aboutTab.click();
		
		driver.switchTo().defaultContent();
	}

	public void enterEditLastname(String strNewLastname, WebDriver driv) {
		this.driver = driv;
		driver.switchTo().frame(iFrameEditContactInfo);
		
		waitForElement(newLastname);
		newLastname.sendKeys(strNewLastname);

		driver.switchTo().defaultContent();
	}

	public void clickSaveAllProfileChanges(WebDriver driv) {
		this.driver = driv;
		driver.switchTo().frame(iFrameEditContactInfo);
		
		waitForElement(saveAllProfileChangesButton);
		saveAllProfileChangesButton.click();
		
		driver.switchTo().defaultContent();
	}

	public void clickPostLink() {
		waitForElement(postLink);
		postLink.click();
	}

	public void enterPostText(String postText, WebDriver driv) {
		this.driver = driv;
		waitForElement(iFramePost);
		driver.switchTo().frame(iFramePost);
		
		waitForElement(postTextBox);
		postTextBox.sendKeys(postText);
		
		driver.switchTo().defaultContent();
	}

	public void clickShareButton() {
		waitForElement(shareButton);
		shareButton.click();
	}

	public void clickFileLink(WebDriver driv) {
		this.driver = driv;
		driver.switchTo().defaultContent();
		waitForElement(fileLink);
		fileLink.click();
	}
	
	public void clickUploadLocalFileButton() {
		waitForElement(localFileUploadLink);
		localFileUploadLink.click();
	}

	public void uploadSelectedFile(String filePath) {
		waitForElement(chooseFilePath);
		chooseFilePath.sendKeys(filePath);
	}

	public void hoverOverProfilePic() {
		waitForElement(profilePhoto);
		hoverOver(profilePhoto);
	}

	public void clickAddPhotoLink() {
		waitForElement(addPhotoLink);
		addPhotoLink.click();
	}

	public void uploadNewProfilePic(String profilePicLink, WebDriver driv) {
		this.driver = driv;
		waitForElement(uploadPhotoFrame);
		driver.switchTo().frame(uploadPhotoFrame);
		
		waitForElement(chooseProfilePicButton);
		chooseProfilePicButton.sendKeys(profilePicLink);
	}

	public void clickUploadButton(WebDriver driv) {
		this.driver = driv;
		waitForElement(uploadPhotoButton);
		uploadPhotoButton.click();
	}

	public void cropProfilePicture() {
		Actions actions = new Actions(driver);
		
		waitForElement(NEHandle);
		actions.dragAndDropBy(NEHandle, 20, 20).build().perform();
		waitForElement(SEHandle);
		actions.dragAndDropBy(SEHandle, 20, -20).build().perform();
		waitForElement(NWHandle);
		actions.dragAndDropBy(NWHandle, -20, 20).build().perform();
		waitForElement(SWHandle);
		actions.dragAndDropBy(SWHandle, -20, -20).build().perform();
	}

	public void clickSaveProfilePicButton(WebDriver driv) {
		this.driver = driv;
		waitForElement(saveProfilePicButton);
		saveProfilePicButton.click();
		
		driver.switchTo().defaultContent();
	}

	public String getProfileName() {
		waitForElement(profileName);
		return profileName.getText();
	}

	public String getLatestPost() {
		waitForElement(latestTextPost);
		return latestTextPost.getText();
	}

	public String getLatestFile() {
		waitForElement(latestFilePost);
		return latestFilePost.getText();
	}

	public boolean isProfilePicUpdated() {
		try {
			waitForElement(deletePhotoLink);
			return true;
		}
		catch (TimeoutException e) {
	       return false;
	   }
	}
	
	public boolean isPostPresent(String text) {
		waitForElement(latestTextPost);

		try {
			waitForElementText(latestTextPost, text);
			System.out.println("[DEBUG]: POST FOUND");
			return true;
		}
		catch (TimeoutException e) {
			System.out.println("[DEBUG]: POST TIMEOUT");
			System.out.println(latestTextPost.getText());
			System.out.println(text);
	       return false;
	   }
	}

	public boolean isFilePresent(String text) {
		waitForElement(latestFilePost);
		
		try {
			waitForElementText(latestFilePost, text);
			System.out.println("[DEBUG]: FILE FOUND");
			return true;
		}
		catch (TimeoutException e) {
			System.out.println("[DEBUG]: FILE TIMEOUT");
			System.out.println(latestFilePost.getText());
			System.out.println(text);
	       return false;
	   }
	}

	public void waitForFileOverlay(WebDriver driv) {
		this.driver = driv;
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOf(fileUploadOverlay));
	}

	public void restoreLastName(String strNewLastname, WebDriver driv) {
		this.driver = driv;
		driver.switchTo().frame(iFrameEditContactInfo);
		
		waitForElement(newLastname);
		newLastname.clear();
		newLastname.sendKeys("Farooqi"+strNewLastname);

		driver.switchTo().defaultContent();
	}
	
	public void waitForPost()
	{
		sleep(2);
	}
}
