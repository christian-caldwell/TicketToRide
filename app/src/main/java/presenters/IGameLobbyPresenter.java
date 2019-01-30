package presenters;

import java.util.ArrayList;

public interface IGameLobbyPresenter {
    public void addPlayer(int gameId, String username);

    public void startGame(int gameId);

    public ArrayList getGameList();

    public void logout();

    public void createGame();

    public void removePlayer(int gameId, String username);

}
