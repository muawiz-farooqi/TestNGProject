package com.testng.base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//common methods across all pages

public class BasePage {
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForWindows(int windows) {
		WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.numberOfWindowsToBe(windows));
	}
	
	public void waitForElementText(WebElement element, String text) throws TimeoutException {
    	WebDriverWait wait = new WebDriverWait(driver, 7);
    	wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void hoverOver(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public void sleep(int time)
	{
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
