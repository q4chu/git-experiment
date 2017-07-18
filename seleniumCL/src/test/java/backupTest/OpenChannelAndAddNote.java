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
public class OpenChannelAndAddNote extends AbstractDriver {

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
		ListHelper.selectListItemEqualsByName(app.chatnels.chatnelsList.byChannelItemTitle(), "automation");
		ListHelper.selectListItemEqualsByName(app.chatnels.adminChat.byAdminOptionList(), "Wall");
		Helper.clickAndExpect(app.chatnels.wall.byAddButton(), app.chatnels.addWall.byAddOptionList());
		ListHelper.selectListItemEqualsByName(app.chatnels.addWall.byAddOptionList(), "Add Note");
		FormHelper.setField("automation", app.chatnels.noteEd.byNoteTitle());
		FormHelper.setField("automation", app.chatnels.noteEd.byNoteDescription());
		FormHelper.formSubmit(app.chatnels.noteEd.byNoteSubmit(), app.chatnels.wall.byWallPanelProof());
		Helper.verifyElementIsDisplayed(app.chatnels.wall.byWallNoteItem());
	}

}
