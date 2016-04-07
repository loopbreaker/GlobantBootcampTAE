package com.globant.bootcamp.tae;

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
	// private static fields
	private static String FB_IFRAME_XPATH = "//iframe[@class='fb_ltr']";
	private static String FB_FIRST_COMMENT_XPATH = "//div[contains(@class, '_3-8y')][1]//span[@class='_5mdd']/span";
	
	// private class fields
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
	
	@Test(
			description = "Exercise 01"
			)	
	public void exercise01() {
		// connect
		this.driver.get("http://labrujula24.com/noticias/2016/23180_Macri-imputado-por-las-cuentas-offshore");
		
		// if reaches timeout then asserts false and return
		if (!Utils.waitForElement(FB_IFRAME_XPATH, this.driver, 20)) {
			Assert.fail("20 secs timeout: facebook ccomments iFrame not loaded");
			return;
		}
		WebElement fb_iframe = driver.findElement(By.xpath(FB_IFRAME_XPATH));
		
		// switch to internal frame
		this.driver.switchTo().frame(fb_iframe).switchTo();
		
		// location of first comment		
		// if reaches timeout then asserts false and return
		if (!Utils.waitForElement(FB_FIRST_COMMENT_XPATH, this.driver, 20)) {
			Assert.fail("20 secs timeout: first comment not loaded");
			return;
		}
		
		String comment_text = this.driver.findElement(By.xpath(FB_FIRST_COMMENT_XPATH)).getText();
		
		// return driver to its default context
		driver.switchTo().defaultContent();
		
		// check the comment text on standard output
		System.out.println("Comment text: " + comment_text);
		
		// Assert
		Assert.assertTrue(comment_text.contains("Noticia"));
	}

}