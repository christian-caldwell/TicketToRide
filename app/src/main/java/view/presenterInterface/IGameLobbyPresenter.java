package view.presenterInterface;

import java.util.ArrayList;

import models.data.Game;
import models.data.Result;
import models.data.User;

public interface IGameLobbyPresenter {
    public Result addPlayer(Game gameId);

    public void startGame(Game game);

    public ArrayList getGameList();
    public void createGame(Game game);

    public User getPlayer();
}
