package core.helpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;
import core.driver.TestLog;

public class Helper extends AppPage {

	public Helper(WebDriver driver) {
		super(driver);
	}

	/**
	 * clicks target and waits for expected element to display retries 10 times
	 * 
	 * @param target
	 * @param expected
	 */
	public static void clickAndExpect(EnhancedBy target, EnhancedBy expected) {
		WaitHelper.waitForElementToLoad(target);
		EnhancedWebElement targetElement = findElement(target);
		boolean isExpectedFound = false;
		int retry = 5;

		do {
			retry--;
			targetElement.click();
			isExpectedFound = WaitHelper.waitForElementToLoad(expected, 3);
		} while (!isExpectedFound && retry > 0);

		AssertHelper.assertTrue("expected element not found: " + expected.name, isExpectedFound);
		TestLog.logPass("I click " + target.name);
	}
	
	public static void clickAndExpect(EnhancedBy target, EnhancedBy expected, EnhancedBy spinner) {
		WaitHelper.waitForElementToLoad(target);
		EnhancedWebElement targetElement = findElement(target);
		boolean isExpectedFound = false;
		int retry = 5;

		do {
			retry--;
			targetElement.click();
			WaitHelper.waitForElementToBeRemoved(spinner);
			isExpectedFound = WaitHelper.waitForElementToLoad(expected, 3);
		} while (!isExpectedFound && retry > 0);

		AssertHelper.assertTrue("expected element not found: " + expected.name, isExpectedFound);
		TestLog.logPass("I click " + target.name);
	}

	/**
	 * clicks target and waits for expected to not be displayed retries 10 times
	 * 
	 * @param target
	 * @param expected
	 */
	public static void clickAndNotExpect(EnhancedBy target, EnhancedBy expected) {
		WaitHelper.waitForElementToLoad(target);
		EnhancedWebElement targetElement = findElement(target);
		EnhancedWebElement expectedElement = null;

		int retry = 10;

		do {
			retry--;
			targetElement.click();
			WaitHelper.waitForElementToBeRemoved(expected, 5);
			expectedElement = findElements(expected);
		} while (expectedElement.isExist() && retry > 0);

		AssertHelper.assertTrue("expected element found", !expectedElement.isExist());
		// TestLogger.logPass("clickAndNotExpect: " + target.name);
	}

	/**
	 * verifies if element(s) is (are) displayed
	 * 
	 * @param by
	 */
	public static void verifyElementIsDisplayed(EnhancedBy by) {
		WaitHelper.waitForElementToLoad(by);
		EnhancedWebElement elements = findElements(by);
		TestLog.logPass("I verify '" + by.name + "' " + "is displayed");
		AssertHelper.assertTrue("element '" + by.name + "' is not displayed", elements.count() > 0);
	}

	/**
	 * returns true if element is displayed
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isPresent(EnhancedBy element) {
		EnhancedWebElement expectedElement = findElement(element);
		return expectedElement.isExist();
	}

	/**
	 * verifies if element(s) is (are) not displayed
	 * 
	 * @param by
	 */
	public static void verifyElementIsNotDisplayed(EnhancedBy by) {
		EnhancedWebElement elements = findElements(by);
		TestLog.logPass("I verify element '" + by.name + "' " + "is not displayed");
		AssertHelper.assertTrue("element '" + by.name + "' is displayed", !elements.isExist());
	}
	
	/**
	 * extract information(text,number) from webelement
	 * @param column
	 * @return list of strings as the information
	 */
	public static List<String> ExtractInfo(EnhancedBy column){
		WaitHelper.waitForElementToLoad(column);
		EnhancedWebElement list = findElements(column);
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < list.count(); ++i) {
			result.add(list.get(i).getText());
		}
		return result;
	}

}