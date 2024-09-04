package com.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Pages.HomePage;
import com.Utility.BaseClass;

public class HomeTest extends com.Utility.BaseClass {
	HomePage home;
	@BeforeClass
	public void setUp() {
	home=new HomePage(driver);
	}
	
	@Test
	public void navigateToRevenueCalculate() {
		home.navigateToRevenueCalculator();
		
	}
	
	
	
}
