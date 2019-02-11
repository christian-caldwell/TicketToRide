package server.facade;

import models.data.Game;
import models.data.Request;
import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class LobbyFacade {
    private ServerCommands serverCommands;

    public LobbyFacade() {
        serverCommands = new ServerCommands();
    }

    public Result createGame(Request request) {
        return serverCommands.createGame(request.getGame().getGameName(), request.getUser());
    }

    public Result joinGame(Request request) {
        return serverCommands.joinGame(request.getUser(), request.getGame());
    }

    public Result getLobbyList() {
        return serverCommands.getLobbyList();
    }

}
