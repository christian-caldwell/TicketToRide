package server;

import models.data.Result;
import models.data.User;

public interface IServer {
    public Result joinGame(String user, String gameName, Integer numPlayers);

    public Result createGame(String gameName, String username, Integer numPlayers);

    public Result startGame(String gameName);
    public Result register(User newUser);
    public Result login(User returnUser);

}
