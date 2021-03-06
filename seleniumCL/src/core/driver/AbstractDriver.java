package core.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Scenario;

import core.appManager.AppManager;
import core.appManager.AppPage;
import core.helpers.PageHelper;
import core.helpers.WaitHelper;
import core.rules.RetryTest;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class AbstractDriver {

	protected WebDriver driver;

	public static ExtentReports extent;
	public ExtentTest feature;
	public ExtentTest scenerio;

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> step = new ThreadLocal<ExtentTest>();
	private static boolean isBeforeTestRun = true;

	protected static Map<String, ExtentTest> testList = new HashMap<String, ExtentTest>();

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<Logger> log = new ThreadLocal<Logger>();
	public AppManager app;
	public WebDriverSetup webDriverSetup = new WebDriverSetup();

	@Rule
	public RetryTest retry = new RetryTest(RetryTest.RETRYCOUNTER);

	@Rule
	public Timeout globalTimeout = new Timeout(20 * 60 * 1000);

	@Rule
	public TestName testName = new TestName();

	public AbstractDriver() {
	}

	public void setupWebDriver() throws Exception {
		setupWebDriver("");
	}

	public void setupWebDriver(String startUrl) throws Exception {
		log.set(Logger.getLogger(getClass() + "-" + testName.getMethodName()));
		reportSetup();
		setWebdriver(createDriver());

		getURL(startUrl);
		app = new AppManager(driver);
		WaitHelper.waitForPageToLoad();
		PageHelper.maximizePage();

	}

	private void setWebdriver(WebDriver webDriver) {
		AbstractDriver.webDriver.set(webDriver);
	}

	public void beforeSuite() {

	}

	@BeforeClass
	public static void beforeClass() {

	}

	public void setupReportPage() {
		// will run only once per test run
		// initializes the test report html page
		if (isBeforeTestRun) {
			extent = ExtentManager.createInstance("extent.html");
			isBeforeTestRun = false;
		}
	}


	public void reportSetup() {
		synchronized (AbstractDriver.class) {
			// will run only once per test run
			// initializes the test report html page
			setupReportPage();

			// will create parent once per class
			// initializes the test instance
			String className = getClassName();
			if (!testList.containsKey(className)) {
				String testParent = className.substring(className.lastIndexOf('.') + 1).trim();
				testParent = parseTestName(testParent);
				feature = extent.createTest(testParent);
				testList.put(className, feature);
			}

			// will run once every test
			// initializes test report
			if (retry.getCurrentTestRun() == 1) {
				String testChild = testName.getMethodName();
				testChild = parseTestName(testChild);
				scenerio = testList.get(className).createNode(Scenario.class,  testChild);

			}
			test.set(scenerio);
		}
		TestLog.Given("Test setup has been successful");
	}
	
	public String parseTestName(String value) {
		String formatted = "";
		value = value.replace("_", " ");
		
	    for (String w : value.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
	    	formatted = formatted + " " + w.toLowerCase();
	    }
	    return formatted;
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public WebDriver createDriver() throws Exception {
		int retry = 3;
		do {
			try {
				retry--;

				driver = webDriverSetup.getWebDriverByType();

				driver.manage().timeouts().implicitlyWait(AppPage.TIMEOUT_SECONDS, TimeUnit.SECONDS);
				// setting timeout causes slow down on mobile
				driver.manage().timeouts().pageLoadTimeout(AppPage.TIMEOUT_SECONDS, TimeUnit.SECONDS);
				//driver.manage().timeouts().setScriptTimeout(AppPage.TIMEOUT_SECONDS, TimeUnit.SECONDS);

			} catch (Exception e) {
				// System.out.println("Driver recreated" +
				// this.getClass().getName());
				// System.out.println("Error: " + e.getMessage());
				WaitHelper.waitForSeconds(3);
				if (retry == 0) {
					throw e;
				}

			}

		} while (driver == null && retry > 0);

		Assert.assertTrue("driver was not created", driver != null);
		// TestLogger.logPass("driver created successfully");

		return driver;
	}

	public void getURL(String url) {
		if (!url.isEmpty()) {
			TestLog.And("I am the site '" + url + "'");
			getWebDriver().get(url);
		}
	}

	protected static WebDriver getWebDriver() {
		return webDriver.get();
	}
	

	@After
	public void shutdown() {
		letRetryKnowAboutReports();
	}
	
	private void letRetryKnowAboutReports() {
		retry.setExtendReport(step.get(), extent);
		retry.setLogger(log.get());
		retry.setWebDriver(getWebDriver());
	}
}