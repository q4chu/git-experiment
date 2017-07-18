package core.helpers;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;

public class AssertHelper extends AppPage {

	public AssertHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * assert true
	 * 
	 * @param message
	 * @param value
	 */
	public static void assertTrue(String message, boolean value) {
		Assert.assertTrue(message, value);
	}
}