//package presenters;
//
//import game.User;
//import client.Client;
//
//public class LoginPresenter extends Observable implements ILoginPresenter {
//	private final String REGISTER_SUCCESSFUL = "Username and Password Registered. Logging In...";
//	private final String LOGIN_SUCCESSFUL = "Username and Password Accepted. Logging In...";
//	private final String NO_PASSWORD_MATCH = "Passwords Do Not Match. Please Input Carefully.";
//	private final String BAD_PASSWORD = "Password Not Accepted. Password Must Be \n"
//			+ "- Atleast X Character \n "
//			+ "- Atleast 1 Number \n "
//			+ "- No Non-Alphanumeric Symbols \n"
//			+ "- Three Fruits or Vegetables Ordered By Flavor";
//	private final String BAD_USERNAME = "Username Not Accepted. Username Must be \n "
//			+ "- Atleast Y Charachers \n "
//			+ "- No Non-Alphanumeric Characters";
//	private final String EXCEPTION_OCCURED = "AN EXCEPTION HAS OCCURED";
//	private ViewFacade facade = null;
//	private Client userClient = null;
//	private ILoginView loginView = null;
//
//
//	public LoginPresenter(ViewFacade facade, Client client, ILoginView loginView) {
//		this.facade = facade;
//		this.userClient = client;
//		this.ILoginView = loginView;
//	}
//
//	@Override
//	public String registerUser(String username, String password, String repeatedPassword) {
//		//Compare Passwords
//		if (password != repeatedPassword) {
//			return NO_PASSWORD_MATCH; //If no match
//		}
//
//		//Match Password to Reg-ex
//		if (false) {
//			return BAD_PASSWORD; //If password characters are unacceptable
//		}
//
//		//Match Username to Reg-ex
//		if (false) {
//			return BAD_USERNAME; //If username characters are unacceptable
//		}
//
//		try {
//			User newUser = new User(username, password);
//			String newAuthToken = facade.register(newUser);
//			userClient.updateAuthToken(newAuthToken);
//			//Transistion to next view: gameLobby
//		}
//		catch () {
//			return EXCEPTION_OCCURED;
//		}
//
//		return REGISTER_SUCCESSFUL;
//	}
//
//	@Override
//	public String loginUser(String username, String password) {
//		try {
//			User returningUser = new User(username, password);
//			String newAuthToken = facade.loginUser(returningUser);
//			userClient.updateAuthToken(newAuthToken);
//			//Transistion to next view: gameLobby
//		}
//		catch () {
//			return EXCEPTION_OCCURED;
//		}
//
//		return LOGIN_SUCCESSFUL;
//	}
//
//	@Override
//	public void update() {
//		//Update view when called by server-side observer
//	}
//}
