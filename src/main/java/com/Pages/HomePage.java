package com.Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utility.Library;


public class HomePage {

	private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
		
	@FindBy(xpath = "//div[text()='Revenue Calculator']")
	private WebElement revenueCalculatorButton;

	@FindBy(xpath = "//span[contains(@class,'MuiSlider-thumb MuiSlider')]")
	private WebElement slider;

	@FindBy(xpath = "//input[@type='range']")
	private WebElement sliderAfterUpdate;

	@FindBy(xpath = "//input[@id=':r0:']")
	private WebElement sliderTextField;

	@FindBy(xpath = "//p[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"]/following-sibling::label//span//input")
	private List<WebElement> cptCodes;

	@FindBy(xpath = "(//p[contains(@class,'MuiTypography-root MuiTypography-')])[8]")
	private WebElement reimbursement;

	//=========================================================
	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	//==========================================================
	
	
	public void navigateToRevenueCalculator() {
		LOGGER.info("navigat to revenueCalculater Page..");
		revenueCalculatorButton.click();
	}

	public void adjustSliderAndSetValue(WebDriver driver) {
		LOGGER.info("Try to adust slider value = 820");
		Actions action = new Actions(driver);
		action.clickAndHold(slider).moveByOffset(93, 0).release().perform();
	}

	public void validateSliderValue() throws InterruptedException {
		LOGGER.info("validate slider value with adjusted slider value = 820");
		Thread.sleep(2000);
		String getValue = sliderTextField.getAttribute("value");
		System.out.println(getValue);
		String expValue = "820";
		Assert.assertEquals(getValue, expValue);
	}

	public void updateTextFieldAndValidateSliderValue() throws InterruptedException {
		LOGGER.info("insert slider text fields = 560 and validate with slider");
		Thread.sleep(2000);
		String updatedTextField = "560";
		sliderTextField.clear();
		sliderTextField.sendKeys(updatedTextField);
		Thread.sleep(2000);
		String actualTextField = sliderAfterUpdate.getAttribute("value");
		System.out.println(actualTextField);
		Assert.assertEquals(actualTextField, updatedTextField);
	}

	public void selectCPTCodes(WebDriver driver) throws InterruptedException {
		LOGGER.info("scroll down ans and select given cpt codes ");
		Library.scrollByPixel(driver, 1);
		Thread.sleep(2000);
		System.out.println(cptCodes.size());
		
		for (int i = 0; i < cptCodes.size(); i++) {
			cptCodes.get(0).click();
			cptCodes.get(1).click();
			cptCodes.get(2).click();
			cptCodes.get(7).click();
			break;

		}

	}

	public void verifyTotalReimbursement(WebDriver driver) throws InterruptedException {
		LOGGER.info("validate Total Recurring Reimbursement for all Patients Per Month=$110700");
		sliderTextField.clear();
		sliderTextField.sendKeys("820");
		Library.scrollByPixel(driver, 1);
		Thread.sleep(2000);
		String getReimbursementValue = reimbursement.getText();
		System.out.println(getReimbursementValue);
		String expectedReimburesement = "$110700";
		Assert.assertEquals(getReimbursementValue, expectedReimburesement);
	}
}
