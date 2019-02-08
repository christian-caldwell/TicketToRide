package client;

import models.data.Request;
import models.data.Result;
import models.data.User;
import server.IServer;

public class ServerProxy implements IServer {

    ClientCommunicator client = new ClientCommunicator();
    @Override
    public Result joinGame(String username, String gameName) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public Result createGame(String gameName, String username) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
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