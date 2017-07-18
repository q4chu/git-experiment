package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class AddChannel extends Chatnels{

	ChatnelsManager manager;

	public AddChannel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Add_Channel_Proof = "[id*='addchannel-info']";
	private static final String Channel_Name = "[id*='addchannel-info'] [name*='name']";
	private static final String Channel_Description = "[id*='addchannel-info'] [name*='desc']";
	private static final String Submit_Button = "[id*='addchannel-info'] .green-btn";
	private static final String Cancel_Button = "[id*='addchannel-info'] .icon-cancel";
	
	public EnhancedBy byAddChannelProof(){
		return BySelector(By.cssSelector(Add_Channel_Proof), "Add Channel Proof");
	}
	public EnhancedBy byChannelName(){
		return BySelector(By.cssSelector(Channel_Name), "channel name");
	}
	public EnhancedBy byChannelDescription(){
		return BySelector(By.cssSelector(Channel_Description), "channel description");
	}
	public EnhancedBy bySubmitButton(){
		return BySelector(By.cssSelector(Submit_Button), "submit button");
	}
	public EnhancedBy byCancelButton(){
		return BySelector(By.cssSelector(Cancel_Button), "cancel button");
	}
}
