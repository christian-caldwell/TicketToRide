package view.facade.client.out;

import client.ServerProxy;
import models.data.Result;
import models.data.User;

public class LoginFacadeOut {
    private ServerProxy server = null;

    public LoginFacadeOut () {
        this.server = new ServerProxy();
    }

    public Result login(User newUser) {

        return this.server.login(newUser);
    }
}
