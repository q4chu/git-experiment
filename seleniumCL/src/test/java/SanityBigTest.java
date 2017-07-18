package test.java;

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
public class SanityBigTest extends AbstractDriver {

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
	public void verifySanity() {
		
//This test will create a Chatnels channel, enter the line intercom, go into this channel and add a wall note, delete the channel and log out

		//create the channel
		Helper.clickAndExpect(app.chatnels.chatnelsList.byAddChannelButton(), app.chatnels.addChannel.byAddChannelProof());
		FormHelper.setField(ChannelInfo.Automation_Temporary, app.chatnels.addChannel.byChannelName());
		FormHelper.formSubmit(app.chatnels.addChannel.bySubmitButton(), app.chatnels.addPrivacy.byChannelPrivacyProof());
		Helper.clickAndExpect(app.chatnels.addPrivacy.bySubmitButton(), app.chatnels.message.byMessageBoxProof());
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "OK");
		
		//go into the line intercom
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Front Desk");
		ListHelper.selectListItemEqualsByName(app.chatnels.user.byChatOptionList(), "Line Intercom");
		WaitHelper.waitForElementToBeRemoved(app.chatnels.login.bySpinner());
		Helper.verifyElementIsDisplayed(app.chatnels.conversation.byConversationPanelProof());
		
		//go back to channel list
		Helper.clickAndExpect(app.chatnels.conversation.byBackButton(), app.chatnels.user.bychannelUserProof());
		Helper.clickAndExpect(app.chatnels.user.byBackButton(), app.chatnels.adminChat.byAdminChatlistProof());
		Helper.clickAndExpect(app.chatnels.adminChat.byBackButton(), app.chatnels.chatnelsList.byChannelListProof());
		
		//go into channel and add note
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Temporary);
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Wall");
		Helper.clickAndExpect(app.chatnels.wall.byAddButton(), app.chatnels.addWall.byAddOptionList());
		ListHelper.selectListItemEqualsByName(app.chatnels.addWall.byAddOptionList(), "Add Note");
		FormHelper.setField(ChannelInfo.Automation_Temporary, app.chatnels.noteEd.byNoteTitle());
		FormHelper.setField(ChannelInfo.Automation_Temporary, app.chatnels.noteEd.byNoteDescription());
		FormHelper.formSubmit(app.chatnels.noteEd.byNoteSubmit(), app.chatnels.wall.byWallPanelProof());
		Helper.verifyElementIsDisplayed(app.chatnels.wall.byWallNoteItem());
		
		//go back to channel list
		Helper.clickAndExpect(app.chatnels.wall.byBackButton(), app.chatnels.adminChat.byAdminChatlistProof());
		Helper.clickAndExpect(app.chatnels.adminChat.byBackButton(), app.chatnels.chatnelsList.byChannelListProof());
		
		//delete the channel
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Temporary);
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Settings");
		ListHelper.selectListItemEqualsByName(app.chatnels.info.byRedButtonList(), "Delete Channel");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(),"Yes");
		Helper.verifyElementIsDisplayed(app.chatnels.chatnelsList.byChannelListProof());
		
		//logout
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelProfile(), app.chatnels.profile.byLogoutButton());
		Helper.clickAndExpect(app.chatnels.profile.byLogoutButton(), app.chatnels.search.bySearchPanelProof(), app.chatnels.login.bySpinner());
	}

}