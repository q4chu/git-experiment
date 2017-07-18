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
public class DeleteMember extends AbstractDriver {

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
	public void verifyDeleteMember() {
		
//NOTICE: AFTER THE TEST, MAKE SURE TO INVITE THE MEMBER BACK TO KEEP THE TEST RUNABLE 
		
		TestLog.Then("I succesfully logged in and see the channel list panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), ChannelInfo.Automation_Member);
		TestLog.Then("I see admin chat panel");
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Settings");
		TestLog.Then("I see info panel");
		Helper.clickAndExpect(app.chatnels.info.byMemberButton(), app.chatnels.member.byChannelMemberProof());
		TestLog.Then("I see member panel");
		Helper.clickAndExpect(app.chatnels.member.byManageButton(), app.chatnels.member.byPlusButton());
		TestLog.Then("I can add member or delete member");
		Helper.clickAndExpect(app.chatnels.member.byDelButton(), app.chatnels.message.byMessageBoxProof());
		TestLog.Then("I see message box");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "OK");
		WaitHelper.waitForElementToBeRemoved(app.chatnels.message.byMessageBoxProof());
		TestLog.Then("I see message box");
		ListHelper.selectListItemEqualsByName(app.chatnels.message.byMessageButtonList(), "OK");
		ListHelper.verifyIsNotInList(app.chatnels.member.byMemberTitleList(),UserInfo.CHATNELS_Member_name);
	}
}