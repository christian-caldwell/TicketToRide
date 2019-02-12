package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;

//after you get the changed data switch changed back to false,
//client proxy will switch changed to true when there are changes.
public class PollManager {

    private Map<Game, Boolean> availableGames = new HashMap<>();
    //private Map<String, Boolean> usernames;

    public ArrayList<Game> getAvailableGames() {
        ArrayList<Game> games = new ArrayList<>();

        for (Map.Entry<Game, Boolean> entry : availableGames.entrySet()) {
            games.add(entry.getKey());
        }
        return games;
    }

    public void setGame(Game game) {
        this.availableGames.put(game,true);
    }

    public PollManagerData getChanges() {
        PollManagerData pollManagerData = new PollManagerData();
        for (Map.Entry<Game, Boolean> entry : availableGames.entrySet()) {
            if (entry.getValue()) {
                pollManagerData.addGamesChanged(entry.getKey());
                entry.setValue(false);
            }
        }
        Result output = new Result();
        output.setPollResult(pollManagerData);
        return pollManagerData;
    }
}
