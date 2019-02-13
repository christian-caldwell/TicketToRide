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

    @Override
    public User getPlayer() {
        ClientFacade client = new ClientFacade();
        return client.getPlayer();
    }




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
        ClientFacade client = new ClientFacade();
        return client.getGames();
    }


    @Override
    public Result createGame(Game game) {
        Result result = new Result();
        if(game.getGameName().equals("")) {
            result.setErrorMessage("name invalid...");
            result.setSuccessful(false);
            return result;
        }

        ClientFacade client = new ClientFacade();
        User player = client.getPlayer();
        player.setGameJoined(game);
        player.setHost(true);
        game.addPlayer(player.getUsername());
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        result = lobbyFacadeOut.createGame(game, player.getUsername());
        client.joinGame(game);

        return result;

    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Server Polled");
        ClientModel client = (ClientModel) o;
        ArrayList<Object> updatedObjectList = client.getChangedObjects();
        this.gameList = client.getLobbyGames();

        System.out.println("Lobby Updated");

        for (Object currUpdatedObject : updatedObjectList) {
            Game currUpdatedGame = (Game) currUpdatedObject;

            /*if (!currUpdatedGame.isVisibleInLobby()) {
                System.out.println("Game Removed From Lobby: " + currUpdatedGame.getGameName());
                removeLobbyGame(currUpdatedGame);
            }
            else {*/
            boolean found = replaceLobbyGame(currUpdatedGame);
            if (!found) {
                System.out.println("Game Updated In Lobby: " + currUpdatedGame.getGameName());
                gameList.add(currUpdatedGame);
            }
            //}
        }

        IGameLobby gameLobby = new LobbyViewActivity();
        gameLobby.updateGameList(this.gameList);
    }


    private boolean replaceLobbyGame (Game updatedGame) {
        for (Game currLobbyGame : this.gameList) {
            if (updatedGame.getGameName().compareTo(currLobbyGame.getGameName()) == 0) {
                System.out.println("Game Updated In Lobby: " + currLobbyGame.getGameName());
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