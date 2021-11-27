package com.flinkpages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.flinkpages.utilities.ElementUtil;

public class confirmationpage {

	private WebDriver driver;

	private ElementUtil elementUtil;

	private By successMessage = By.cssSelector("div.justify-content-center>h2");


	public confirmationpage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);

	}

	public String getConfirmationPageTitle() {
		return driver.getTitle();
	}


	public String getSuccessMessage() {

		elementUtil.getCurrentURL("confirmation");

		return elementUtil.waitForElementPresenceWithWebDriverWait(successMessage,40,2).getText();
	}

}
