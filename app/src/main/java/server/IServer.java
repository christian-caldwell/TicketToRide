package server;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;

public interface IServer {
    public Result joinGame(String user, String gameName, Integer numPlayers);

    public Result createGame(String gameName, String username, Integer numPlayers);

    public String startGame(Game gameName);
    public Result register(User newUser);
    public Result login(User returnUser);

}
