package server.facade;

import models.data.User;
import models.data.Result;
import server.IServer;

public class ServerFacade implements IServer {
    @Override
    public Result joinGame(String username, String gameName) {
        return null;
    }

    @Override
    public Result createGame(String gameName, String username) {
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
