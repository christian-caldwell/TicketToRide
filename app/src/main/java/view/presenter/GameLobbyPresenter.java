package view.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import com.example.cs340.tickettoride.LobbyViewActivity;
import com.example.cs340.tickettoride.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import client.Poller;
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
    //LobbyViewActivity gameLobby = new LobbyViewActivity();

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
    public boolean onCreate() {
        try {
            Poller.start();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        ClientModel client = (ClientModel) o;
        System.out.println("Server Polled by User: " + client.getPlayer().getUsername() );


        this.gameList = client.getChangedGameList();

        //IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGameList(this.gameList, client.getPlayer());
        new LobbyViewActivity.UpdateGameListAsyncTask(client.getPlayer()).execute(this.gameList);
    }


    // AsyncTask Class



}