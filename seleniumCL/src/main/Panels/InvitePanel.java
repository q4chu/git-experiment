package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class InvitePanel extends Chatnels{

	ChatnelsManager manager;

	public InvitePanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Invite_Panel_Proof = "[id*='invite-list']";
	private static final String EmialField = "[id*='invite-list'] [name='email']";
	private static final String Plus_Button = "[id*='invite-list'] .icon-plus";
	private static final String Send_Invitation_Button = "[id*='invite-list'] .green-btn:not(.add-email)";
	private static final String MailItem = "[id*='invite-list'] .mail-item";
	
	public EnhancedBy byInvitePanelProof(){
		return BySelector(By.cssSelector(Invite_Panel_Proof), "invite panel proof");
	}
	public EnhancedBy byEmialField(){
		return BySelector(By.cssSelector(EmialField), "email field");
	}
	public EnhancedBy byPlusButton(){
		return BySelector(By.cssSelector(Plus_Button), "plus button");
	}
	public EnhancedBy bySendInvitationButton(){
		return BySelector(By.cssSelector(Send_Invitation_Button), "send invitation button");
	}
	public EnhancedBy byMailItem(){
		return BySelector(By.cssSelector(MailItem), "mail item");
	}
}
