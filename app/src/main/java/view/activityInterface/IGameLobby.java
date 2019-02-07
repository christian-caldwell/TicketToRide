package view.activityInterface;

import java.util.Map;

import models.data.Game;

public interface IGameLobby {
    void updateGameList(Map<String, Game> map);
    void updateGamePlayers(Game game);
    void onGameCreated();
    void onCreateGameFailed(String errorMessage);
}
