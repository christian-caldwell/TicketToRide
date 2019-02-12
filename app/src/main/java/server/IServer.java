package server;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;

public interface IServer {
    public Result joinGame(String user, Game gameName);

    public Result createGame(String gameName);

    public String startGame(Game gameName);
    public Result register(String newUser, String password);
    public Result login(User returnUser);

    public PollManagerData pollServer();
}
