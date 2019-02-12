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

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    ArrayList<Game> gameList = new ArrayList<>();
    ViewFacade viewfacade = new ViewFacade();

    @Override
    public boolean isUserHosting() {
        ClientFacade client = new ClientFacade();
        return client.getPlayer().isHost();
    }

    @Override
    public ArrayList<String> getHostedGamePlayers() {
        ClientFacade client = new ClientFacade();
        if (client.getPlayer().isHost()) {
            return client.getPlayer().getActiveGame().getPlayers();
        }
        else {
            //player is not a host
            return null;
        }
    }

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
        client.joinGame(game);
    }

    @Override
    public ArrayList getGameList() {
        LobbyFacadeOut lobbyFacade = new LobbyFacadeOut();
        Result result = lobbyFacade.getLobbyList();

        this.gameList = result.getLobbyList();
        ClientFacade client = new ClientFacade();
        client.setLobbyList(this.getGameList());
        return gameList;
    }


    @Override
    public void createGame(Game game) {
        ClientFacade client = new ClientFacade();
        String playerName = client.getPlayer().getUsername();
        game.addPlayer(playerName);
        game.setHostName(playerName);
        User user = new User(playerName, "");
        user.setHost(true);


        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        lobbyFacadeOut.createGame(game);

        client.joinGame(game);
        lobbyFacadeOut.createGame(game);

    }

    @Override
    public void update(Observable o, Object arg) {
        ClientModel client = (ClientModel) o;
        ArrayList<Object> updatedObjectList = client.getChangedObjects();
        this.gameList = client.getLobbyGames();


        for (Object currUpdatedObject : updatedObjectList) {
            Game currUpdatedGame = (Game) currUpdatedObject;

            if (!currUpdatedGame.isVisibleInLobby()) {
                removeLobbyGame(currUpdatedGame);
            }
            else {
                boolean found = replaceLobbyGame(currUpdatedGame);
                if (!found) {
                    gameList.add(currUpdatedGame);
                }
            }
        }

    }


    private boolean replaceLobbyGame (Game updatedGame) {
        for (Game currLobbyGame : this.gameList) {
            if (updatedGame.getGameName().compareTo(currLobbyGame.getGameName()) == 0) {
                this.gameList.remove(currLobbyGame);
                this.gameList.add(updatedGame);
                return true;
            }
        }
        return false;
    }

    private void removeLobbyGame (Game removedGame) {
        String removedGameName = removedGame.getGameName();
        for (Game lobbyGame : this.gameList) {
            if (lobbyGame.getGameName().compareTo(removedGameName) == 0) {
                this.gameList.remove(lobbyGame);
            }
        }
    }
}