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
import core.helpers.FileUploadHelper;
import core.runner.ParallelRunner;
import main.categories.login;
import main.categories.user;
import main.constants.UserInfo;
import main.constants.PhotoInfo;
import main.objects.UserObject;
import main.pages.Chatnels;

@RunWith(ParallelRunner.class)
public class ChangeCoverPhoto extends AbstractDriver {

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
	public void verifyChnageCoverPhoto() {
		
//this test is not finished, wait for Ehsan's help
		
		Helper.clickAndExpect(app.chatnels.chatnelsList.byChannelProfile(), app.chatnels.profile.byLogoutButton());
		FileUploadHelper.uploadFile(PhotoInfo.CoverPhotoPath, app.chatnels.profile.byUploadButton());
	}
}