package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class HomePage {
	private ActionDriver actionDriver;
	
	private By adminTab=By.xpath("//span[text()='Admin']");
	private By userIDBtn=By.className("oxd-userdropdown-name");
	private By logoutBtn=By.xpath("//a[text()='Logout']");
	private By orangeHRMlogo=By.xpath("//div[@class='oxd-brand-banner']/img");

	//Initilize the ActionDriver object by passing WebDriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver= new ActionDriver(driver);
	}

	//Method to verify if Admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}
	
	//Method to verify if orangeHRMlogo is visible
	public boolean verifyOranegHRMlogo() {
		return actionDriver.isDisplayed(orangeHRMlogo);
	}
	
	//Method to Perform Logout
	public void logout() {
		actionDriver.click(userIDBtn);
		actionDriver.click(logoutBtn);
	}
}
