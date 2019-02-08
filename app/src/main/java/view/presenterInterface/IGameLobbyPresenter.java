package view.presenterInterface;

import java.util.ArrayList;

public interface IGameLobbyPresenter{
    public void addPlayer(String gameId);

    public void startGame(String gameId);

    public ArrayList getGameList();

    public void createGame(String gameName);

}
