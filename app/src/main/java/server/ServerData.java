package server;

import java.util.ArrayList;
import java.util.Map;

import game.Game;
import game.User;
import results.Result;

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

    public Result setGame(Game newGame) {
        Result result = new Result();
        if (availableGames.containsKey(newGame.getGameName())) {
            result.setErrorMessage("ERROR: \"" + newGame.getGameName() + "\" is taken, cannot create game.");
        }
        else {
            result.setErrorMessage("");
        }
        availableGames.put(newGame.getGameName(), newGame);
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
