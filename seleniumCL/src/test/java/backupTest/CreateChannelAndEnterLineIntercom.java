package test.java.backupTest;

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
public class CreateChannelAndEnterLineIntercom extends AbstractDriver {

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
	public void verifyCreateChannelAndAddNote() {
		Helper.clickAndExpect(app.chatnels.chatnelsList.byAddChannelButton(), app.chatnels.addChannel.byAddChannelProof());
		FormHelper.setField("automaion", app.chatnels.addChannel.byChannelName());
		FormHelper.formSubmit(app.chatnels.addChannel.bySubmitButton(), app.chatnels.addPrivacy.byChannelPrivacyProof());
		Helper.clickAndExpect(app.chatnels.addPrivacy.bySubmitButton(), app.chatnels.message.byMessageBoxProof());
		Helper.clickAndExpect(app.chatnels.message.byMessageButtonList(), app.chatnels.adminChat.byAdminChatlistProof());
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Front Desk");
		ListHelper.selectListItemEqualsByName(app.chatnels.user.byChatOptionList(), "Line Intercom");
		WaitHelper.waitForElementToBeRemoved(app.chatnels.login.bySpinner());
		Helper.verifyElementIsDisplayed(app.chatnels.conversation.byConversationPanelProof());
	}

}
