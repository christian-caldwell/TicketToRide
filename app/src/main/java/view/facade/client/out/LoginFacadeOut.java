package view.facade.client.out;

import client.ServerProxy;
import models.data.User;

public class LoginFacadeOut {
    private ServerProxy server = null;


    public String login(User newUser) {
        return "auth token returned";
    }
}
