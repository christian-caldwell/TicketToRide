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

    ArrayList<Game> gameList = new ArrayList<>();
    ViewFacade viewfacade = new ViewFacade();


    @Override
    public Result addPlayer(Game game) {
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        User user = ClientModel.create().getPlayer();
        Result joinResult = lobbyFacadeOut.joinGame(game, user);
        IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGamePlayers(gameId);

        ClientFacade client = new ClientFacade();
        client.joinGame(game);
        return joinResult;
    }

    @Override
    public void startGame(Game game) {
        GameStartFacadeOut gameStartFacadeOut = new GameStartFacadeOut();
        gameStartFacadeOut.startGame(game);
        ClientFacade client = new ClientFacade();
        client.joinGame(game);
    }

    @Override
    public ArrayList getGameList() {
        ClientFacade clientFacade = new ClientFacade();
        return clientFacade.getGames();
    }


    @Override
    public void createGame(Game game) {
        ClientFacade client = new ClientFacade();
        String playerName = client.getHost();
        game.addPlayer(playerName);
        game.setHostName(client.getHost());

        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        lobbyFacadeOut.createGame(game, playerName);

        client.joinGame(game);
        lobbyFacadeOut.createGame(game, playerName);

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