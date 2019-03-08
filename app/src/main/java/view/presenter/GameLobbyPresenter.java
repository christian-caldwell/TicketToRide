package view.presenter;

import com.example.cs340.tickettoride.LobbyViewActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import client.Poller;
import models.data.Game;
import models.data.Result;
import models.data.User;
import view.facade.client.ClientFacade;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.presenterInterface.IGameLobbyPresenter;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    private ArrayList<Game> gameList = new ArrayList<>();
    LobbyViewActivity gameLobby;

    @Override
    public User getPlayer() {
        ClientFacade client = new ClientFacade();
        return client.getPlayer();
    }


    public GameLobbyPresenter(LobbyViewActivity lobbyViewActivity) {
        this.gameLobby = lobbyViewActivity;
        ClientModel.create().setGameLobbyPresenter(this);
    }


    @Override
    public Result addPlayer(Game game) {
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        User user = ClientModel.create().getUser();
        Result joinResult = lobbyFacadeOut.joinGame(game, user);
        //gameLobby.updateGamePlayers(gameId);

        return joinResult;
    }

    @Override
    public void startGame() {
        User user = ClientModel.create().getUser();
        GameStartFacadeOut gameStartFacadeOut = new GameStartFacadeOut();
        gameStartFacadeOut.startGame(user.getGame().getGameName());
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
        LobbyFacadeOut lobbyFacadeOut = new LobbyFacadeOut();
        game.addPlayer(player.getUsername());
        result = lobbyFacadeOut.createGame(game, player.getUsername());
        if(result.isSuccessful()) {
            player.setHost(true);
            player.setGameJoined(game);
        }

        return result;

    }

    @Override
    public boolean onCreate() {
        try {
            Poller.startLobbyPoller();
            return true;
        }


        catch (Exception e) {
            return false;
        }
    }




    @Override
    public void update(Observable o, Object arg) {
        ClientModel client = (ClientModel) o;
        System.out.println("Server Polled by User: " + client.getUser().getUsername() );


        this.gameList = client.getChangedGameList();

        //IGameLobby gameLobby = new LobbyViewActivity();
        //gameLobby.updateGameList(this.gameList, client.getUser());
        //TODO: i think we need new LobbyViewActivity() here as second param
        new LobbyViewActivity.UpdateGameListAsyncTask(client.getUser(), gameLobby).execute(this.gameList);
    }


    // AsyncTask Class



}