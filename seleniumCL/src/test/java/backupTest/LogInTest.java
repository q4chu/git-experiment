package test.java.backupTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import core.driver.AbstractDriver;
import core.driver.TestLog;
import core.helpers.Helper;
import core.helpers.PageHelper;
import core.runner.ParallelRunner;
import main.categories.login;
import main.categories.user;
import main.constants.UserInfo;
import main.objects.UserObject;
import main.pages.Chatnels;

@RunWith(ParallelRunner.class)
public class LogInTest extends AbstractDriver {

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
	public void verifyLoginTest() {
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelProfile(), app.chatnels.profile.byLogoutButton(), app.chatnels.search.bySearchPanelProof());
		Helper.clickAndExpect(app.chatnels.profile.byLogoutButton(), app.chatnels.search.bySearchPanelProof(), app.chatnels.login.bySpinner());
	}

}