package com.orangehrm.test;

import org.testng.annotations.Test;
import com.orangehrm.base.BaseClass;

public class DummyClass extends BaseClass {

	@Test
	public void dummyTest() {
		String title = driver.getTitle();

		assert title.equalsIgnoreCase("OrangeHRM") : "Test Failed -  Title not Matching";

		System.out.println("Test Passed");
	}
}
