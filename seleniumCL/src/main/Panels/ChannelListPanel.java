package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import core.helpers.Helper;
import main.pages.Chatnels;

public class ChannelListPanel extends Chatnels {

	ChatnelsManager manager;

	public ChannelListPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	private static final String Add_Channel_Button = "[id*='channel-list-holder'] .icon-plus"; 
	private static final String Channel_List_Proof = "[id*='channel-list-holder']";
	private static final String Channel_Item_Title = ".channel-item .title";
	private static final String Channel_search = "[id*='channel-list-holder'] .icon-search";
	private static final String Channel_Inbox = "[id*='channel-list-holder'] .icon-inbox-box";
	private static final String Channel_Profile = "[id*='channel-list-holder'] .icon-user";
	
	public EnhancedBy byAddChannelButton() {
		return BySelector(By.cssSelector(Add_Channel_Button), "add channel button");
	}
	
	public EnhancedBy byChannelListProof() {
		return BySelector(By.cssSelector(Channel_List_Proof),"channel list");				
	}
	public EnhancedBy byChannelItemTitle() {
		return BySelector(By.cssSelector(Channel_Item_Title),"channel item title");				
	}
	public EnhancedBy byChannelSearch() {
		return BySelector(By.cssSelector(Channel_search),"channel search");				
	}
	public EnhancedBy byChannelInbox() {
		return BySelector(By.cssSelector(Channel_Inbox),"channel inbox");				
	}
	public EnhancedBy byChannelProfile() {
		return BySelector(By.cssSelector(Channel_Profile),"channel profile");				
	}
}