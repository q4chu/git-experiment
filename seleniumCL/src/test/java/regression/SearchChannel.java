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
public class SearchChannel extends AbstractDriver {

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
		TestLog.Then("I succesfully logged in and see the channel list panel");
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelSearch(), app.chatnels.search.bySearchPanelProof());
		TestLog.Then("I see search panel");
		FormHelper.setFieldAndEnter(ChannelInfo.Automation_Permanent, app.chatnels.search.bySearchInputField());
//hardcoded here, wait for the result to show up
		TestLog.Then("I wait 2 seconds for the result");
		WaitHelper.waitForSeconds(2);
		ListHelper.selectListItemEqualsByName(app.chatnels.search.bySearchListTitle(), ChannelInfo.Automation_Permanent);
		Helper.verifyElementIsDisplayed(app.chatnels.adminChat.byAdminChatlistProof());
	}
}