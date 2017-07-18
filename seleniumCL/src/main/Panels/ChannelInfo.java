package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ChannelInfo extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Back_Button = ".channel-info .return"; 
	private static final String Red_Button_List = ".channel-info .red-btn .x-button-label"; //include Delete Channel, Unsubscribe 
	private static final String Channel_Info_Proof = ".channel-info";
	private static final String Member_Button = ".channel-info .icon-user";
	private static final String Privacy_Public_Button = ".channel-info .public";
	private static final String Privacy_Private_Button = ".channel-info .self";
	
	public ChannelInfo(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byBackButton(){
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy byRedButtonList(){
		return BySelector(By.cssSelector(Red_Button_List), "red button list");
	}
	public EnhancedBy bychannelInfoProof(){
		return BySelector(By.cssSelector(Channel_Info_Proof), "channel info proof");
	}
	public EnhancedBy byMemberButton(){
		return BySelector(By.cssSelector(Member_Button), "member button");
	}
	public EnhancedBy byPrivacyPublicButton(){
		return BySelector(By.cssSelector(Privacy_Public_Button), "privacy public icon");
	}
	public EnhancedBy byPrivacyPrivateButton(){
		return BySelector(By.cssSelector(Privacy_Private_Button), "privacy private icon");
	}
}
