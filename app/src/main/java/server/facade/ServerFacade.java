package server.facade;

import models.data.Game;
import models.data.PollManagerData;
import models.data.User;
import models.data.Result;
import server.IServer;

public class ServerFacade implements IServer {
    @Override
    public Result joinGame(User user, Game game) {
        return null;
    }

    @Override
    public Result createGame(String gameName, User user) {
        return null;
    }

    @Override
    public String startGame(Game game) {
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

    @Override
    public PollManagerData pollServer() {
        return null;
    }
}
