package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actionDriver;
	public static final Logger logger=LoggerManager.getLogger(BaseClass.class);

	@BeforeSuite
	public void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
		
	}

	@BeforeMethod
	public void setup() {
		System.out.println("Setting up WebDriver for : " + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		
		//Initialization of actionDriver only once
		if(actionDriver==null) {
			actionDriver=new ActionDriver(driver);
			logger.info("ActionDriver intance is created");
			
		}

	}

	// Initialize the WebDriver based on browser defined in config.prop file
	private void launchBrowser() {
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported : " + browser);
		}

	}

	// configure browser properties such as wait , maximize , getting url
	public void configureBrowser() {
		// Implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// Maximize the browser
		driver.manage().window().maximize();

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to Navigate");
		}
	}

	// driver getter methods
	/*
	 * public WebDriver getDriver() { return driver; }
	 * 
	 * // driver setter methods public void setDriver(WebDriver driver) {
	 * this.driver = driver; }
	 */
	
	//Getter Method for webDriver
	public static WebDriver getDriver() {
		if(driver ==null) {
			System.out.println("WebDriver is not initialized");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		return driver;
	}
	
	//Getter Method for actionDriver
		public static ActionDriver getActionDriver() {
			if(actionDriver ==null) {
				System.out.println("ActionDriver is not initialized");
				throw new IllegalStateException("ActionDriver is not initialized");
			}
			return actionDriver;
		}
	
	public static Properties getProp() {
		return prop;
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		System.out.println("WebDriver Instance is close");
		driver=null;
		System.out.println("ActionDriver Instance is close");
		actionDriver=null;
	}

}
