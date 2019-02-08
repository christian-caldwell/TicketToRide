package view.presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import client.ServerProxy;
import models.data.Result;
import models.data.User;
import view.facade.client.ClientFacade;
import view.facade.client.out.RegisterFacadeOut;
import view.presenterInterface.IRegisterPresenter;
import view.activityInterface.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter, Observer {
    private final String REGISTER_SUCCESSFUL = "Username and Password Registered. Logging In...";
    private final String NO_PASSWORD_MATCH = "Passwords Do Not Match. Please Input Carefully.";
    private final String BAD_PASSWORD = "Password Not Accepted. Password Must Be \n"
            + "- Atleast X Character \n "
            + "- Atleast 1 Number \n "
            + "- No Non-Alphanumeric Symbols \n"
            + "- Three Fruits or Vegetables Ordered By Flavor";
    private final String BAD_USERNAME = "Username Not Accepted. Username Must be \n "
            + "- Atleast Y Charachers \n "
            + "- No Non-Alphanumeric Characters";
    private final String REGISTER_FAILED = "Register Failed Unexpectedly";

    private final String PASSWORD_CRITERIA = "[a-zA-Z1-9]{5,}+";
    private final String USERNAME_CRITERIA = "[a-zA-Z1-9]{5,}+";

    private IRegisterView registerView = null;


    public RegisterPresenter(ServerProxy server) {
    }

    @Override
    public String registerUser(String username, String password, String repeatedPassword) {
        //Compare Passwords
        if (password.compareTo(repeatedPassword) != 0) {
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

        //RegisterActivity.NotifyRegisterStarted()

        User newUser = new User(username, password);
        RegisterFacadeOut registerFacadeOut = new RegisterFacadeOut();

        Result registerResult = registerFacadeOut.register(newUser);
        //Transistion to next view: gameLobby

        if (registerResult == null) {
            return REGISTER_FAILED;
        }

        if (registerResult.isSuccessful()) {
            ClientFacade client = new ClientFacade();
            client.updateAuthToken(registerResult.getAuthenticationToken());
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
