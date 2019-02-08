package view.presenter;

import com.example.cs340.tickettoride.LobbyViewActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import models.data.Game;
import models.data.User;
import view.presenterInterface.IGameLobbyPresenter;
import view.facade.ViewFacade;
import view.activityInterface.IGameLobby;

import client.ServerProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    ServerProxy serverproxy = new ServerProxy();
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(String gameName) {
        serverproxy.joinGame(new User("???", "???"), new Game(gameName));
        IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGamePlayers(gameId);
    }

    @Override
    public void startGame(String gameName) {
        serverproxy.startGame(new Game(gameName));
    }

    @Override
    public ArrayList getGameList() {
        gameList = viewfacade.getGameList();
        //view.updateGameList(gameList);
        return gameList;
    }


    @Override
    public void createGame(String gameName) {
        serverproxy.createGame(gameName, new User("???", "???"));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}