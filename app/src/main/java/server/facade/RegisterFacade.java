package server.facade;

import models.data.Request;
import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class RegisterFacade {
    private ServerCommands serverCommands;

    public RegisterFacade() {
        serverCommands = new ServerCommands();
    }

    public Result register(String newUserName, String newPassword) {
        return serverCommands.register(newUserName, newPassword);
    }
}
