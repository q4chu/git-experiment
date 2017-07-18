package core.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;
import core.driver.ImpEnhancedWebElement;

/**
 * app page is parent class of different apps
 * 
 * @author ehsan matean
 *
 */
public class AppPage {

	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();;

	/**
	 * global timeout in seconds
	 */
	public static final int TIMEOUT_SECONDS = 60;

	public AppPage(WebDriver driver) {
		setWebdriver(driver);
	}

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}

	private void setWebdriver(WebDriver webDriver) {
		AppPage.webDriver.set(webDriver);
	}

	/**
	 * finds an element based on by value
	 * 
	 * @param element
	 * @return
	 */
	public static EnhancedWebElement findElement(EnhancedBy element) {

		return new ImpEnhancedWebElement(element.name, element.by, getWebDriver(), null);
	}

	/**
	 * finds element based on parent element
	 * 
	 * @param element
	 * @param parent
	 * @return
	 */
	public static EnhancedWebElement findElement(EnhancedBy child, WebElement parent) {

		return new ImpEnhancedWebElement(child.name, child.by, getWebDriver(), parent);
	}

	/**
	 * finds list of elements
	 * 
	 * @param element
	 * @return
	 */
	public static EnhancedWebElement findElements(EnhancedBy element) {

		return new ImpEnhancedWebElement(element.name, element.by, getWebDriver(), null);
	}

	/**
	 * finds a list of elements based on parent element
	 * 
	 * @param element
	 * @param parent
	 * @return
	 */
	public static EnhancedWebElement findElements(EnhancedBy element, EnhancedWebElement parent) {

		return new ImpEnhancedWebElement(element.name, element.by, getWebDriver(), parent);
	}

	/**
	 * sets the by value with by selector and name of the element
	 * 
	 * @param by
	 * @param name
	 * @return
	 */
	public static EnhancedBy BySelector(By by, String name) {

		return new EnhancedBy(by, name);
	}
}
