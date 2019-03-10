package server.facade;

import models.data.Result;
import server.ServerCommands;

public class GameStartFacade {
    private ServerCommands serverCommands;

    public GameStartFacade() {
        serverCommands = new ServerCommands();
    }

    public Result startGame(String gameName) {
        return serverCommands.startGame(gameName);
    }

    public Result requestGame(String gameName) {
        return serverCommands.requestGame(gameName);
    }
}
