package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import core.helpers.FormHelper;
import core.helpers.Helper;
import main.objects.UserObject;
import main.pages.Chatnels;

public class SearchPanel extends Chatnels {

	ChatnelsManager manager;

	public SearchPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;

	}	

	private static final String Login_Button = ".search [id*='chatnels-toolbar'] .x-docked-right .x-button-label[id*=element]";
	private static final String Back_Button = ".search .icon-thumbnails";
	private static final String Search_Panel_Proof = ".search";
	private static final String Search_List_Title = ".search .title";
	private static final String SearchInputField = ".search input";
	private static final String SearchItem = ".search-item";
	
	public EnhancedBy byLoginButton() {
		return BySelector(By.cssSelector(Login_Button), "login button");
	}
	public EnhancedBy byBackButton() {
		return BySelector(By.cssSelector(Back_Button), "back button");
	}
	public EnhancedBy bySearchPanelProof() {
		return BySelector(By.cssSelector(Search_Panel_Proof), "search panel proof");
	}
	public EnhancedBy bySearchListTitle() {
		return BySelector(By.cssSelector(Search_List_Title), "search list title");
	}
	public EnhancedBy bySearchInputField() {
		return BySelector(By.cssSelector(SearchInputField), "search input field");
	}
	public EnhancedBy bySearchItem() {
		return BySelector(By.cssSelector(SearchItem), "search item");
	}
}
