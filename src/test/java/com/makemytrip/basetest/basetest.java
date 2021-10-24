package com.makemytrip.basetest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.makemytrip.pages.homepage;
import com.makemytrip.utilities.Driverfactory;



public class basetest {
	
	Driverfactory df;
	
	public WebDriver driver;
	public homepage hp;
	public Properties prop;
	
	
	

	
	
	@BeforeTest
	public void setUp() {
		
		
		df = new Driverfactory();
		prop = df.readPropertiesFile();
		driver = df.setUpDriver(prop);
		hp = new homepage(driver);
		
	}
	
	@AfterTest
	public void endTestCase() {
		driver.quit();
	}

}
