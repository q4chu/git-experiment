package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ProfilePanel extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Profile_Option_List = "[id*='profile-holder'] .list-button"; //include Edit Display Name; Change Password; Notification Settings
	//Report a Problem; Email Notifications; Browser Notifications
	private static final String Profile_Panel_Proof = "[id*='profile-holder']";
	private static final String Logout_Button = "[id*='profile'] .red-btn";
	private static final String UploadButton = "[id*='profile'] [name='thin-upload']";
	
	public ProfilePanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byLogoutButton(){
		return BySelector(By.cssSelector(Logout_Button), "Logout button");
	}
	public EnhancedBy byUploadButton(){
		return BySelector(By.cssSelector(UploadButton), "upload button");
	}
	public EnhancedBy byOptionList(){
		return BySelector(By.cssSelector(Profile_Option_List), "profile option list");
	}
	public EnhancedBy byProfilePanelProof(){
		return BySelector(By.cssSelector(Profile_Panel_Proof), "profile panel proof");
	}
	
}
