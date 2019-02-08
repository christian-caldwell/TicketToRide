package server;

import client.Client;
import models.Request;
import models.Result;
import models.User;
import presenters.ViewFacade;

public class ServerProxy implements IServer {

    ViewFacade viewFacade;
    Client client = new Client();

    public ServerProxy(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }

    @Override
    public Result joinGame(String username, String gameName) {
        Request request = new Request();
        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public Result createGame(String gameName, String username) {
        Request request = new Request();
        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public String startGame(String gameName, Integer maxPlayers) {
        return null;
    }

    @Override
    public Result register(User newUser) {
        return null;
    }

    @Override
    public Result login(User returnUser) {
        return null;
    }
}