package server;

import models.data.Game;
import models.data.Result;
import models.data.User;

public interface IServer {
    public Result joinGame(User user, Game gameName);

    public Result createGame(String gameName, User user);

    public Result startGame(Game gameName);
    public Result register(User newUser);
    public Result login(User returnUser);
}
