package server.facade;

import models.data.Game;
import models.data.Result;
import models.data.User;
import server.ServerCommands;

public class LobbyFacade {
    private ServerCommands serverCommands;

    public LobbyFacade() {
        serverCommands = new ServerCommands();
    }

    public Result createGame(String gameName, User user) {
        return serverCommands.createGame(gameName, user);
    }

    public Result joinGame(Game game, User user) {
        return serverCommands.joinGame(user, game);
    }
}
