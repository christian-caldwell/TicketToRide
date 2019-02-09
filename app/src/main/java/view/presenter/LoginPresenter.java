package view.presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import client.ClientModel;
import client.Poller;

import models.data.Result;
import models.data.User;
import view.activityInterface.ILoginView;
import view.facade.client.ClientFacade;
import view.facade.client.out.LoginFacadeOut;
import view.presenterInterface.ILoginPresenter;

public class LoginPresenter implements ILoginPresenter, Observer {
	private final String LOGIN_SUCCESSFUL = "Username and Password Accepted. Logging In...";
	private final String BAD_PASSWORD = "Password Not Accepted. Password Must Be \n"
			+ "- Atleast X Character \n "
			+ "- Atleast 1 Number \n "
			+ "- No Non-Alphanumeric Symbols \n"
			+ "- Three Fruits or Vegetables Ordered By Flavor";
	private final String BAD_USERNAME = "Username Not Accepted. Username Must be \n "
			+ "- At least Y Charachers \n "
			+ "- No Non-Alphanumeric Characters";
	private final String EXCEPTION_OCCURED = "AN EXCEPTION HAS OCCURED";
	private final String LOGIN_FAILED = "Login Failed Unexpectedly";

	private final String PASSWORD_CRITERIA = "[a-zA-Z1-9]{5,}+";
	private final String USERNAME_CRITERIA = "[a-zA-Z1-9]{5,}+";

	private ILoginView loginView = null;
	private ClientFacade userClient = null;
	
	
	public LoginPresenter() {
	}

	@Override
	public Result loginUser(String username, String password) {
		Result result = new Result();
		//Match Password to Reg-ex
		if (checkRegex(password, this.PASSWORD_CRITERIA)) {
			result.setErrorMessage(BAD_PASSWORD);
			result.setSuccesful(false);
			return result; //If password characters are unacceptable
		}

		//Match Username to Reg-ex
		if (checkRegex(password, this.USERNAME_CRITERIA)) {
			result.setErrorMessage(BAD_USERNAME);
			result.setSuccesful(false);
			return result; //If password characters are unacceptable
		}

		//LoginActivity.NotifyLoginStarted()

		User returnUser = new User(username, password);
		LoginFacadeOut loginFacadeOut = new LoginFacadeOut();

		Result loginResult = loginFacadeOut.login(returnUser);
		//Transition to next view: gameLobby

		if (loginResult == null) {
			result.setSuccesful(false);
			result.setErrorMessage(LOGIN_FAILED);
			return result;
		}
		
		if (loginResult.isSuccessful()) {
			ClientFacade client = new ClientFacade();
			client.updateAuthToken(result.getAuthenticationToken());
			return loginResult;
		}
		else {
			return loginResult;
		}
	}

	@Override
	public boolean onCreate() {
		try {
			ClientModel.create();
			Poller.start();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private boolean checkRegex(String input, String criteria) {
		return Pattern.matches(criteria, input);
	}

	///// Observer Functions
	@Override
	public void update(Observable obs, Object obj) {

	}


}
