package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class AddChannelPrivacyPanel extends Chatnels{

	ChatnelsManager manager;

	public AddChannelPrivacyPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Channel_Privacy_Proof = "[id*='addchannel-privacy']";
	private static final String Privacy_JustMe = "[id*='addchannel-privacy'] .self";
	private static final String Privacy_MembersOnly = "[id*='addchannel-privacy'] .private";
	private static final String Privacy_Unlisted = "[id*='addchannel-privacy'] .unlisted";
	private static final String Privacy_Public = "[id*='addchannel-privacy'] .public";
	private static final String Submit_Button = "[id*='addchannel-privacy'] .green-btn";
	private static final String Cancel_Button = "[id*='addchannel-privacy'] .icon-cancel";
	
	public EnhancedBy byChannelPrivacyProof(){
		return BySelector(By.cssSelector(Channel_Privacy_Proof), "Channel Privacy Proof");
	}
	public EnhancedBy byPrivacyJustMe(){
		return BySelector(By.cssSelector(Privacy_JustMe), "privacy just me");
	}
	public EnhancedBy byPrivacyMembersOnly(){
		return BySelector(By.cssSelector(Privacy_MembersOnly), "privacy members only");
	}
	public EnhancedBy byPrivacyUnlisted(){
		return BySelector(By.cssSelector(Privacy_Unlisted), "privacy unlisted");
	}
	public EnhancedBy byPrivacyPublic(){
		return BySelector(By.cssSelector(Privacy_Public), "privacy public");
	}
	public EnhancedBy bySubmitButton(){
		return BySelector(By.cssSelector(Submit_Button), "submit button");
	}
	public EnhancedBy byCancelButton(){
		return BySelector(By.cssSelector(Cancel_Button), "cancel button");
	}
}
