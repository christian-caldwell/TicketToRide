package view.presenterInterface;

import java.util.ArrayList;

import models.data.Game;
import models.data.Request;
import models.data.User;

public interface IGameLobbyPresenter {
    public void addPlayer(Game gameId, User user);

    public void startGame(Game game);

    public ArrayList getGameList();
    public void createGame(Game game);

    public User getPlayer();
}
