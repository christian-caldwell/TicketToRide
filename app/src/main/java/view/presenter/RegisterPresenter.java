package view.presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import client.ClientFacade;
import client.ServerProxy;
import models.data.Result;
import models.data.User;
import view.activityInterface.ILoginView;
import view.presenterInterface.IRegisterPresenter;

public class RegisterPresenter implements IRegisterPresenter, Observer {
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


    public RegisterPresenter() {
    }

    @Override
    public String registerUser(String username, String password, String repeatedPassword) {
        //Compare Passwords
        // It should already check before it gets her. Logic is in activity.
        if (password != repeatedPassword) {
            return NO_PASSWORD_MATCH; //If no match
        }

        //Match Password to Reg-ex
        if (checkRegex(password, this.PASSWORD_CRITERIA)) {
            return BAD_PASSWORD; //If password characters are unacceptable
        }

        //Match Username to Reg-ex
        if (checkRegex(password, this.USERNAME_CRITERIA)) {
            return BAD_USERNAME; //If username characters are unacceptable
        }

        Result registerResult = null;
        User newUser = new User(username, password);


//        registerResult = this.server.register(newUser);
        //Transistion to next view: gameLobby

        if (registerResult == null) {
            return REGISTER_FAILED;
        }

        if (registerResult.isSuccessful()) {
            this.userClient.updateAuthToken(registerResult.getAuthenticationToken());
            return REGISTER_SUCCESSFUL;
        }
        else {
            return registerResult.getErrorMessage();
        }


    }

    @Override
    public void update(Observable obs, Object obj) {

    }

    private boolean checkRegex(String input, String criteria) {

        return Pattern.matches(criteria, input);
    }

}
