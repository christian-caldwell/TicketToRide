package server;

import java.util.ArrayList;
import java.util.Map;

import models.Game;
import models.User;

public class ServerData {
    private static ServerData sServerData;
    private Map<String, Game> availableGames;
    private ArrayList<User> users;


    public Map<String, Game> getAvailableGames() {
        return availableGames;
    }

    public Game getGame(String gameId) {
        return availableGames.get(gameId);
    }

    public void setGame(Game newGame) {
        availableGames.put(newGame.getGameName(), newGame);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static ServerData getInstance() {
        if (sServerData == null) {
            sServerData = new ServerData();
        }
        return sServerData;
    }

}
