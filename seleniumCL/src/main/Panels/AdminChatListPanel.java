package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;
public class AdminChatListPanel extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Back_Button = "[id*='admin-chatslist'] .icon-thumbnails"; 
	private static final String Admin_Option_List = "[id*='admin-chatslist'] .list-button"; //include title, settings, wall, chat lines, Front Desk
	private static final String Admin_Chatlist_Proof = "[id*='admin-chatslist']";
	
	public AdminChatListPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byBackButton(){
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy byAdminOptionList(){
		return BySelector(By.cssSelector(Admin_Option_List), "admin option list");
	}
	public EnhancedBy byAdminChatlistProof(){
		return BySelector(By.cssSelector(Admin_Chatlist_Proof), "admin chatlist proof");
	}
}