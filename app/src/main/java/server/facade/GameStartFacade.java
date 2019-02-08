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

    public Result startGame(Request request) {
        Game game = new Game(request.getGame().getGameName());
        Result result = new Result();
        result.setErrorMessage(serverCommands.startGame(game));
        return result;
    }
}
