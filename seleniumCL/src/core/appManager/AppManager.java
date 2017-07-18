package core.appManager;

import org.openqa.selenium.WebDriver;

import main.Panels.ChatnelsManager;




/**
 * Manages all existing apps Abstract driver refers to this manager to call
 * methods in different apps
 */
public class AppManager extends AppPage {

	public AppManager(WebDriver driver) {
		super(driver);

	}

	public ChatnelsManager chatnels = new ChatnelsManager(getWebDriver());
}