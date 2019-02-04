package server;


import results.Result;

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
    public Result register(String username, String password) {
        return null;
    }

    @Override
    public Result login(String username, String password) {
        return null;
    }
}
