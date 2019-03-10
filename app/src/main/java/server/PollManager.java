package server;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;

//after you get the changed data switch changed back to false,
//client proxy will switch changed to true when there are changes.
public class PollManager {
    private static PollManager singleton;

    public PollManager() {

    }

/*
    public static PollManager create() {
        if (singleton == null){
            singleton = new PollManager();
        }

        return singleton;
    }

    private Map<Game, Boolean> availableGames = new HashMap<>();
    //private Map<String, Boolean> usernames;

    public Result getAvailableGames() {
        ArrayList<Game> games = new ArrayList<>();


        for (Map.Entry<Game, Boolean> entry : availableGames.entrySet()) {
            games.add(entry.getKey());
        }

        System.out.println("Current Complete Game List: " + games.toString());

        Result result = new Result();
        PollManagerData data = new PollManagerData();
        data.setGamesChanged(games);
        result.setPollResult(data);
        return result;
    }

    public void setGame(Game game) {
        System.out.println("Game Added to full game list. GameName: " + game.getGameName());
        this.availableGames.put(game,true);
        System.out.println("Current Complete Game List: " + this.availableGames.toString());
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
    }*/

    public Result getAvailableGames(String username) {
        ServerData dataContainer = ServerData.getInstance();
        Map<String, Game> availableGames =  dataContainer.getAvailableGames();

        ArrayList<Game> games = new ArrayList<>();


        for (Map.Entry<String, Game> entry : availableGames.entrySet()) {
            if (!entry.getValue().isStarted() || entry.getValue().getPlayerUsernames().contains(username)) {
                games.add(entry.getValue());
            }
        }
        System.out.println("Current Complete Game List: " + games.toString());

        Result result = new Result();
        PollManagerData data = new PollManagerData();
        data.setGamesChanged(games);
        result.setPollResult(data);
        return result;
    }

    public Result getRunningGame(String gameName, String userName, Integer playerActions, Integer chatSize) {
        ServerData dataContainer = ServerData.getInstance();
        Game game = dataContainer.getGame(gameName);

        if (game.getNumPlayerActions().equals(playerActions) && chatSize.equals(game.getChatLog().size())) {
            game = null;
        }
        else if (!game.getPlayerUsernames().contains(userName)) {
            System.out.println("This user doesn't belong here!!!!");
            game = null;
        }

        if (game != null) {
            game = game.copy();
            game.hideSecrets(userName);
        }
        Result result = new Result();
        result.setRunningGame(game);
        return result;
    }


}
