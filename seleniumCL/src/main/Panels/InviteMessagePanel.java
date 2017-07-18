package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class InviteMessagePanel extends Chatnels{

	ChatnelsManager manager;

	public InviteMessagePanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Invite_Message_Proof = "[id*='invite-message']";
	private static final String OK_Button = "[id*='invite-message'] .green-btn";
	
	public EnhancedBy byInviteMessageProof(){
		return BySelector(By.cssSelector(Invite_Message_Proof), "invite message proof");
	}
	public EnhancedBy byOKButton(){
		return BySelector(By.cssSelector(OK_Button), "ok button");
	}
}
