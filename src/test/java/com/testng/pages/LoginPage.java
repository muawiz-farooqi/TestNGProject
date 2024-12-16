package com.testng.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testng.base.BasePage;

//elements of corresponding screen/page
//its action for the elements
// Automation
// 	- finding element
//	- entering data
//	- performing action
//	---- sequencing them ----

public class LoginPage extends BasePage {
	WebDriver driver;
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(name="Login")
	WebElement loginButton;
	
	@FindBy(id="rememberUn")
	WebElement rememeberMeBox;
	
	@FindBy(id="forgot_password_link")
	WebElement forgotPasswordLink;	
	
	@FindBy(id="un")
	WebElement forgottenUsername;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(id="error")
	WebElement errorMsg;
	
	@FindBy(id="header")
	WebElement headerMsg;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterIntoUsername(String strUsername) {
		waitForElement(username);
		username.sendKeys(strUsername);
	}
	
	public void enterIntoPassword(String strPassword) {
		waitForElement(password);
		password.sendKeys(strPassword);
	}
	
	public void clickLoginButton() {
		waitForElement(loginButton);
		loginButton.click();
	}

	public void clearPassword() {
		waitForElement(password);
		password.clear();
	}

	public void checkRememberMe() {
		waitForElement(rememeberMeBox);
		if (!rememeberMeBox.isSelected()) {
			rememeberMeBox.click();
		}
	}

	public void clickForgotPassword() {
		waitForElement(forgotPasswordLink);
		forgotPasswordLink.click();
	}

	public void enterForgottenUsername(String strUsername) {
		waitForElement(forgottenUsername);
		forgottenUsername.sendKeys(strUsername);
	}

	public void clickContinue() {
		waitForElement(continueButton);
		continueButton.click();
	}
	
	public void waitForPage() {
		waitForElement(password);
	}

	public String getErrorMessage() {
		waitForElement(errorMsg);
		return errorMsg.getText();
	}

	public boolean isRememberMeChecked() {
		return rememeberMeBox.isSelected();
	}

	public String getPasswordResetConfirmation() {
		waitForElement(headerMsg);
		return headerMsg.getText();
	}
	
	public boolean isLoginPageDisplayed() {
		try {
			waitForElement(password);
			return true;
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
}
