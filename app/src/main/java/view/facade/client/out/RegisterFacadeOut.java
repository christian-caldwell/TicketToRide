package view.facade.client.out;

import client.ServerProxy;
import models.data.User;

public class RegisterFacadeOut {

    private ServerProxy server = null;


    public String register(User newUser) {
        return "auth token returned";
    }
}
