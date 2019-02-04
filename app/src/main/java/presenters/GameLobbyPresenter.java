package presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import server.ServerProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    ServerProxy serverproxy = new ServerProxy();
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(String gameId, String username) {
        serverproxy.joinGame(gameId, username);

        // Implement this one the vew interface hs been made
        //view.updateGamePlayers(gameId);
    }

    @Override
    public void startGame(String gameId) {
        serverproxy.startGame(gameId, 5);
    }

    @Override
    public ArrayList getGameList() {
        gameList = viewfacade.getGameList();
        //view.updateGameList(gameList);
        return gameList;
    }


    @Override
    public void createGame() {
        serverproxy.createGame("game name", "4", "test");
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
