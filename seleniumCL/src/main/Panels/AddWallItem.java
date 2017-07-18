package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class AddWallItem extends Chatnels{

	ChatnelsManager manager;

	public AddWallItem(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Add_Wall_Item_Proof = "[id*='itemselection']";
	private static final String Add_option_List = "[id*='itemselection'] .x-button-label";  //Add Note; Add Image; Add Event; Add Video; Cancel
	
	public EnhancedBy byAddWallItemProof(){
		return BySelector(By.cssSelector(Add_Wall_Item_Proof), "Add wall item Proof");
	}
	public EnhancedBy byAddOptionList(){
		return BySelector(By.cssSelector(Add_option_List), "add option list");
	}
}