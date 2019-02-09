package server;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;

//after you get the changed data switch changed back to false,
//client proxy will switch changed to true when there are changes.
public class PollManager {

    private Map<Game, Boolean> availableGames;
    private Map<User, Boolean> users;

    public Map<Game, Boolean> getAvailableGames() {
        return availableGames;
    }

    public void setGame(Game game) {
        this.availableGames.put(game,true);
    }

    public Map<User, Boolean> getUsers() {
        return users;
    }

    public void setUser(User user) {
        this.users.put(user, true);
    }

    public PollManagerData getChanges() {
        PollManagerData pollManagerData = new PollManagerData();
        for (Map.Entry<Game, Boolean> entry : availableGames.entrySet()) {
            if (entry.getValue()) {
                pollManagerData.addGamesChanged(entry.getKey());
                entry.setValue(false);
            }
        }
        for (Map.Entry<User, Boolean> entry : users.entrySet()) {
            if (entry.getValue()) {
                pollManagerData.addUsersChanged(entry.getKey());
                entry.setValue(false);
            }
        }

        Result output = new Result();
        output.setPollResult(pollManagerData);
        return pollManagerData;
    }
}
