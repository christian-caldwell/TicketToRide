package presenters;

import java.util.ArrayList;

public interface IGameLobbyPresenter {
    public void addPlayer(String gameId, String username);

    public void startGame(String gameId);

    public ArrayList getGameList();

    public void createGame();

}
