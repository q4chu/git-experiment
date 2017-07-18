package core.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public interface EnhancedWebElement extends WebElement {

	void click();

	void click(int index);

	boolean isExist();

	boolean isExist(int index);

	void moveMouse();

	void moveMouse(int index);

	EnhancedWebElement findElement(By by, String elementName, EnhancedWebElement parentElement);

	By getElementCssSelector();

	By getElementCssSelector(int index);

	void sendKeys(CharSequence... keysToSend);

	void sendKeys(int index, CharSequence... keysToSend);

	void scrollToView();

	void scrollToView(int index);

	String getAttribute(String name);

	String getAttribute(int index, String name);

	String getText();

	String getText(int index);

	String getElementName();

	String getElementName(int index);

	int count();

	boolean isEmpty();

	List<WebElement> getElement();

	By getBy();

	Point getLocation(int index);

	String getCssValue(String arg0, int index);

	void clear(int index);

	boolean isEnabled(int index);

	boolean isDisplayed(int index);

	String getAttribute(String name, int index);
	
	void setAttribute(String attribute, String value);

	WebElement get(int index);
}
