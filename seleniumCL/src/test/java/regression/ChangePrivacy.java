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
import main.constants.ChannelInfo;
import main.constants.UserInfo;
import main.objects.UserObject;
import main.pages.Chatnels;

@RunWith(ParallelRunner.class)
public class ChangePrivacy extends AbstractDriver {

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
	public void verifyInviteMember() {
		
//This test will change the privacy to just me and change it back to public
		TestLog.Then("I succesfully logged in and see the channel list panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Permanent);
		TestLog.Then("I am in automation_permanent admin chat panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Settings");
		TestLog.Then("I am in info panel");
		Helper.clickAndExpect(app.chatnels.info.byPrivacyPublicButton(), app.chatnels.privacy.byChannelPrivacyProof());
		TestLog.Then("I see privacy panel");
		Helper.clickAndExpect(app.chatnels.privacy.byPrivacyJustMe(), app.chatnels.privacy.byPrivacyJustMe());
		Helper.clickAndExpect(app.chatnels.privacy.bySubmitButton(), app.chatnels.message.byMessageBoxProof());
		TestLog.Then("I see privacy message box");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "Yes");
		WaitHelper.waitForElementToBeRemoved(app.chatnels.login.bySpinner());
		Helper.verifyElementIsDisplayed(app.chatnels.info.byPrivacyPrivateButton());
		
		//go back and go to search panel to check if the privacy changed
		Helper.clickAndExpect(app.chatnels.info.byBackButton(), app.chatnels.adminChat.byAdminChatlistProof());
		Helper.clickAndExpect(app.chatnels.adminChat.byBackButton(), app.chatnels.chatnelsList.byChannelListProof());
		TestLog.Then("I go back to channel list panel");
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelSearch(), app.chatnels.search.bySearchPanelProof());
		TestLog.Then("I see search panel");
		FormHelper.setFieldAndEnter(ChannelInfo.Automation_Permanent, app.chatnels.search.bySearchInputField());
//hardcoded here, wait for the search result to come out. 
		WaitHelper.waitForSeconds(2);
		Helper.verifyElementIsNotDisplayed(app.chatnels.search.bySearchItem());
		
		//change back the privacy to make sure the test case is runable next time
		Helper.clickAndExpect(app.chatnels.search.byBackButton(), app.chatnels.chatnelsList.byChannelListProof());
		TestLog.Then("I go back to channel list panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Permanent);
		TestLog.Then("I am in automation_permanent admin chat panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Settings");
		TestLog.Then("I am in info panel");
		Helper.clickAndExpect(app.chatnels.info.byPrivacyPrivateButton(), app.chatnels.privacy.byChannelPrivacyProof());
		TestLog.Then("I see privacy panel");
		Helper.clickAndExpect(app.chatnels.privacy.byPrivacyPublic(), app.chatnels.privacy.byPrivacyPublic());
		Helper.clickAndExpect(app.chatnels.privacy.bySubmitButton(), app.chatnels.info.byPrivacyPublicButton(), app.chatnels.login.bySpinner());
		TestLog.Then("I see privacy changed back to public");
	}
}