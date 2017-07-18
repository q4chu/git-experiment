package core.driver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebDriverSetup {

	public enum WebDriverType {
		INTERNET_EXPLORER, REMOTE_WEBDRIVER, IOS_DRIVER, ANDROID_DRIVER, CHROME_MAC, CHROME_WIN, CHROME_LINUX, PHANTOMJS_MAC, PHANTOMJS_WIN, FIREFOX_MAC, FIREFOX_WIN;
	}

	/**
	 * get webdriver by type set in properties file
	 * 
	 * @return
	 * @throws IOException
	 */

	public WebDriver getWebDriverByType() throws IOException {
		WebDriver driver = null;
		WebDriverType type = Enum.valueOf(WebDriverType.class, PropertiesReader.getDriverType());

		switch (type) {
		case FIREFOX_MAC:
			System.setProperty("webdriver.gecko.driver", "../webDriver/osx/node/drivers/geckodriver");
			driver = new FirefoxDriver();
			break;
		case FIREFOX_WIN:
			System.setProperty("webdriver.gecko.driver", "../webDriver/win/node/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case INTERNET_EXPLORER:
			driver = new InternetExplorerDriver();
			break;
		case CHROME_MAC:
			System.setProperty("webdriver.chrome.driver", "../webDriver/osx/node/drivers/chromedriver");
			driver = new ChromeDriver(getCapability());
			break;
		case CHROME_WIN:
			System.setProperty("webdriver.chrome.driver", "../webDriver/win/node/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case CHROME_LINUX:
			System.setProperty("webdriver.chrome.driver", "../webDriver/linux/node/drivers/chromedriver");
			driver = new ChromeDriver();
			break;
		case PHANTOMJS_MAC:
			System.setProperty("phantomjs.binary.path", "../webDriver/osx/node/drivers/phantomjs/bin/phantomjs");
			driver = new PhantomJSDriver();
			break;
		case PHANTOMJS_WIN:
			System.setProperty("phantomjs.binary.path", "../webDriver/win/node/drivers/phantomjs/bin/phantomjs");
			driver = new PhantomJSDriver();
			break;
		case REMOTE_WEBDRIVER:
			driver = new RemoteWebDriver(new URL(PropertiesReader.getGridUrl() + ":" + PropertiesReader.getGridPort() + "/wd/hub"), getCapability());
			break;
		case IOS_DRIVER:
			driver = new IOSDriver(new URL(PropertiesReader.getGridUrl() + ":" + PropertiesReader.getGridPort() + "/wd/hub"), getIosCapability());
			break;
		case ANDROID_DRIVER:
			driver = new AndroidDriver(new URL(PropertiesReader.getGridUrl() + ":" + PropertiesReader.getGridPort() + "/wd/hub"), getAndroidCapability());
			break;
		default:
			throw new IllegalStateException("Unsupported browsertype " + PropertiesReader.getDriverType());
		}

		return driver;
	}

	/**
	 * sets capability for web based apps
	 * 
	 * @return
	 * @throws IOException
	 */
	public DesiredCapabilities getCapability() throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// set phantomjs capabilities
		ArrayList<String> cliArgsCap = new ArrayList<String>();
		cliArgsCap.add("--web-security=false");
		cliArgsCap.add("--ssl-protocol=any");
		cliArgsCap.add("--ignore-ssl-errors=true");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
		capabilities.setCapability("recordVideo", false);
		capabilities.setCapability("takesScreenshot", true);

		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.SEVERE);

		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		capabilities.setBrowserName(PropertiesReader.getBrowser());
		return capabilities;
	}

	/**
	 * sets capability of ios based apps
	 * 
	 * @return
	 */
	public DesiredCapabilities getIosCapability() {
		// https://github.com/appium/appium
		// user appium desktop app for locator

		DesiredCapabilities capabilities = new DesiredCapabilities();
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "../");
		File app = new File(appDir, "HeadCheck.app");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		//capabilities.setCapability(MobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		//capabilities.setCapability("orientation", ScreenOrientation.LANDSCAPE);
		//capabilities.setCapability("bundleId", "com.conquermobile.HeadCheck");
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("noReset", true);

		return capabilities;
	}

	/**
	 * sets capability of android based apps run android simulator
	 * 
	 * @return
	 */
	public DesiredCapabilities getAndroidCapability() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "../");
		File app = new File(appDir, "HeadCheck.apk");

		capabilities.setCapability("device", "Android");

		// mandatory capabilities
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("session-override", true);

		// other caps
		capabilities.setCapability("app", app.getAbsolutePath());
		return capabilities;
	}
}
