package core.helpers;

import java.io.File;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;
import core.driver.TestLog;

public class FormHelper extends AppPage {

	public FormHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * set field value if value is not empty
	 * 
	 * @param value
	 * @param field
	 */
	public static void setField(String value, EnhancedBy field) {
		EnhancedWebElement fieldElement = findElement(field);
		if (value != null && !value.isEmpty()) {
			// clear field is slow on android and ios
			fieldElement.clear();
			fieldElement.sendKeys(value);

			TestLog.logPass("I set field '" + field.name + "' with value '" + value + "'");
		}
	}
	
	public static void setFieldAndEnter(String value, EnhancedBy field) {
		setField(value, field);
		EnhancedWebElement targetElement = findElement(field);
		targetElement.sendKeys(Keys.ENTER);
	}

	/**
	 * select submit button and wait for expected element to load
	 * 
	 * @param button
	 * @param expected
	 * 
	 */
	public static void formSubmit(EnhancedBy button, EnhancedBy expected) {
		Helper.clickAndExpect(button, expected);
		// TestLogger.logPass("Then I submit form");

	}

	/**
	 * clicks submit button, wait for element to appear and loading spinner to
	 * be removed
	 * 
	 * @param button
	 * @param expected
	 * @param spinner
	 */
	public static void formSubmit(EnhancedBy button, EnhancedBy expected, EnhancedBy spinner) {
		Helper.clickAndExpect(button, expected);
		WaitHelper.waitForElementToBeRemoved(spinner);
	}

	/**
	 * selects drop down
	 * 
	 * @param option
	 *            : list option we want to select
	 * @param field
	 *            : the drop down field
	 * @param list
	 *            : the list items in the drop down list
	 */
	public static void selectDropDown(String option, EnhancedBy field, EnhancedBy list) {
		if (option != null && !option.isEmpty()) {
			Helper.clickAndExpect(field, list);
			ListHelper.selectListItemEqualsByName(list, option);
			TestLog.logPass("I select drop down option '" + option + "'");
		}
	}
	
	public static void selectDropDown(String option, EnhancedBy field, String field_Identifier, EnhancedBy list) {
		if (option != null && !option.isEmpty()) {
			ListHelper.selectListItemContainsByName(field, field_Identifier);
			ListHelper.selectListItemEqualsByName(list, option);
			TestLog.logPass("I select drop down option '" + option + "'");
		}
	}

	/**
	 * selects radio button by radio button description
	 * 
	 * @param option
	 * @param buttons
	 */
	public static void selectRadioButton(String option, EnhancedBy buttons) {
		if (option != null && !option.isEmpty()) {
			ListHelper.selectListItemEqualsByName(buttons, option);
		}
	}

	/**
	 * selects multiple checkbox options
	 * 
	 * @param selections
	 * @param checkboxes
	 */
	public static void selectMultipleCheckboxOptions(List<String> selections, EnhancedBy checkboxes) {
		for (String selection : selections) {
			TestLog.logPass("I select '" + selection + "'");
			ListHelper.selectListItemEqualsByName(checkboxes, selection);
		}
	}

	/**
	 * uploads file by specifying file location relative to main path
	 * 
	 * @param location
	 * @param imageButton
	 */
	public static void uploadFile(String location, EnhancedBy imageButton) {
		File file = new File("");
		String path = file.getAbsolutePath() + location;
		setField(path, imageButton);
		TestLog.logPass("I upload file at location '" + location + "'");
	}

	public void uploadImages(List<String> locations, EnhancedBy imageButton, EnhancedBy images) {
		for (String location : locations) {
			uploadImage(location, imageButton, images);
		}
	}

	/**
	 * sets the image based on location
	 * 
	 * @param location
	 * @param imageButton
	 * @param images
	 *            : uploaded image
	 */
	public static void uploadImage(String location, EnhancedBy imageButton, EnhancedBy images) {
		int imageCount = ListHelper.getListCount(images);
		File file = new File("");
		String path = file.getAbsolutePath() + location;
		setField(path, imageButton);
		WaitHelper.waitForAdditionalElementsToLoad(images, imageCount);
		TestLog.logPass("uploaded file: " + location);
	}
}