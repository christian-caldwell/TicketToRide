package view.presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import client.ClientModel;
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
            + "- At least 5 Characters \n "
            + "- At least 1 Number \n "
            + "- No Non-Alphanumeric Symbols \n";

    private final String BAD_USERNAME = "Username Not Accepted. Username Must be \n "
            + "- Atleast 5 Charachers \n "
            + "- No Non-Alphanumeric Characters";
    private final String REGISTER_FAILED = "Register Failed Unexpectedly";

    private final String PASSWORD_CRITERIA = "[a-zA-Z1-9]{5,}+";
    private final String USERNAME_CRITERIA = "[a-zA-Z1-9]{5,}+";

    private IRegisterView registerView = null;


    public RegisterPresenter(ServerProxy server) {
    }

    public RegisterPresenter() {

    }

    @Override
    public Result registerUser(String username, String password, String repeatedPassword) {
        Result result = new Result();
        //Compare Passwords
        if (password.compareTo(repeatedPassword) != 0) {
            result.setErrorMessage(NO_PASSWORD_MATCH);
            result.setSuccessful(false);
            return result;
        }

        //Match Password to Reg-ex
        if (!checkRegex(username, this.USERNAME_CRITERIA)) {
            result.setErrorMessage(BAD_USERNAME);
            result.setSuccessful(false);
            return result; //If password characters are unacceptable
        }

        //Match Username to Reg-ex
        if (!checkRegex(password, this.PASSWORD_CRITERIA)) {
            result.setSuccessful(false);
            result.setErrorMessage(BAD_PASSWORD);
            return result; //If username characters are unacceptable
        }

        //RegisterActivity.NotifyRegisterStarted()

        User newUser = new User(username, password);
        RegisterFacadeOut registerFacadeOut = new RegisterFacadeOut();

        Result registerResult = registerFacadeOut.register(newUser);
        //Transistion to next view: gameLobby

        if (registerResult == null) {
            result.setErrorMessage(REGISTER_FAILED);
            result.setSuccessful(false);
            return result;
        }

        if (registerResult.isSuccessful()) {
            ClientFacade client = new ClientFacade();
            client.setUser(newUser);
            result.setErrorMessage(REGISTER_SUCCESSFUL);
            result.setSuccessful(true);
            return result;
        }
        else {
            return registerResult;
        }


    }

    @Override
    public void update(Observable o, Object obj) {
        ClientModel client = (ClientModel) o;
    }

    private boolean checkRegex(String input, String criteria) {

        return Pattern.matches(criteria, input);
    }

}
