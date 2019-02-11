package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.data.Game;
import models.data.User;

import models.data.Result;

public class ServerData {
    private static ServerData sServerData;
    private Map<String, Game> availableGames;
    private ArrayList<User> users;


    public ServerData() {
        this.availableGames = new HashMap<>();
        this.users = new ArrayList<User>();
        this.users.add(new User("t","t"));
    }

    public Map<String, Game> getAvailableGames() {
        return availableGames;
    }

    public Game getGame(String gameId) {
        return availableGames.get(gameId);
    }

 
    public Result setGame(Game newGame) {
        Result result = new Result();
        result.setGame(newGame);
        if (availableGames.containsKey(newGame.getGameName())) {
            result.setErrorMessage("ERROR: \"" + newGame.getGameName() + "\" is taken, cannot create game.");
            result.setSuccessful(false);

        }
        else {
            availableGames.put(newGame.getGameName(), newGame);
            result.setErrorMessage("");
            result.setSuccessful(false);
        }
        return result;
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
