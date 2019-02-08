package server.facade;

import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class LoginFacade {
    private ServerCommands serverCommands;

    public LoginFacade() {
        serverCommands = new ServerCommands();
    }

    public Result login(User user) {
        return serverCommands.login(user);
    }
}
