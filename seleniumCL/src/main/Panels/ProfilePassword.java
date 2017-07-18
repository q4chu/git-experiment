package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ProfilePassword extends Chatnels {

	ChatnelsManager manager;
	
	private static final String CurrentPassword = "[id*='password'] [placeholder='Current password']"; 
	private static final String NewPassword = "[id*='password'] [placeholder='New password']";
	private static final String ReNewPassword = "[id*='password'] [placeholder='Re-type new password']";
	//Report a Problem; Email Notifications; Browser Notifications
	private static final String Profile_Password_Proof = "[id*='password']";
	private static final String SubmitButton = "[id*='password'] .green-btn";
	private static final String CancelButton = "[id*='password'] .dark-grey-btn";
	
	public ProfilePassword(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byCurrentPassword(){
		return BySelector(By.cssSelector(CurrentPassword), "current password");
	}
	public EnhancedBy byNewPassword(){
		return BySelector(By.cssSelector(NewPassword), "new password");
	}
	public EnhancedBy byReNewPassword(){
		return BySelector(By.cssSelector(ReNewPassword), "retype new password");
	}
	public EnhancedBy byProfilePasswordProof(){
		return BySelector(By.cssSelector(Profile_Password_Proof), "profile password proof");
	}
	public EnhancedBy bySubmitButton(){
		return BySelector(By.cssSelector(SubmitButton), "submit button");
	}
	public EnhancedBy byCancelButton(){
		return BySelector(By.cssSelector(CancelButton), "cancel button");
	}
}