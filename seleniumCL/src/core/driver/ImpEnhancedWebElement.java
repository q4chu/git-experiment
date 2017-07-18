package core.driver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import core.appManager.AppPage;

public class ImpEnhancedWebElement implements EnhancedWebElement {

	private final String elementName;
	private final By by;
	private final WebDriver webDriver;
	private final WebElement parent;

	@Override
	public void clear() {
		clear(0);
	}

	@Override
	public void clear(int index) {
		int retry = 1;

		boolean success = false;
		do {
			retry--;
			try {
				if (isExist()) {
					scrollToView(index);
					WebElement element = getElement().get(index);
					element.clear();
					success = true;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		} while (!success && retry > 0);

	}

	public ImpEnhancedWebElement(String elementName, By by, WebDriver webDriver, WebElement parent) {
		this.elementName = elementName;
		this.by = by;
		this.webDriver = webDriver;
		this.parent = parent;
	}

	@Override
	public EnhancedWebElement findElement(By by, String elementName, EnhancedWebElement parentElement) {

		return new ImpEnhancedWebElement(elementName, by, webDriver, parentElement);

	}

	@Override
	public String getCssValue(String arg0) {
		return getCssValue(arg0, 0);
	}

	@Override
	public String getCssValue(String arg0, int index) {
		WebElement element = getElement().get(index);
		return element.getCssValue(arg0);
	}

	@Override
	public Point getLocation() {
		return getLocation(0);
	}

	@Override
	public Point getLocation(int index) {
		WebElement element = getElement().get(index);
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		return getSize(0);
	}

	public Dimension getSize(int index) {
		System.out.println("getSize: " + elementName);
		WebElement element = getElement().get(index);
		return element.getSize();
	}

	@Override
	public int count() {
		if (isExist()) {
			List<WebElement> elements = getElement();
			return elements.size();
		}
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return (count() == 0);
	}

	@Override
	public String getTagName() {
		return getTagName(0);
	}

	public String getTagName(int index) {
		WebElement element = getElement().get(index);
		return element.getTagName();
	}

	@Override
	public boolean isDisplayed() {
		return isDisplayed(0);
	}

	@Override
	public boolean isDisplayed(int index) {
		WebElement element = getElement().get(index);
		return element.isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return isEnabled(0);
	}

	@Override
	public boolean isEnabled(int index) {
		WebElement element = getElement().get(index);
		return element.isEnabled();
	}

	@Override
	public void submit() {
		submit(0);
	}

	public void submit(int index) {
		WebElement element = getElement().get(index);
		element.submit();

	}

	@Override
	public void click() {	
		click(0);
	}

	@Override
	public void click(int index) {
		int retry = 10;

		boolean success = false;
		do {
			retry--;
			try {
				if(isExist()){
				//	scrollToView(index);
					WebElement toClick = getElement().get(index);
					toClick.click();
					success = true;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		} while (!success && retry > 0);
	}

	@Override
	public void scrollToView() {
		scrollToView(0);
	}

	@Override
	public void scrollToView(int index) {

		WebElement element = getElement().get(index);

		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				+ "var elementTop = arguments[0].getBoundingClientRect().top;"
				+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript(scrollElementIntoMiddle, element);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public boolean isExist() {
		return isExist(0);
	}

	@Override
	/**
	 * returns true if element is displayed sets timeout to minimum to get the
	 * value quickly
	 */
	public boolean isExist(int index) {
		webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

		boolean isElementExists = false;
		try {
			WebElement element = getElement().get(index);
			if (element.isDisplayed()){
				isElementExists = true;
			}
		} catch (Exception e) {
			isElementExists = false;
		}

		webDriver.manage().timeouts().implicitlyWait(AppPage.TIMEOUT_SECONDS, TimeUnit.SECONDS);
		return isElementExists;
	}

	@Override
	public void moveMouse() {
		moveMouse(0);
	}

	@Override
	public void moveMouse(int index) {
		WebElement element = getElement().get(index);
		element.getLocation();
	}

	public By getBy() {
		return by;
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		sendKeys(0, keysToSend);
	}

	@Override
	public void sendKeys(int index, CharSequence... keysToSend) {
		int retry = 1;

		boolean success = false;
		do {
			retry--;
			try {
				if (isExist()) {
					scrollToView(index);
					WebElement element = getElement().get(index);
					element.sendKeys(keysToSend);
					success = true;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		} while (!success && retry > 0);
	}

	@Override
	public String getAttribute(String name) {
		return getAttribute(name, 0);
	}

	@Override
	public String getAttribute(String name, int index) {
		WebElement element = getElement().get(index);

		return element.getAttribute(name);
	}
	
	public void setAttribute(String attribute, String value) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("document.getElementsBySelector('"+ getBy() +"').setAttribute('"+attribute+"', '"+value+"')");
	}
	
	@Override
	public WebElement get(int index) {
		return getElement().get(index);
	}

	@Override
	public String getText() {
		return getText(0);
	}

	@Override
	public String getText(int index) {
		WebElement element = getElement().get(index);
		return element.getText();
	}

	@Override
	public String getElementName() {
		return elementName;
	}

	@Override
	public List<WebElement> getElement() {
		int retry = 5;
		boolean success = false;
		List<WebElement> webElements = null;
		do {
			retry--;
			try {
				if (parent != null) {
					webElements = parent.findElements(by);
				} else {
					webElements = webDriver.findElements(by);
				}
				success = true;

			} catch (Exception e) {
				e.getMessage();
			}
		} while (!success && retry > 0);

		return webElements;
	}

	@Override
	public boolean isSelected() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public By getElementCssSelector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public By getElementCssSelector(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(int index, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementName(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
