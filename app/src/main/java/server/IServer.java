package server;

import results.Result;

public interface IServer {
    public Result joinGame(String username, String gameName);
    public Result createGame(String gameName, String maxPlayers, String username);
    public String startGame(String gameName,  Integer maxPlayers);
    public Result register(String username, String password);
    public Result login(String username, String password);
}
