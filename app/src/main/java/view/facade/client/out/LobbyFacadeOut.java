package view.facade.client.out;

import client.ClientModel;
import client.ServerProxy;
import models.data.Request;
import models.data.Result;
import models.data.Game;
import models.data.User;
import server.PollManager;
import server.ServerCommands;

public class LobbyFacadeOut {
    private ServerProxy server;

    public LobbyFacadeOut() {
        this.server = new ServerProxy();
    }

    public Result createGame(Game game, String username) {
        Result result = server.createGame(game.getGameName(), username, game.getPlayers().size());
        return result;
    }

    public Result joinGame(Game game, User user) {
        Result result = server.joinGame(user.getUsername(), game.getGameName(), game.getPlayers().size());
        user.addGamesJoined(game);
        return result;

    }
}
