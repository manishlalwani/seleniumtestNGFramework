package com.makemytrip.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;	}
	
	public WebElement getElement(By locator) {
		try {
		return driver.findElement(locator);
		}catch(Exception e) {
			System.out.println("Element not present : "+locator);
			return null;
		}
	}
	
	public List<WebElement> getElements(By locator){
		
		try {
			return driver.findElements(locator);
			}catch(Exception e) {
				System.out.println("Element not present : "+locator);
				return null;
			}	
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doEnterText(By locator,String textToEnter) {
		getElement(locator).sendKeys(textToEnter);
	}
	
	
	

}
