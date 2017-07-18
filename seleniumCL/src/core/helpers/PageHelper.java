package core.helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;

public class PageHelper extends AppPage {

	public PageHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * maximizes web page
	 */
	public static void maximizePage() {
		getWebDriver().manage().window().maximize();
	}

	/**
	 * sets page size
	 * 
	 * @param x
	 * @param y
	 */
	public static void setPageSize(int x, int y) {
		Dimension dimension = new Dimension(x, y);
		getWebDriver().manage().window().setSize(dimension);
	}

	/**
	 * reload page
	 */
	public static void refreshPage() {
		getWebDriver().navigate().refresh();
	}

	/**
	 * switches frame to frame specified
	 * 
	 * @param frame
	 */
	public static void switchIframe(EnhancedBy frame) {
		EnhancedWebElement frameElement = findElement(frame);
		getWebDriver().switchTo().defaultContent(); // you are now outside both
													// frames
		getWebDriver().switchTo().frame(frameElement);
	}
	
	/**
	 * switches frame to frame specified
	 * 
	 * @param frame
	 */
	public static void switchIframe(String frame) {
		getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		getWebDriver().switchTo().frame(frame);
	}

	/**
	 * switches to default frame
	 */
	public static void switchToDefaultFrame() {
		getWebDriver().switchTo().defaultContent();
	}
	
	/**
	 * dismisses alert by selecting ok or cancel
	 * 
	 * @param alert
	 */
	public static void dimissAlert() {
		try {
			Alert alert = getWebDriver().switchTo().alert();
			// update is executed
			alert.accept();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * return the current window handle
	 * @return
	 */
	public static String currentWindow() {
		return getWebDriver().getWindowHandle();
	}
	
	/**
	 * switch to the new opened window
	 * @param defaultWindow
	 */
	public static void switchToNewWindow(String defaultWindow){
		for(String winHandle : getWebDriver().getWindowHandles()){
			if(!winHandle.equals(defaultWindow))
				getWebDriver().switchTo().window(winHandle);
		}
	}
	
	/**
	 * close the window and return to the defaultWindow
	 * @param defaultWindow
	 */
	public static void CloseAndReturn(String defaultWindow){
		getWebDriver().close();
		getWebDriver().switchTo().window(defaultWindow);
	}

}