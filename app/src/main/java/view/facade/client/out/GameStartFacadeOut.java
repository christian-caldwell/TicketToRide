package view.facade.client.out;

import client.ServerProxy;
import models.data.Game;
import models.data.Result;

public class GameStartFacadeOut {

    private ServerProxy server;

    public GameStartFacadeOut() {
        this.server = new ServerProxy();
    }

    public String startGame(Game game) {
        return this.server.startGame(game);
    }

}
