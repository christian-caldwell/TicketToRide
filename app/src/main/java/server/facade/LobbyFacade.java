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

    public Result createGame(String gameName, String username, Integer numPlayers) {

        Result result = serverCommands.createGame(gameName, username, numPlayers);
        if (result.isSuccessful()) {
            result = serverCommands.joinGame(username,gameName, numPlayers);
        }
        return result;
    }

    public Result joinGame(String username, String gameName, Integer numPlayers) {
        return serverCommands.joinGame(username, gameName, numPlayers);
    }

}
