package core.helpers;

import java.security.SecureRandom;

import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;

public class UtilityHelper extends AppPage {

	public UtilityHelper(WebDriver driver) {
		super(driver);
	}

	public static String generateRandomString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

}