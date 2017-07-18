package core.helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;

public class WaitHelper extends AppPage {

	public WaitHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * waits for element to be displayed for amount of time specified by 60
	 * seconds
	 * 
	 * @param target
	 */
	public static void waitForElementToLoad(final EnhancedBy target) {

		waitForElementToLoad(target, TIMEOUT_SECONDS);
	}

	/**
	 * waits for element to laod
	 * 
	 * @param target
	 * @param time
	 */
	public static boolean waitForElementToLoad(final EnhancedBy target, int time) {
		Wait<WebDriver> wait = new FluentWait<>(getWebDriver()).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
	   		wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return findElements(target).count() > 0;
				}
			});
		} catch (Exception e) {
			e.getMessage();
			return false;

		}
		return true;
	}

	/**
	 * waits for element count to increase from the originalCount Usefull when
	 * waiting for a list to expand with additional items
	 * 
	 * @param target
	 * @param originalCount
	 */
	public static void waitForAdditionalElementsToLoad(final EnhancedBy target, final int originalCount) {

		Wait<WebDriver> wait = new FluentWait<>(getWebDriver()).withTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ListHelper.getListCount(target) > originalCount;
			}
		});
	}

	/**
	 * waits for element to not be displayed wait for maximum of 60 seconds
	 * 
	 * @param target
	 */
	public static void waitForElementToBeRemoved(final EnhancedBy target) {
		waitForElementToBeRemoved(target, TIMEOUT_SECONDS);
	}

	/**
	 * waits for element to not be displayed
	 * 
	 * @param target
	 * @param time
	 *            : maximum amount of time in seconds to wait
	 */
	public static boolean waitForElementToBeRemoved(final EnhancedBy target, int time) {

		Wait<WebDriver> wait = new FluentWait<>(getWebDriver()).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
		wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				EnhancedWebElement elements = findElements(target);
				try {
					for (int x = 0; x < elements.count(); x++) {
						if (elements.isExist(x)) {
							return false;
						}
					}
				} catch (Exception e) {
					e.getMessage();
				}
				return true;
			}
			});
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;
	}

	/**
	 * waits for number of seconds
	 * 
	 * @param seconds
	 */
	public static void waitForSeconds(double seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.getMessage();
		}
		// TestLogger.logPass("Then I wait for '" + seconds + "' seconds");
	}

	/**
	 * waits for webpage to load
	 */
	public static void waitForPageToLoad() {
		Wait<WebDriver> wait = new FluentWait<>(getWebDriver()).withTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}
	
	/**
	 * waits for text to be loaded for amount of time specified by 60
	 * seconds
	 * 
	 * @param target
	 */
	public static void waitForTextToLoad(final WebElement target) {

		waitForTextToLoad(target, TIMEOUT_SECONDS);
	}

	/**
	 * make sure only one element and caller needs to take responsibility to have text in the element
	 * @param target
	 * @param time
	 */
	public static void waitForTextToLoad(final WebElement target, int time) {

		Wait<WebDriver> wait = new FluentWait<>(getWebDriver()).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		 try {
		wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return target.getText().length() != 0;
			}
		});
		 } catch( Exception e ) {
	            e.getMessage();
	  
	     }
	}

}