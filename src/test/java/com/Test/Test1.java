package com.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.Pages.HomePage;
import com.Utility.BaseClass;
import com.Utility.Library;

public class Test1 extends BaseClass {
	HomePage home;

	@Test (priority=1)
	public void initializeHomePage() {
		home = new HomePage(driver);

	}

	@Test (priority=2)
	public void navigateToRevenueCalculator() {
		home.navigateToRevenueCalculator();
	}

	@Test (priority=3)
	public void scrollDownToTheSliderSectionAndAdjustAndValidateValue() throws InterruptedException {
		Library.scrollByPixel(driver, 1);
		home.adjustSliderAndSetValue(driver);
		home.validateSliderValue();
	}

	@Test (priority=4)
	public void updateAndValidateTextField () throws InterruptedException {
		home.updateTextFieldAndValidateSliderValue();
	}
	
	@Test (priority=5)
	public void selectGivenCPTCodes() throws InterruptedException {
		home.selectCPTCodes(driver);
	}
	
	@Test (priority=6)
	public void validateTotalRecurringReimbursement() throws InterruptedException {
		home.verifyTotalReimbursement(driver);
	}
	
	
	
}
