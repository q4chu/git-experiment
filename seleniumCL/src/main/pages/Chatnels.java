package main.pages;


import java.io.IOException;

import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.PropertiesReader;

public class Chatnels extends AppPage {

	public static String chatnels = getUrl();

	public Chatnels(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * set url through maven using -D command
	 * eg. -DcraigslistSite = "www.test.com"
	 * @return
	 */
	public static String getUrl() {
	   if (!getUrlFromMaven().isEmpty()) { return getUrlFromMaven(); }
	   else if (!getUrlFromProperties().isEmpty()) { return getUrlFromProperties(); }
	   else return "https://qaw.chatnels.com/ct"; 
	}
	
	public static String getUrlFromMaven() {
		String value = System.getProperty("craigslistSite", "");
		return value;
	}
	
	public static String getUrlFromProperties() {
		try {
			return PropertiesReader.Property().getProperty("craigslistSite", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return "";
	}
}


