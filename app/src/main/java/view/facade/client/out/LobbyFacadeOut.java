package view.facade.client.out;

import client.ServerProxy;
import models.data.Request;
import models.data.Result;
import server.ServerCommands;

public class LobbyFacadeOut {
    private ServerCommands serverCommands;

    public LobbyFacadeOut() {
        serverCommands = new ServerCommands();
    }

    public Result createGame(Request request) {
        return serverCommands.createGame(request.getGame().getGameName(), request.getUser());
    }

    public Result joinGame(Request request) {
        return serverCommands.joinGame(request.getUser(), request.getGame());
    }

}
