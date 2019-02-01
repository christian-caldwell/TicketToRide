package presenters;

import java.util.ArrayList;
<<<<<<< HEAD:testingPurposes/src/main/java/com/example/testingpurposes/presenters/GameLobbyPresenter.java

import com.example.testingpurposes.server.serverProxy;
import com.example.testingpurposes.game.Game;
=======
import java.util.Observable;
import java.util.Observer;
import server.serverProxy;
>>>>>>> master:app/src/main/java/presenters/GameLobbyPresenter.java

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
