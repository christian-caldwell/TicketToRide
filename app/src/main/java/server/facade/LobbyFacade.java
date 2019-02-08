package server.facade;

import models.data.Result;
import server.ServerCommands;

public class LobbyFacade {
    private ServerCommands serverCommands;

    public LobbyFacade() {
        serverCommands = new ServerCommands();
    }

    public Result createGame(String gameName, String username) {
        return serverCommands.createGame(gameName, username);
    }

    public Result joinGame(String gameName, String username) {
        return serverCommands.joinGame(username, gameName);
    }
}
