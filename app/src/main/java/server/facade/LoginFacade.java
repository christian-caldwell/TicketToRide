package server.facade;

import models.data.Request;
import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class LoginFacade {
    private ServerCommands serverCommands;

    public LoginFacade() {
        serverCommands = new ServerCommands();
    }

    public Result login(String username, String password) {
        return serverCommands.login(new User(username, password));
    }
}
