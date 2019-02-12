package server.facade;

import models.data.Game;
import models.data.Request;
import models.data.Result;
import server.ServerCommands;

public class GameStartFacade {
    private ServerCommands serverCommands;

    public GameStartFacade() {
        serverCommands = new ServerCommands();
    }

    public Result startGame(Game game) {
        Result result = new Result();
        result.setErrorMessage(serverCommands.startGame(game));
        return result;
    }
}
