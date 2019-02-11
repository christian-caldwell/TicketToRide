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
    private ArrayList<Game> gamesActive = new ArrayList<>();
    private ArrayList<Game> gamesWaiting = new ArrayList<>();
    private ArrayList<Game> gamesLobby= new ArrayList<>();
    private ArrayList<Object> changedObjects;

    private static ClientModel singleton;

    public ClientModel() {

    }

    public static ClientModel create() {
        if (singleton == null) {
            singleton = new ClientModel();
        }
        return singleton;
    }

    public User getPlayer() {
        return this.player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public ArrayList<Game> getActiveGames() {
        return this.gamesActive;
    }

    public void setActiveGames(ArrayList<Game> games) {
        this.gamesActive = games;
    }

    public ArrayList<Game> getWaitingGames() {
        return this.gamesWaiting;
    }

    public void setWaitingGames(ArrayList<Game> games) {
        this.gamesWaiting = games;
    }

    public ArrayList<Game> getLobbyGames() {
        return this.gamesLobby;
    }

    public void setLobbyGames(ArrayList<Game> games) {
        this.gamesLobby = games;
    }

    public void addChange(Object changedMember) {
        this.changedObjects.add(changedMember);
    }

    public void clearChangeList() {
        this.changedObjects.clear();
    }

    public void addWaitingGame(Game game) {
        this.gamesWaiting.add(game);
    }

    public void addActiveGame(Game game) {
        this.gamesActive.add(game);
    }

    public void removeWaitingGame(Game game) {
        this.gamesWaiting.remove(game);
    }

    public void removeActiveGame(Game game) {
        this.gamesActive.remove(game);
    }

    public void addLobbyGame(Game game) {
        this.gamesActive.add(game);
    }

    public void removeLobbyGame(Game game) {
        this.gamesWaiting.remove(game);
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
