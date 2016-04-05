package com.globant.bootcamp.tae;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class App
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
			description = "Exercise"
			)

	public void exercise01() {
		driver.get("http://www.google.com");
	}

}