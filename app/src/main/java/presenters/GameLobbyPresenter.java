package presenters;

import android.widget.Button;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import server.serverProxy;
import game.Game;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    serverProxy serverproxy = new serverProxy();


    @Override
    public void addPlayer(String gameId, String username) {
        serverproxy.joinGame(gameId, username);
        view.updateGamePlayers(gameId);
    }

    @Override
    public void startGame(String gameId) {

        serverproxy.startGame(gameId, );
    }

    @Override
    public ArrayList getGameList() {
        gameList = model.getGameList();
        view.updateGameList(gameList);
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
