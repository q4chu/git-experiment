package core.helpers;

import java.util.function.BiPredicate;

import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;
import core.driver.TestLog;

public class ListHelper extends AppPage {

	public ListHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * selects an element in list by its index value
	 * 
	 * @param list
	 * @param index
	 */
	public static void selectElementInList(EnhancedBy list, int index) {
		EnhancedWebElement expectedElement = findElements(list);
		expectedElement.click(index);
	}

	/**
	 * selects list item by the string option provided
	 * 
	 * @param list
	 * @param option
	 */
	public static void selectListItemEqualsByName(EnhancedBy list, String option) {
		int index = getElementIndexEqualsByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);
		selectElementInList(list, index);
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}

	/**
	 * finds target element which is in the same container and has the same
	 * index as the parent eg. delete button in the list of customers, both
	 * having index 2. we find the index by name, and use that to find the
	 * target element
	 * 
	 * @param list
	 * @param option
	 * @param target
	 */
	public static void selectListItemEqualsByName(EnhancedBy list, String option, EnhancedBy target) {
		int index = getElementIndexEqualsByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);

		selectElementInList(target, index);
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}

	/**
	 * selects list item containing string eg. a list of athletes names
	 * containing a delete button
	 * 
	 * @param list
	 * @param option
	 * @param target
	 */
	public static void selectListItemContainsByName(EnhancedBy list, String option, EnhancedBy target) {
		int index = getElementIndexContainByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);

		selectElementInList(target, index);
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}

	/**
	 * Selects list item from a parent container eg. delete button in a list
	 * defined by name find the container containing the name and then finds the
	 * delete button in that container as target
	 * 
	 * @param list
	 * @param option
	 * @param target
	 */
	public static void selectListItemContainsFromContainer(EnhancedBy list, String option, EnhancedBy target) {
		int index = getElementIndexContainByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);

		EnhancedWebElement containerList = findElements(list);
		EnhancedWebElement targetElement = findElement(target, containerList.get(index));

		targetElement.click();
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}
	/**
	 * finds target element which is in the same container and has the same
	 * index as the parent eg. delete button in the list of customers, both
	 * having index 2. we find the index containing name, and use that to find
	 * the target element
	 * 
	 * @param list
	 * @param option
	 */
	public static void selectListItemContainsByName(EnhancedBy list, String option) {
		WaitHelper.waitForElementToLoad(list);
		int index = getElementIndexContainByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);
		selectElementInList(list, index);
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}

	/**
	 * selects list item by the string option provided
	 * 
	 * @param list
	 * @param option
	 */
	public static void selectListItemByIndex(EnhancedBy list, String option) {
		int index = getElementIndexEqualsByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);
		selectElementInList(list, index);
		TestLog.logPass("I select list option '" + option + "' from list '" + list.name + "'");
	}

	/**
	 * returns the number of elements in list
	 * 
	 * @param list
	 * @return
	 */
	public static int getListCount(EnhancedBy list) {
		EnhancedWebElement listElements = findElements(list);
		return listElements.count();
	}

	/**
	 * returns the index of text value in a list
	 * 
	 * @param list
	 * @param option
	 * @return
	 */
	public static int getElementIndexEqualsByText(EnhancedBy list, String option) {
		int optionIndex = -1;
		WaitHelper.waitForElementToLoad(list);
		EnhancedWebElement listElement = findElements(list);

		for (int i = 0; i < listElement.count(); i++) {
			WaitHelper.waitForTextToLoad(listElement.get(i));
			if (listElement.getText(i).equalsIgnoreCase(option)) {
				optionIndex = i;
				break;
			}
		}
		return optionIndex;
	}

	/**
	 * retuns index of element in list which contains in text
	 * 
	 * @param list
	 * @param option
	 * @return
	 */
	public static int getElementIndexContainByText(EnhancedBy list, String option) {
		int optionIndex = -1;
        WaitHelper.waitForElementToLoad(list);
		EnhancedWebElement listElement = findElements(list);

		for (int i = 0; i < listElement.count(); i++) {
			WaitHelper.waitForTextToLoad(listElement.get(i));
			if (listElement.getText(i).contains(option)) {
				optionIndex = i;
				break;
			}
		}
		return optionIndex;
	}

	/**
	 * verifies if option value is in the list index = -1 indicates the value is
	 * not in list
	 * 
	 * @param list
	 * @param option
	 */
	public static void verifyIsInList(EnhancedBy list, String option) {
		int index = getElementIndexEqualsByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index > -1);
	}
	
	public static void verifyIsNotInList(EnhancedBy list, String option) {
		int index = getElementIndexEqualsByText(list, option);
		AssertHelper.assertTrue("option not found in list: " + list.name, index == -1);
	}
	
	public static boolean isInList(EnhancedBy list, String option, BiPredicate<String, String> comp)
	{
		int index = getElementIndexContainByText(list, option);
		return index != -1;
	}

}