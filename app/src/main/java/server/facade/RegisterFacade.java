package server.facade;

import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class RegisterFacade {
    private ServerCommands serverCommands;

    public RegisterFacade() {
        serverCommands = new ServerCommands();
    }

    public Result register(User user) {
        return serverCommands.register(user);
    }
}
