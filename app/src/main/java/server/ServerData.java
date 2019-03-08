package server;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import models.data.DestinationCard;
import models.data.Enums;
import models.data.Game;
import models.data.Player;
import models.data.Result;
import models.data.Route;
import models.data.User;

public class ServerData {
    private static ServerData sServerData;
    private Map<String, Game> availableGames;
    private ArrayList<User> users;

    private Map<Enums.Color, Integer> initialTicketDeck = new HashMap<>();
    private Queue<DestinationCard> initialDestinationDeck = new ArrayDeque<>();
    private Set<Route> initialRouteSet = new HashSet<>();

//    CARD COLORS
    private static final Integer GREEN = 1;
    private static final Integer RED = 2;
    private static final Integer YELLOW = 3;
    private static final Integer BLUE = 4;
    private static final Integer ORANGE = 5;
    private static final Integer PURPLE = 6;
    private static final Integer WHITE = 7;
    private static final Integer BLACK = 8;
    private static final Integer WILD = 9;

//    PLAYER COLORS
    private static final Integer RED_PLAYER = 1;
    private static final Integer GREEN_PLAYER = 2;
    private static final Integer BLUE_PLAYER = 3;
    private static final Integer YELLOW_PLAYER = 4;
    private static final Integer BLACK_PLAYER = 5;


    public ServerData() {
        this.availableGames = new HashMap<>();
        this.users = new ArrayList<User>();
        this.users.add(new User("t","t"));
        this.users.add(new User("a", "a"));
        this.users.add(new User("s", "s"));
        this.users.add(new User("d", "d"));
        this.users.add(new User("f", "f"));
        this.users.add(new User("g", "g"));
    }

    public Map<String, Game> getAvailableGames() {
        return availableGames;
    }

    public Game getGame(String gameId) {
        return availableGames.get(gameId);
    }

 
    public Result setGame(Game newGame) {
        Result result = new Result();
        result.setGame(newGame.getGameName());
        if (availableGames.containsKey(newGame.getGameName())) {
            result.setErrorMessage("ERROR: \"" + newGame.getGameName() + "\" is taken, cannot create game.");
            result.setSuccessful(false);

        }
        else {
            availableGames.put(newGame.getGameName(), newGame);
            result.setSuccessful(true);
        }
        return result;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    public boolean isHost(User user) {
        for (String s: availableGames.keySet()) {
            if (availableGames.get(s).getPlayers().get(0).equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void removeFromGames(User user) {
        for (String s: availableGames.keySet()) {
            availableGames.get(s).getPlayers().remove(user.getUsername());
        }
    }

    public static ServerData getInstance() {
        if (sServerData == null) {
            sServerData = new ServerData();
        }
        return sServerData;
    }

    public Game findGame(String gameName) {
        return availableGames.get(gameName);
    }

    public void initializeGamePlayers(Game targetGame) {
        ArrayList<String> usernameList = targetGame.getPlayerUsernames();
        Collections.shuffle(usernameList);
        int playerCount = 0;
        for(String username: usernameList) {
            playerCount++;
            Player userPlayer = null;
            switch (playerCount) {
                case (1):
                    userPlayer = new Player(username, Enums.PlayerColor.RED);
                    break;
                case (2):
                    userPlayer = new Player(username, Enums.PlayerColor.GREEN);
                    break;
                case (3):
                    userPlayer = new Player(username, Enums.PlayerColor.BLUE);
                    break;
                case (4):
                    userPlayer = new Player(username, Enums.PlayerColor.YELLOW);
                    break;
                case (5):
                    userPlayer = new Player(username, Enums.PlayerColor.BLACK);
                    break;
                default:
                    System.out.println("Initializing more than 5 players!");
            }
            if (userPlayer != null) {
                targetGame.addPlayer(userPlayer);
            }
        }
    }

    public void initializeContainers(Game targetGame) {
//        targetGame.setTicketCardDeck(Constants.getStartingTicketDeck());
//        targetGame.setAvailableRoutes(Constants.getStartingRouteSet());
//        targetGame.setDestinationDeck(Constants.getStartingDestinationDeck());
//        targetGame.dealFaceUpTicketCards();
    }

    public void dealHands (Game targetGame) {
//        for (Player targetPlayer: targetGame.getPlayers()) {
//            for (int i = 0; i < 4; i++) {
//                TrainCard card = targetGame.dealTicketCard(0);
//                targetPlayer.addTicketToHand(card.getCardColor());
//            }
//            targetPlayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
//            targetPlayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
//            targetPlayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
//
//        }
    }
}
