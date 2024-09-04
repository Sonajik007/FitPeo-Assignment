package com.Utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public static WebDriver driver;
	@BeforeClass
	public void setUp() {
		driver=new FirefoxDriver();
		driver.get("https://www.fitpeo.com/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
            driver.quit();
        }
	}
}
