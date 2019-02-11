package view.presenter;

import com.example.cs340.tickettoride.LobbyViewActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import client.ClientModel;
import models.data.Game;
import models.data.User;
import models.data.Result;
import view.facade.client.ClientFacade;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.presenterInterface.IGameLobbyPresenter;
import view.facade.ViewFacade;
import view.activityInterface.IGameLobby;

import client.ServerProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<Game> gameList;
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public void addPlayer(Game game, User user) {
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        Result joinResult = lobbyFacadeOut.joinGame(game, user);
        IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGamePlayers(gameId);

        ClientFacade client = new ClientFacade();
        client.joinGame(game);
    }

    @Override
    public void startGame(Game game) {
        GameStartFacadeOut gameStartFacadeOut = new GameStartFacadeOut();
        gameStartFacadeOut.startGame(game);
        ClientFacade client = new ClientFacade();
        client.startGame(game);
    }

    @Override
    public ArrayList getGameList() {

        return gameList;
    }


    @Override
    public void createGame(Game game) {
        Game newGame = new Game(gameName);
        ClientFacade client = new ClientFacade();
        String playerName = client.getHost();
        newGame.addPlayer(playerName);
        newGame.setHostName(client.getHost());
        User user = new User(playerName, "");

        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        ClientFacade client = new ClientFacade();
        client.waitingForGame(game);
        lobbyFacadeOut.createGame(game);

        client.joinGame(newGame);
        return lobbyFacadeOut.createGame(newGame.getGameName(), user).getGame();

    }

    @Override
    public void update(Observable o, Object arg) {
        ClientModel client = (ClientModel) o;
        ArrayList<Object> updatedObject = client.getChangedObjects();

        if (updatedObject.isEmpty()) {
            //No update or server error
        }
        else {
            for (Object currUpdatedObject : this.gameList) {
                if (currUpdatedObject.getClass() == Game.class) {
                    Game currUpdatedGame = (Game) currUpdatedObject;
                    for (Game currentLobbyGame : this.gameList) {
                        if (currentLobbyGame.getGameName().compareTo(currUpdatedGame.getGameName()) == 0) {
                            currentLobbyGame = currUpdatedGame;
                        }

                    }
                }
            }
        }
    }
}