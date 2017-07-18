package main.Panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.driver.EnhancedBy;
import core.helpers.FormHelper;
import core.helpers.Helper;
import main.objects.UserObject;
import main.pages.Chatnels;

public class LoginPanel extends Chatnels {

	ChatnelsManager manager;

	public LoginPanel(WebDriver driver, ChatnelsManager manager) {
		super(driver);
		this.manager = manager;

	}	

	private static final String EMAIL_FIELD = ".login .x-input-email";
	private static final String PASSWORD_FIELD = ".login .x-input-password";
	private static final String LOGIN_SUBMIT = ".login .sign-in";
	private static final String Login_Panel_Proof = ".login";
	private static final String Spinner = ".x-mask-loadmask .x-loading-spinner-outer";
	
	public EnhancedBy byLoginPanelProof() {
		return BySelector(By.cssSelector(Login_Panel_Proof), "login panel proof");
	}
	
	public EnhancedBy byEmailField() {
		return BySelector(By.cssSelector(EMAIL_FIELD), "email field");
	}

	public EnhancedBy byPasswordField() {
		return BySelector(By.cssSelector(PASSWORD_FIELD), "password field");
	}

	public EnhancedBy byLoginSubmit() {
		return BySelector(By.cssSelector(LOGIN_SUBMIT), "submit button");
	}
	
	public EnhancedBy bySpinner() {
		return BySelector(By.cssSelector(Spinner), "spinner");
	}
	
	/**
	 * enter login info and click login button
	 * 
	 * @param user
	 */
	public void login(UserObject user) {
		Helper.clickAndExpect(manager.search.byLoginButton(), byLoginPanelProof());
		FormHelper.setField(user.email, byEmailField());
		FormHelper.setField(user.password, byPasswordField());
		FormHelper.formSubmit(byLoginSubmit(), manager.chatnelsList.byChannelListProof(),bySpinner());
	}
}