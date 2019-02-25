package view.facade.client.out;

import client.ServerProxy;

public class GameStartFacadeOut {

    private ServerProxy server;

    public GameStartFacadeOut() {
        this.server = new ServerProxy();
    }

    public Boolean startGame(String gameName) {
        return this.server.startGame(gameName).isSuccessful();
    }

}
