package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

import core.driver.EnhancedBy;

public class NewChannelMessageBox extends Chatnels {
	ChatnelsManager manager;

	public NewChannelMessageBox(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Message_Box_Proof = ".chatnels-msgbox";
	private static final String MessageButtonList = ".chatnels-msgbox [class*='button-label']";
	
	public EnhancedBy byMessageBoxProof(){
		return BySelector(By.cssSelector(Message_Box_Proof), "message box Proof");
	}
	public EnhancedBy byMessageButtonList(){
		return BySelector(By.cssSelector(MessageButtonList), "message button");
	}
}
