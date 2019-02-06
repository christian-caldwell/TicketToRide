package server;

import models.Result;
import models.User;

public interface IServer {
    public Result joinGame(String username, String gameName);
    public Result createGame(String gameName, String maxPlayers, String username);
    public String startGame(String gameName,  Integer maxPlayers);
    public Result register(User newUser);
    public Result login(User returnUser);
}
