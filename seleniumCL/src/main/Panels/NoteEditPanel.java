package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import main.pages.Chatnels;

public class NoteEditPanel extends Chatnels{

	ChatnelsManager manager;

	public NoteEditPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;
	}

	private static final String Note_Edit_proof = "[id*='noteeditpanel']";
	private static final String Note_Title = "[id*='noteeditpanel'] [name='data.title']";
	private static final String Note_Description = "[id*='noteeditpanel'] [name='data.src']";
	private static final String Note_Url = "[id*='noteeditpanel'] [name='webLink']";
	private static final String Note_Document = "[id*='noteeditpanel'] [name='file']";
	private static final String Note_Pin = "[id*='noteeditpanel'] .x-toggle'";
	private static final String Note_Close = "[id*='noteeditpanel'] .icon-close";
	private static final String Note_Submit = "[id*='noteeditpanel'] .green-btn";
	
	public EnhancedBy byNoteEditProof() {
		return BySelector(By.cssSelector(Note_Edit_proof), "note edit proof");
	}
	public EnhancedBy byNoteTitle() {
		return BySelector(By.cssSelector(Note_Title), "note title");
	}
	public EnhancedBy byNoteDescription() {
		return BySelector(By.cssSelector(Note_Description), "note description");
	}
	public EnhancedBy byNoteUrl() {
		return BySelector(By.cssSelector(Note_Url), "note url");
	}
	public EnhancedBy byNoteDocument() {
		return BySelector(By.cssSelector(Note_Document), "note document");
	}
	public EnhancedBy byNotePin() {
		return BySelector(By.cssSelector(Note_Pin), "note pin");
	}
	public EnhancedBy byNoteClose() {
		return BySelector(By.cssSelector(Note_Close), "note close");
	}
	public EnhancedBy byNoteSubmit() {
		return BySelector(By.cssSelector(Note_Submit), "note submit");
	}
}
