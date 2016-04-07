package com.globant.bootcamp.tae;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils
{
	// A version of waitWhileElementPresentXpath
	// This one reverses the logic waiting for the element to the loaded
	public static boolean waitForElement(String locator, WebDriver driver, int maxSeconds) {
		long initialMillis = System.currentTimeMillis ();
		boolean loaded = false;
		boolean timeout = false ;
		do {
			try {
				driver.findElement(By.xpath(locator));
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
}