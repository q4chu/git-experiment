package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ChannelMemberPanel extends Chatnels{

	ChatnelsManager manager;

	public ChannelMemberPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Channel_Member_Proof = ".channel-contributor";
	private static final String Manage_Button = ".channel-contributor .green-btn";
	private static final String Plus_Button = ".channel-contributor .icon-plus";
	private static final String Del_Button = ".channel-contributor .del-btn";
	private static final String MailItem = ".channel-contributor .mail-item";
	private static final String MemberTitleList = ".friend-item .medium-font";
	
	public EnhancedBy byChannelMemberProof(){
		return BySelector(By.cssSelector(Channel_Member_Proof), "Channel member Proof");
	}
	public EnhancedBy byManageButton(){
		return BySelector(By.cssSelector(Manage_Button), "manage button");
	}
	public EnhancedBy byPlusButton(){
		return BySelector(By.cssSelector(Plus_Button), "plus button");
	}
	public EnhancedBy byDelButton(){
		return BySelector(By.cssSelector(Del_Button), "delete button");
	}
	public EnhancedBy byMailItem(){
		return BySelector(By.cssSelector(MailItem), "mail item");
	}
	public EnhancedBy byMemberTitleList(){
		return BySelector(By.cssSelector(MemberTitleList), "member title list");
	}
}
