package server.facade;

import models.data.Result;
import server.ServerCommands;

public class GameStartFacade {
    private ServerCommands serverCommands;

    public GameStartFacade() {
        serverCommands = new ServerCommands();
    }

    public Result startGame(String gameName) {
        Result result = new Result();
        result.setErrorMessage(serverCommands.startGame(gameName, 5));
        return result;
    }
}
