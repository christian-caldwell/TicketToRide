package view.facade.client.out;

import java.util.ArrayList;

import client.ServerProxy;
import models.data.Request;
import models.data.Result;
import models.data.Game;
import models.data.User;
import server.ServerCommands;

public class LobbyFacadeOut {
    private ServerProxy server;
    public LobbyFacadeOut() {
        this.server = new ServerProxy();
    }

    public Result createGame(String gameName, User user) {
        return server.createGame(gameName, user);
    }

    public Result joinGame(Game game, User user) {
        return server.joinGame(user, game);
    }
    public Result getLobbyList() {
        return server.getLobbyList();
    }
}
