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
    private ArrayList<Game> gamesLobby = new ArrayList<>();
    private ArrayList<Object> changedObjects = new ArrayList<>();

    private static ClientModel singleton;

    public ClientModel() {}

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

    public ArrayList<Game> getLobbyGames() {
        return this.gamesLobby;
    }

    public ArrayList<Object> getChangedObjects() {
        return this.changedObjects;
    }

    public void addLobbyGames(Game game) {
        this.gamesLobby.add(game);
    }

    public void addChange(Object changedMember) {
        this.changedObjects.add(changedMember);
    }

    public void clearChangeList() {
        this.changedObjects.clear();
    }

    public void addLobbyGame(Game game) {
        this.gamesLobby.add(game);
    }

    public void removeLobbyGame(Game game) {
        this.gamesLobby.remove(game);
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
