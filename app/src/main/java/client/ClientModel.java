package client;

import java.util.ArrayList;
import java.util.Observable;

import models.data.Game;
import models.data.User;

public class ClientModel extends Observable {
    ArrayList<Game> games;
    ArrayList<User> users;

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
