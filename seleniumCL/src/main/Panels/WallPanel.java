package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class WallPanel extends Chatnels {

	ChatnelsManager manager;
	
	private static final String Back_Button = "[id*='wallholder'] .return"; 
	private static final String WallPannel_Proof = "[id*='wallholder']"; //include title, settings, wall, chat lines, front desk
	private static final String Add_Button = "[id*='wallholder'] .icon-plus";
	private static final String Note_Item = "[id*='wallholder'] .note[class*='wallitem']";
	
	public WallPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}
	
	public EnhancedBy byBackButton(){
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy byAddButton(){
		return BySelector(By.cssSelector(Add_Button), "add button");
	}
	public EnhancedBy byWallPanelProof(){
		return BySelector(By.cssSelector(WallPannel_Proof), "wall panel proof");
	}
	public EnhancedBy byWallNoteItem(){
		return BySelector(By.cssSelector(Note_Item), "wall note item");
	}
}
