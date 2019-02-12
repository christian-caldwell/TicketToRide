package view.facade.client.out;

import java.util.ArrayList;

import client.ClientModel;
import client.ServerProxy;
import models.data.Request;
import models.data.Result;
import models.data.Game;
import models.data.User;
import server.PollManager;
import server.ServerCommands;
import view.facade.client.ClientFacade;

public class LobbyFacadeOut {
    private ServerProxy server;

    public LobbyFacadeOut() {
        this.server = new ServerProxy();
    }

    public Result createGame(Game game) {
        Result result = server.createGame(game.getGameName());
        if (result.isSuccessful()) {
            result = server.joinGame(ClientModel.create().getPlayer().getUsername(),game);
        }
        return result;
    }

    public Result joinGame(Game game, User user) {
        Result result = server.joinGame(user.getUsername(), game);
        ClientFacade client = new ClientFacade();
        client.joinGame(game);
        return result;
    }

    public Result getLobbyList() {
        return server.getLobbyList();
    }
}
