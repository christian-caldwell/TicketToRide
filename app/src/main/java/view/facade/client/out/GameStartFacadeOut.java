package view.facade.client.out;

import client.ServerProxy;
import models.data.Game;
import models.data.Result;

public class GameStartFacadeOut {

    private ServerProxy server;

    public GameStartFacadeOut() {
        this.server = new ServerProxy();
    }

    public Result startGame(Game game) {
        return new Result();
//        return this.server.startGame(game);
    }

}
