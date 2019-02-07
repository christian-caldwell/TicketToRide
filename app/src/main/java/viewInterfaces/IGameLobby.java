package viewInterfaces;

import java.util.Map;

import models.Game;

public interface IGameLobby {
    void updateGameList(Map<String, Game> map);
    void updateGamePlayers(Game game);
    void onGameCreated();
    void onCreateGameFailed(String errorMessage);
}
