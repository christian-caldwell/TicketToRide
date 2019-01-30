package presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import server.serverProxy;
import presenters.ViewFacade;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    serverProxy serverproxy = new serverProxy();
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(String gameId, String username) {
        serverproxy.joinGame(gameId, username);
        view.updateGamePlayers(gameId);
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
        serverproxy.createGame();
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
