package com.makemytrip.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.utilities.ElementUtil;

import io.qameta.allure.Step;

public class homepage {

	
	private By links = By.xpath("//ul[@class='makeFlex font12']//a");
	private By iconImage = By.xpath("//a[@class='mmtLogo makeFlex']");
	private By fromCityBox = By.xpath("//div[contains(@class,'searchCity')]");
	private By fromCityList = By.xpath("//div[contains(@class,'searchCity')]//li");
	private By toCityBox = By.xpath("//div[contains(@class,'searchToCity')]");
	private By toCityList = By.xpath("//div[contains(@class,'searchToCity')]//li");
	private By searchBtn = By.linkText("Search");
	private WebDriver driver;
	private WebDriverWait wait;
	private ElementUtil elementUtil;
	
	
	
	public homepage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	@Step
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	@Step
	public String getImageIconDetails() {
		//driver.findElement(iconImage);
		//driver.findElement(iconImage).click();
		elementUtil.doClick(iconImage);
		wait.until(ExpectedConditions.elementToBeClickable(iconImage));
		return driver.getTitle();
	}
	@Step
	public List<String> getprintAllLinks() {
        
		//List<WebElement> linkElements = driver.findElements(links);
		//elementUtil.getElements(links);
		List<String> linkText = new ArrayList<String>();
		//System.out.println(linkElements.size());
		
		for(WebElement we : elementUtil.getElements(links)) {
		//	System.out.println(we.getText());
			linkText.add(we.getText());
		}
		return linkText;		
	}
	
	@Step
	public void enterFromCity(String fromCity) {
		
		
		By loc = By.xpath("//li[contains(@class,'makeRelative') and @data-cy='account'] ");
		elementUtil.doClick(loc);
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(fromCityBox));
		elementUtil.doClick(fromCityBox);
		By text = By.xpath("//div[contains(@class,'searchCity')]//input");
		wait.until(ExpectedConditions.visibilityOfElementLocated(text));
		By fromText = By.cssSelector("input.react-autosuggest__input");
		elementUtil.doEnterText(fromText,fromCity);
		
		for(WebElement e : elementUtil.getElements(fromCityList)) {
			if(e.getText().contains(fromCity)) {
				e.click();
				break;
			}
		}
	}
	
	@Step
	public void enterToCity(String toCity) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(toCityBox));
		elementUtil.doClick(toCityBox);
		//By text = By.xpath("//div[contains(@class,'searchToCity')]//input");
		By toCityText = By.cssSelector("input#toCity");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(toCityText));
		By toText = By.cssSelector("input.react-autosuggest__input");
		wait.until(ExpectedConditions.visibilityOfElementLocated(toText));
		elementUtil.doEnterText(toText,toCity);
		//driver.findElement(toCityList).sendKeys("Bangalore");
		//List<WebElement> listOfCities = driver.findElements(toCityList);
		for(WebElement e : elementUtil.getElements(toCityList)) {
			if(e.getText().contains(toCity)) {
				e.click();
				break;
			}
		}
	}
	
	@Step
	public void clickSearch() {
		elementUtil.doClick(searchBtn);
	}
	
	@Step
	public void selectCurrentDateFromDatePicker() {
		
		By currentDate = By.xpath("//div[contains(@class,'selected')]");
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(currentDate));
		wait.until(ExpectedConditions.elementToBeClickable(currentDate)).click();
		//elementUtil.doClick(currentDate);
		//elementUtil.getElements(currentDate).get(0).click();
		
		
	}
	
}
