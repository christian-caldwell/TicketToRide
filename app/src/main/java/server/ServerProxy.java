package server;

import results.Result;

public class ServerProxy implements IServer {

    @Override
    public Result joinGame(String username, String gameName) {
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