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
		driver.get("http://labrujula24.com/noticias/2016/23099_Mauricio-Macri-hablo-sobre-su-sociedad-offshore-qEs-una-operacion-legalq");
		
		// iframe locator
		String locator = "//iframe[@class=\"fb_ltr\"]";
		WebElement fb_iframe = driver.findElement(By.xpath(locator));
		
		// switch to internal frame
		this.driver.switchTo().frame(fb_iframe).switchTo();
		
		// location of first comment
		String first_comment = "/html/body/div[1]/div/div/div/div/div[3]/div[1]";
		String comment_text = this.driver.findElement(By.xpath(first_comment)).getText();
		
		// check the comment text on standard output
		System.out.println("Noticia: " + comment_text);
		
		// assert
		Assert.assertTrue(comment_text.contains("Noticia"));
	}

}