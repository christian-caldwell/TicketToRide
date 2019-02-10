package client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import models.data.Game;
import models.data.User;

public class ClientModel extends Observable {
    ArrayList<Game> games;
    private static ClientModel singleton;

    public ClientModel() {}

    public static ClientModel create() {
        if (singleton == null) {
            singleton = new ClientModel();
        }
        return singleton;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
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
