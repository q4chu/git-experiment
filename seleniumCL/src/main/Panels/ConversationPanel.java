package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class ConversationPanel extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Back_Button = ".conversation-card .return"; 
	private static final String Show_Setting_Button= ".conversation-card .icon-ellipsis"; 
	private static final String Conversation_Panel_Proof = ".conversation-card";
	private static final String Camera_Button = ".conversation-card [id*='image-upload']";
	private static final String Input_Field = ".conversation-card Textarea";
	
	public ConversationPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byBackButton(){
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy byShowSettingButton(){
		return BySelector(By.cssSelector(Show_Setting_Button), "show setting button");
	}
	public EnhancedBy byConversationPanelProof(){
		return BySelector(By.cssSelector(Conversation_Panel_Proof), "conversation panel proof");
	}
	public EnhancedBy byCameraButton(){
		return BySelector(By.cssSelector(Camera_Button), "camera button");
	}
	public EnhancedBy byInputField(){
		return BySelector(By.cssSelector(Input_Field), "input field");
	}
}
