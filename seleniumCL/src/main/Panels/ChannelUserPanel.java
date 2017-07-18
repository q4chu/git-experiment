package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ChannelUserPanel extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Back_Button = "[id*='channel-users'] .return"; 
	private static final String Chat_Option_List = "[id*='channel-users'] .list-button .x-button-label"; //include Line Intercom
	private static final String Channel_User_Proof = "[id*='channel-users']";
	
	public ChannelUserPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byBackButton(){
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy byChatOptionList(){
		return BySelector(By.cssSelector(Chat_Option_List), "chat option list");
	}
	public EnhancedBy bychannelUserProof(){
		return BySelector(By.cssSelector(Channel_User_Proof), "channel user proof");
	}
}
