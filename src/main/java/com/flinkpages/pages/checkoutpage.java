package com.flinkpages.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.flinkpages.utilities.ElementUtil;
import com.flinkpages.utilities.JavaScriptUtil;


public class checkoutpage{

	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;

	private By productNamesSelected = By.xpath("//tbody//td");
	private By payForBtn = By.xpath("//button/span[contains(text(),'Pay')]");
	private By emailTextBox = By.xpath("//input[@id='email']");
	private By cvvNumber = By.id("cc-csc");
	private By zipNumberTxtBx = By.id("billing-zip");
	private By submitCCBtn =  By.xpath("//span[@class='iconTick']");
	private By frameForPaymentPopup = By.xpath("//iframe[contains(@src,'stripe')]");



	public checkoutpage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getCheckoutPageTitle() {
		return driver.getTitle();
	}



	public ArrayList<String> getProductNames() {


		List<WebElement> elements = elementUtil.getElements(productNamesSelected);

		ArrayList<String> productNames = new ArrayList<String>();

		for(int i = 0;i<elements.size();i++) {
			if(i%2==0)
				productNames.add(elements.get(i).getText());
		}
		return productNames;

	}

	public ArrayList<Integer> getProductValues() {

		String productNamexpath = "//tbody//td";
		List<WebElement> elements = elementUtil.getElements(By.xpath(productNamexpath));

		ArrayList<Integer> productValues = new ArrayList<Integer>();

		for(int i = 0;i<elements.size();i++) {
			if(i%2!=0) {
				productValues.add(Integer.parseInt(elements.get(i).getText()));
			}
		}
		return productValues;

	}

	public int getTotalValues() {
		String finalValueText = elementUtil.getElement(By.id("total")).getText();
		String[] values = finalValueText.split(" ");
		int totalValue = Integer.parseInt(values[values.length-1]);
		return totalValue;

	}

	public boolean verifyProductTotal() {

		int sum = getProductValues().stream().mapToInt(x->x).sum();
		if(sum==getTotalValues()) {
			return true;
		}
		return false;

	}

	public confirmationpage makePayment() throws InterruptedException {

		elementUtil.waitForElementsVisible(payForBtn,30).click();
		elementUtil.moveToFrame(frameForPaymentPopup);
		elementUtil.waitForElementsVisible(By.xpath("//h1"),30);
		elementUtil.waitForElementsVisible(emailTextBox,30).sendKeys(RandomStringUtils.randomAlphabetic(10)+"@hotmail.com");
		jsUtil.sendKeysUsingWithId("card_number",elementUtil.getRandomCreditCardNumbers());
		jsUtil.sendKeysUsingWithId("cc-exp","03/22");		
		elementUtil.doEnterText(cvvNumber, RandomStringUtils.randomNumeric(3));
		elementUtil.waitForElementsVisible(zipNumberTxtBx,30).sendKeys(RandomStringUtils.randomNumeric(6));
		elementUtil.waitForElementsVisible(submitCCBtn,30).click();
		elementUtil.moveToDefaultFromFrame();
		return new confirmationpage(driver);

	}

}
