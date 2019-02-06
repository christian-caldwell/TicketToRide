package viewInterfaces;

import java.util.Map;

import game.Game;

public interface IGameLobby {
    void updateGameList(Map<String, Game> map);
    void updateGamePlayers(Game game);
}
