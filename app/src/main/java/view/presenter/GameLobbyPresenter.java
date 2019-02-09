package view.presenter;

import com.example.cs340.tickettoride.LobbyViewActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import models.data.Game;
import models.data.User;
import models.data.Result;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.presenterInterface.IGameLobbyPresenter;
import view.facade.ViewFacade;
import view.activityInterface.IGameLobby;

import client.ServerProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<String> gameList;
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(Game game, User user) {
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        Result joinResult = lobbyFacadeOut.joinGame(game, user);
        IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGamePlayers(gameId);
    }

    @Override
    public void startGame(Game game) {
        GameStartFacadeOut gameStartFacadeOut = new GameStartFacadeOut();
        gameStartFacadeOut.startGame(game);
    }

    @Override
    public ArrayList getGameList() {
        //view.updateGameList(gameList);
        return gameList;
    }


    @Override
    public Game createGame(Game game, User user) {
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        return lobbyFacadeOut.createGame(game.getGameName(), user).getGame();

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}