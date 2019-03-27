package server;

import java.util.ArrayList;
import java.util.Map;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;

/**
 * PollManager is responsible for querying and returning the information for which Clients will
 * regularly poll
 */
public class PollManager {

    /**
     * PollManager is a singleton, this is the only instance
     */
    private static PollManager singleton;

    /**
     * Since PollManager is a singleton, the constructor should never be called, and doesn't do much
     */
    public PollManager() {

    }

    /**
     * @param username  The username of the user polling for the list of games
     * @return          A result object containing the list of Games available in the GameLobby
     *
     * @Pre  username is a valid username registered in the server
     * @Post The saved data on the server remains unchanged
     * @Post The list contained in result contains every game on the server that has not yet started
     *       and the game which the user indicated by username has joined (even if that game has
     *       started).
     */
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

    /**
     * @param gameName      The name of the game being polled for
     * @param userName      The username of the user polling for the game
     * @param playerActions The number of turns of which the user has record
     * @param chatSize      The number of chat messages the user has stored
     * @return              A result containing a copy of the game with the name indicated, but
     *                      with the specific cards of other users hidden.  If the game stored on
     *                      the server has the same number of playerActions and the same chatSize
     *                      then the returned game is set to null to minimize traffic
     *
     * @Pre  gameName is a valid name of a game stored in the server
     * @Pre  userName is a valid username of a user who has joined the game with the indicated name
     * @Pre  playerActions >= 0 and <= to the number of playerActions stored in the game with the
     *       indicated name
     * @Pre  chatSize >= 0 and <= to the number of chat messages stored in the game with the
     *       indicated name
     * @Post The saved data on the server remains unchanged
     * @Post The game contained in result is null if playerActions and chatSize indicate that the
     *       client's copy of the indicated game is still up-to-date, otherwise the game contained
     *       in result is an exact copy of the server's copy of the game with the indicated name
     *       with the exception of the face-down cards of the other players
     */
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
