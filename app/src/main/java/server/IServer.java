package server;

import models.data.Result;
import models.data.User;

public interface IServer {
    public Result joinGame(String username, String gameName);

    public Result createGame(String gameName, String username);

    public String startGame(String gameName,  Integer maxPlayers);
    public Result register(User newUser);
    public Result login(User returnUser);
}
