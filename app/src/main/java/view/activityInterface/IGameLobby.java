package view.activityInterface;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;
import models.data.User;

public interface IGameLobby {
    void updateGameList(ArrayList<Game> lobbyGames, User user);
    void onGameCreated();
    void onCreateGameFailed(String errorMessage);
}
