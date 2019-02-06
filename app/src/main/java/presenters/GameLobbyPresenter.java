package presenters;

import com.example.cs340.tickettoride.LobbyViewActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import server.ServerProxy;
import viewInterfaces.IGameLobby;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    ServerProxy serverproxy = new ServerProxy();
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(String gameId, String username) {
        serverproxy.joinGame(gameId, username);
        IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGamePlayers(gameId);
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
