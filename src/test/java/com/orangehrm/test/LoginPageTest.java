package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;

	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}

	@Test
	public void verifyLoginTest() {
		loginPage.login("admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(), "Admin should be viisble after login");
		homePage.logout();
	}

	@Test
	public void invaidLoginTest() {
		loginPage.login("admin", "admin");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage), "Test Failed:Invalid error message");
	}

}
