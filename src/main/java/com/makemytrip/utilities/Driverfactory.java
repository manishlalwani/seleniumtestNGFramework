package com.makemytrip.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {

	private WebDriver driver;
	private Properties prop;
	private String browser;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	
	
	public WebDriver setUpDriver(Properties prop) {

		browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		    tlDriver.set(new ChromeDriver());
		}
		else if (browser.equalsIgnoreCase("ff") || browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}

		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		// Launch URL
		getDriver().get(prop.getProperty("applicationUrl"));
		return getDriver();

	}

	public Properties readPropertiesFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(
					new FileReader("./src/main/java/com/makemytrip/properties/application.properties"));
			prop = new Properties();
			try {
				prop.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config properties file is not found");
		}

		return prop;
	}

	public void tearDown() {

		driver.quit();
	}
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}

}
