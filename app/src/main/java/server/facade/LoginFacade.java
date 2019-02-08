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

    public Result login(Request request) {
        return serverCommands.login(request.getUser());
    }
}
