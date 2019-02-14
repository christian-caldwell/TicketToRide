package client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import models.data.Game;
import models.data.User;
import view.presenter.GameLobbyPresenter;
import view.presenter.GamePresenter;
import view.presenter.LoginPresenter;
import view.presenter.RegisterPresenter;

public class ClientModel extends Observable {
    private User player;
    private Game gameActive;
    private ArrayList<Game> lobbyGameList = new ArrayList<>();
    private ArrayList<Game> newGameList = new ArrayList<>();

    private static ClientModel singleton;

    private ClientModel() {}

    public static ClientModel create() {
        if (singleton == null) {
            singleton = new ClientModel();
        }
        return singleton;
    }

    public User getPlayer() {
        return this.player;
    }

    public void setPlayer (User player) {
        this.player = player;
    }

    public Game getActiveGame() {
        return this.gameActive;
    }

    public void setActiveGame(Game gamePlaying) {
        this.gameActive = gamePlaying;
    }

    public ArrayList<Game> getLobbyGamesList() {
        return this.lobbyGameList;
    }

    public void setLobbyGamesList(ArrayList<Game> newGameList) {
        this.lobbyGameList = newGameList;
    }

    public void addLobbyGamesList(Game game) {
        this.lobbyGameList.add(game);
    }

    public void setChangedGameList(ArrayList<Game> newGameList) {
        this.newGameList = newGameList;
    }

    public ArrayList<Game> getChangedGameList() {
        return this.newGameList;
    }

    public void addChange(Game changedGame) {
        this.newGameList.add(changedGame);
    }

    public void clearChangeList() {
        this.newGameList.clear();
    }


    public void update() {
        addObserver(new GameLobbyPresenter());
        addObserver(new GamePresenter());
        addObserver(new LoginPresenter());
        addObserver(new RegisterPresenter());
        setChanged();
        notifyObservers();
        deleteObservers();
        clearChangeList();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    ///Observable Functions


}
