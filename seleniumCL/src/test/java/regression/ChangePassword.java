package test.java.regression;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import core.driver.AbstractDriver;
import core.driver.TestLog;
import core.helpers.FormHelper;
import core.helpers.Helper;
import core.helpers.ListHelper;
import core.helpers.PageHelper;
import core.helpers.WaitHelper;
import core.runner.ParallelRunner;
import main.categories.login;
import main.categories.user;
import main.constants.UserInfo;
import main.objects.UserObject;
import main.pages.Chatnels;

@RunWith(ParallelRunner.class)
public class ChangePassword extends AbstractDriver {

	@Before
	public void beforeMethod() throws Exception {
		setupWebDriver(Chatnels.chatnels);
		
		UserObject user = new UserObject().withEmail(UserInfo.CHATNELS_Email)
				.withPassword(UserInfo.CHATNELS_Password);
		TestLog.When("I login with admin user");
		PageHelper.switchIframe("ihandle");
		app.chatnels.login.login(user);
	}

	@Category({ login.class, user.class })
	@Test
	public void verifyChnagePassword() {
		
//This test will change the password and change it back
		
		TestLog.Then("I succesfully logged in and see the channel list panel");
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelProfile(), app.chatnels.profile.byLogoutButton());
		TestLog.Then("I see profile panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.profile.byOptionList(), "Change Password");
		TestLog.Then("I see password panel");
		FormHelper.setField(UserInfo.CHATNELS_Password, app.chatnels.password.byCurrentPassword());
		FormHelper.setField(UserInfo.CHATNELS_Change_Password, app.chatnels.password.byNewPassword());
		FormHelper.setField(UserInfo.CHATNELS_Change_Password, app.chatnels.password.byReNewPassword());
		Helper.clickAndExpect(app.chatnels.password.bySubmitButton(), app.chatnels.message.byMessageBoxProof(),app.chatnels.login.bySpinner());
		TestLog.Then("I see message box");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "OK");
		TestLog.Then("I changed my password");
		
		//change the password back
		ListHelper.selectListItemEqualsByName(app.chatnels.profile.byOptionList(), "Change Password");
		TestLog.Then("I see password panel");
		FormHelper.setField(UserInfo.CHATNELS_Change_Password, app.chatnels.password.byCurrentPassword());
		FormHelper.setField(UserInfo.CHATNELS_Password, app.chatnels.password.byNewPassword());
		FormHelper.setField(UserInfo.CHATNELS_Password, app.chatnels.password.byReNewPassword());
		Helper.clickAndExpect(app.chatnels.password.bySubmitButton(), app.chatnels.message.byMessageBoxProof(),app.chatnels.login.bySpinner());
		TestLog.Then("I see message box and changed back my password");
	}
}