package com.globant.bootcamp.tae;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Ex01
{
	private WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			driver = new FirefoxDriver();
		} catch (Exception e) {
		}
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context){
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
		}
	}
	
	public WebElement findElement(WebDriver driver, By locator, int seconds) {
		try {
			seconds = (seconds == 0 ? 1 : seconds);
			this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	
	// A version of waitWhileElementPresentXpath
	// This one reverses the logic waiting for the element to the loaded
	public boolean waitForElement(String locator, int maxSeconds) {
		long initialMillis = System.currentTimeMillis ();
		boolean loaded = false;
		boolean timeout = false ;
		do {
			try {
				this.driver.findElement(By.xpath(locator));
				loaded= true;
			} catch (Exception e){	}
			
			long currentMillis = System. currentTimeMillis ();
			if ((currentMillis - initialMillis)/ 1000 >= maxSeconds){
				timeout = true ;
			}
		} while (!loaded && !timeout);
		if (timeout){
			System.out.println("Timeout: "+ maxSeconds + " seconds have passed waiting for page");
			return false ;
		} else {
			return true ;
		}
	} 
	
	@Test(
			description = "Exercise 01"
			)	
	public void exercise01() {
		// connect
		driver.get("http://labrujula24.com/noticias/2016/23099_Mauricio-Macri-hablo-sobre-su-sociedad-offshore-qEs-una-operacion-legalq");
		
		// iframe locator
		String locator = "//iframe[@class='fb_ltr']";

		// If reaches timeout then asserts false and return
		if (!this.waitForElement(locator, 20)) {
			Assert.assertTrue(false);
			return;
		}
			
		WebElement fb_iframe = driver.findElement(By.xpath(locator));
		
		// switch to internal frame
		this.driver.switchTo().frame(fb_iframe).switchTo();
		
		// location of first comment
		String first_comment = "//div[contains(@class, '_3-8y')][1]";
		String comment_text = this.driver.findElement(By.xpath(first_comment)).getText();
		
		// return driver to its default context
		driver.switchTo().defaultContent();
		
		// check the comment text on standard output
		System.out.println("Noticia: " + comment_text);
		
		// Assert
		Assert.assertTrue(comment_text.contains("Noticia"));
	}

}