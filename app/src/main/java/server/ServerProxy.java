package server;

import client.Client;
import models.Request;
import models.Result;

public class ServerProxy implements IServer {

    Client client = new Client();
    @Override
    public Result joinGame(String username, String gameName) {
        Request request = new Request();
        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public Result createGame(String gameName, String maxPlayers, String username) {
        return null;
    }

    @Override
    public String startGame(String gameName, Integer maxPlayers) {
        return null;
    }

    @Override
    public Result register(String username, String password) {
        return null;
    }

    @Override
    public Result login(String username, String password) {
        return null;
    }
}