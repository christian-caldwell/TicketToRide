package view.facade.client.out;

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

    public Result createGame(Request request) {
        this.server = new ServerProxy();
        return server.createGame(request.getGame().getGameName(), request.getUser());
    }

    public Result joinGame(Game game, User user) {
        this.server = new ServerProxy();
        return server.joinGame(user, game);
    }
}
