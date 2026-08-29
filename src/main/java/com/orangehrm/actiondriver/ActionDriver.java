package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver extends BaseClass {
	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
		System.out.println("WebDriver Instance is created");
	}

	// Method to click element
	public void click(By by) {
		String elementDescription=getElementDescription(by);
		waitForElementToBeClickable(by);
		driver.findElement(by).click();
		logger.info("clicked on element ----->"+elementDescription);

	}

	// Method to enter text into an input field
	public void enterText(By by, String value) {
		String elementDescription=getElementDescription(by);
		waitForElementToBeVisibile(by);
		WebElement element = driver.findElement(by);
		element.clear();
		logger.info("cleared element ----->"+value+elementDescription+"--->"+value);
		element.sendKeys(value);
	}

	// Method to get text from an input field
	public String getText(By by) {
		waitForElementToBeVisibile(by);
		return driver.findElement(by).getText();
	}

	// Method to compare Two text --change return type
	public boolean compareText(By by, String expectedText) {
		waitForElementToBeVisibile(by);
		String actualText = driver.findElement(by).getText();
		if (expectedText.equals(actualText)) {
			System.out.println("Text are Matching: " + actualText + " equals " + expectedText);
			return true;
		}
		return false;
	}

	// wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable + " + e.getMessage());
		}
	}

	// Method to check if element is displayed
	public boolean isDisplayed(By by) {
		waitForElementToBeVisibile(by);
		return driver.findElement(by).isDisplayed();
	}

	// Wait for the pageLoad
	// Explicit wait
	public void waitForPageLoad(int timeOutInSec) {
		wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
				.executeScript("return document.readyStte").equals("complete"));
		System.out.println("Page load successfully");
	}

	// scroll to an element
	public void scrollToElement(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(by);
		js.executeScript("ariguments[0],scrollIntoView(true)", element);
	}

	// wait for element to be visible

	private void waitForElementToBeVisibile(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible + " + e.getMessage());
		}
	}

	// Method to get element description using By locator
	public String getElementDescription(By locator) {
		// Check for null driver or locator to avoid NullPointer Exception
		if (driver == null) {
			return "driver is null";
		}
		if (locator == null) {
			return "Locator is null";
		}

		try {
			// find the element using the locator
			WebElement element = driver.findElement(locator);

			// Get Element Description
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String text = element.getText();
			String className = element.getDomAttribute("class");
			String placeHolder = element.getDomAttribute("placeholder");

			// Return the description based on element Attributes
			if (isNotEmpty(name)) {
				return "Element with name:" + name;
			} else if (isNotEmpty(id)) {
				return "Element with id:" + id;
			} else if (isNotEmpty(text)) {
				return "Element with text:" + truncate(text, 50);
			} else if (isNotEmpty(className)) {
				return "Element with className:" + className;
			} else if (isNotEmpty(placeHolder)) {
				return "Element with placeHolder:" + placeHolder;
			}
		} catch (Exception e) {
			logger.error("Unable to describe element"+e.getMessage());
		}
		return "Unable to describe element";
	}

	// Utility method to check a String is not null or empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	// Utility method to check a String is not null or empty
	private String truncate(String value, int maxLength) {
		if (value == null || value.length() <= maxLength) {
			return value;
		}
		return value.substring(maxLength) + "...";
	}

}
