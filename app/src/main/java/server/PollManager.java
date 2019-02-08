package server;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;
import models.data.PollManagerData;
import models.data.User;

//after you get the changed data switch changed back to false,
//client proxy will switch changed to true when there are changes.
public class PollManager {

    private Map<Boolean, Game> availableGames;
    private Map<Boolean, User> users;

    public Map<Boolean, Game> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(Map<Boolean, Game> availableGames) {
        this.availableGames = availableGames;
    }

    public Map<Boolean, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Boolean, User> users) {
        this.users = users;
    }

    public PollManagerData getChanges() {
        PollManagerData pollManagerData = new PollManagerData();
        for (Map.Entry<Boolean, Game> entry : availableGames.entrySet()) {
            if (entry.getKey()) {
                pollManagerData.addGamesChanged(entry.getValue());
            }
        }
        for (Map.Entry<Boolean, User> entry : users.entrySet()) {
            if (entry.getKey()) {
                pollManagerData.addUsersChanged(entry.getValue());
            }
        }
        return pollManagerData;
    }
}
