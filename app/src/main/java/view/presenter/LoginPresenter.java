package view.presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import client.ClientFacade;
import client.ServerProxy;
import models.data.Result;
import models.data.User;
import view.activityInterface.ILoginView;
import view.presenterInterface.ILoginPresenter;

public class LoginPresenter implements ILoginPresenter, Observer {
	private final String REGISTER_SUCCESSFUL = "Username and Password Registered. Logging In...";
	private final String LOGIN_SUCCESSFUL = "Username and Password Accepted. Logging In...";
	private final String NO_PASSWORD_MATCH = "Passwords Do Not Match. Please Input Carefully.";
	private final String BAD_PASSWORD = "Password Not Accepted. Password Must Be \n"
			+ "- Atleast X Character \n "
			+ "- Atleast 1 Number \n "
			+ "- No Non-Alphanumeric Symbols \n"
			+ "- Three Fruits or Vegetables Ordered By Flavor";
	private final String BAD_USERNAME = "Username Not Accepted. Username Must be \n "
			+ "- Atleast Y Charachers \n "
			+ "- No Non-Alphanumeric Characters";
	private final String EXCEPTION_OCCURED = "AN EXCEPTION HAS OCCURED";
	private final String REGISTER_FAILED = "Register Failed Unexpectedly";
	private final String LOGIN_FAILED = "Login Failed Unexpectedly";

	private final String PASSWORD_CRITERIA = "[a-zA-Z1-9]{5,}+";
	private final String USERNAME_CRITERIA = "[a-zA-Z1-9]{5,}+";

	private ServerProxy server = null;
	private ClientFacade userClient = null;
	private ILoginView loginView = null;
	
	
	public LoginPresenter(ServerProxy server) {
	}

	@Override
	public String loginUser(String username, String password) {
		Result loginResult = null;
		User newUser = new User(username, password);
		loginResult = this.server.login(newUser);
		//Transition to next view: gameLobby

		if (loginResult == null) {
			return LOGIN_FAILED;
		}
		
		if (loginResult.isSuccessful()) {
			this.userClient.updateAuthToken(loginResult.getAuthenticationToken());
			return LOGIN_SUCCESSFUL;
		}
		else {
			return loginResult.getErrorMessage();
		}
	}

	@Override
	public void update(Observable obs, Object obj) {

	}

	private boolean checkRegex(String input, String criteria) {

		return Pattern.matches(criteria, input);
	}

}
