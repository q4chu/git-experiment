package main.Panels;

import org.openqa.selenium.WebDriver;

import main.pages.Chatnels;

public class ChatnelsManager extends Chatnels {

	public ChatnelsManager(WebDriver driver) {
		super(driver);

	}

	public LoginPanel login = new LoginPanel(getWebDriver(), this);
	public AddChannel addChannel = new AddChannel(getWebDriver(), this);
	public AddWallItem addWall = new AddWallItem(getWebDriver(), this);
	public AdminChatListPanel adminChat = new AdminChatListPanel(getWebDriver(), this);
	public AddChannelPrivacyPanel addPrivacy = new AddChannelPrivacyPanel(getWebDriver(), this);
	public ChannelUserPanel user = new ChannelUserPanel(getWebDriver(), this);
	public ChannelListPanel chatnelsList = new ChannelListPanel(getWebDriver(), this);
	public ConversationPanel conversation = new ConversationPanel(getWebDriver(), this);
	public NewChannelMessageBox message = new NewChannelMessageBox(getWebDriver(),this);
	public NoteEditPanel noteEd = new NoteEditPanel(getWebDriver(),this);
	public WallPanel wall = new WallPanel(getWebDriver(),this);
	public ProfilePanel profile = new ProfilePanel(getWebDriver(),this);
	public SearchPanel search = new SearchPanel(getWebDriver(),this);
	public ChannelInfo info = new ChannelInfo(getWebDriver(),this);
	public ProfilePassword password = new ProfilePassword(getWebDriver(),this);
	public ChannelMemberPanel member = new ChannelMemberPanel(getWebDriver(),this);
	public InvitePanel invite = new InvitePanel(getWebDriver(),this);
	public InviteMessagePanel inviteMessage = new InviteMessagePanel(getWebDriver(),this);
	public ChangePrivacyPanel privacy = new ChangePrivacyPanel(getWebDriver(),this);
}
