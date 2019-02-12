package view.activityInterface;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;


public interface IGameLobby {
    void updateGameList(ArrayList<Game> gameLobby);
    void onGameCreated();
    void onCreateGameFailed(String errorMessage);
}
