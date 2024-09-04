package com.Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Library {

	public static void scrollByPixel(WebDriver driver, int iteration) throws InterruptedException {

		for (int i = 0; i < iteration; i++) {
		Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,450)");
		}
		
	}
}
