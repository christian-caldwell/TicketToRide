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

    public Result createGame(String gameName) {
        return serverCommands.createGame(gameName);
    }

    public Result joinGame(User user, Game game) {
        return serverCommands.joinGame(user, game);
    }

    public Result getLobbyList() {
        return serverCommands.getLobbyList();
    }

}
