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
public class InviteMember extends AbstractDriver {

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
		
//this test can only check the correctness of client side, cannot check the invitation email
		
		TestLog.Then("I succesfully logged in and see the channel list panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Member);
		TestLog.Then("I see admin chat panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Settings");
		TestLog.Then("I see info panel");
		Helper.clickAndExpect(app.chatnels.info.byMemberButton(), app.chatnels.member.byChannelMemberProof());
		TestLog.Then("I see member panel");
		Helper.clickAndExpect(app.chatnels.member.byManageButton(), app.chatnels.member.byPlusButton());
		TestLog.Then("I can add member or delete member");
		Helper.clickAndExpect(app.chatnels.member.byPlusButton(), app.chatnels.invite.byInvitePanelProof());
		TestLog.Then("I see invite panel");
		FormHelper.setField(UserInfo.CHATNELS_Second_Email, app.chatnels.invite.byEmialField());
		Helper.clickAndExpect(app.chatnels.invite.byPlusButton(), app.chatnels.invite.byMailItem());
		TestLog.Then("I see the email address has been added");
		Helper.clickAndExpect(app.chatnels.invite.bySendInvitationButton(), app.chatnels.inviteMessage.byInviteMessageProof());
		TestLog.Then("I see invite message");
		Helper.clickAndExpect(app.chatnels.inviteMessage.byOKButton(),app.chatnels.message.byMessageBoxProof());
		TestLog.Then("I see message box");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "OK");
		//wait for jason to enable deleting pending invitations, then delete the pending invitation to check if this test create new pending invitation 
//		Helper.verifyElementIsDisplayed(app.chatnels.member.byMailItem());
		TestLog.Then("I have succesfully invited a member");
	}
}