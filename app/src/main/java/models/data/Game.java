package models.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Game {
    private boolean isStarted;
    private String gameName;
    private Integer numPlayerActions;
    private Integer currentLongestRouteValue;
    private Enums.Color currentTurnPlayer;

    private ArrayList<String> playerUsernames = new ArrayList<>();

    private ArrayList<Player> players;
    private ArrayList<ChatMessage> chatLog = new ArrayList<>();
    private Map<Enums.Color, Integer> ticketCardDeck = new HashMap<>();
    private Map<Enums.Color, Integer> ticketCardDiscard = new HashMap<>();
    private TrainCard[] faceUpTrainCards = new TrainCard[5];
    private Set<DestinationCard> destinationDeck = new HashSet<>();
    private Set<Route> availableRoutes = new HashSet<>();

    ////////////

    public Game(String gameName) {
        this.gameName = gameName;
        isStarted = false;
        numPlayerActions = 0;
        currentLongestRouteValue = 0;
        currentTurnPlayer = Enums.Color.RED;
    }

    public ArrayList<String> getPlayerUsernames() {
        return playerUsernames;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    
    public String getStatus() {
        return "";
    }

    public void addPlayerUsername(String userName) { this.playerUsernames.add(userName); }

    public Map<Enums.Color, Integer> getTicketCardDeck() {
        return ticketCardDeck;
    }

    public void setTicketCardDeck(Map<Enums.Color, Integer> ticketCardDeck) {
        this.ticketCardDeck = ticketCardDeck;
    }

    public Map<Enums.Color, Integer> getTicketCardDiscard() {
        return ticketCardDiscard;
    }

    public void setTicketCardDiscard(Map<Enums.Color, Integer> ticketCardDiscard) {
        this.ticketCardDiscard = ticketCardDiscard;
    }

    public TrainCard[] getFaceUpTrainCards() {
        return faceUpTrainCards;
    }

    public void setFaceUpTrainCard(TrainCard card, int position) {
        this.faceUpTrainCards[position] = card;
    }

    public Set<DestinationCard> getDestinationDeck() {
        return destinationDeck;
    }

    public void setDestinationDeck(Set<DestinationCard> destinationDeck) {
        this.destinationDeck = destinationDeck;
    }

    public Set<Route> getAvailableRoutes() {
        return availableRoutes;
    }

    public void setAvailableRoutes(Set<Route> availableRoutes) {
        this.availableRoutes = availableRoutes;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String username) {
        for (Player p: players) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void addInitializedPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public ArrayList<ChatMessage> getChatLog() {
        return chatLog;
    }

    public void addChat(ChatMessage chat) {
        this.chatLog.add(chat);
    }

    public Enums.Color getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public Integer getNumPlayerActions() {
        return numPlayerActions;
    }

    public void incrementNumPlayerActions() {
        ++numPlayerActions;
    }

    public void incrementCurrentTurnPlayer() {
        if (this.currentTurnPlayer == Enums.Color.RED) {
            this.currentTurnPlayer = Enums.Color.GREEN;
        }
        else if (this.currentTurnPlayer == Enums.Color.GREEN) {
            if (this.players.size() > 2) {
                this.currentTurnPlayer = Enums.Color.BLUE;
            }
            else {
                this.currentTurnPlayer = Enums.Color.RED;
            }
        }
        else if (this.currentTurnPlayer == Enums.Color.BLUE) {
            if (this.players.size() > 3) {
                this.currentTurnPlayer = Enums.Color.YELLOW;
            }
            else {
                this.currentTurnPlayer = Enums.Color.RED;
            }
        }
        else if (this.currentTurnPlayer == Enums.Color.YELLOW) {
            if (this.players.size() > 4) {
                this.currentTurnPlayer = Enums.Color.BLACK;
            }
            else {
                this.currentTurnPlayer = Enums.Color.RED;
            }
        }
        else if (this.currentTurnPlayer == Enums.Color.BLACK) {
            this.currentTurnPlayer = Enums.Color.RED;
        }
    }

    public Integer getCurrentLongestRouteValue() {
        return currentLongestRouteValue;
    }

    public void setCurrentLongestRouteValue(Integer currentLongestRouteValue) {
        this.currentLongestRouteValue = currentLongestRouteValue;
    }

    public void returnDestinationCards (DestinationCard[] returnedCards) {
        if (returnedCards != null) {
            for (DestinationCard card: returnedCards) {
                destinationDeck.add(card);
            }
        }
    }

    public Player findPlayer(String username){
        for (Player foundPlayer: this.players) {
            if (foundPlayer.getUsername().equals(username)) {
                return foundPlayer;
            }
        }
        return null;
    }

    public Game copy() {
//        Game clone = new Game();
//        /*
//        public String status;
//        private boolean isStarted;
//        private String gameName;
//        private ArrayList<String> playerUsernames = new ArrayList<>();
//
//        //new stuff for phase 2
//        private Map<Enums.Color, Integer> ticketCardDeck;
//        private Map<Enums.Color, Integer> ticketCardDiscard;
//        private ArrayList<TrainCard> faceUpTrainCards = new ArrayList<>(5);
//        private Queue<DestinationCard> destinationCards;
//        private Set<Route> availableRoutes;
//        private ArrayList<Player> players;
//        private ArrayList<ChatMessage> chatLog;
//        private Enums.Color currentTurnPlayer;
//        private Integer numPlayerActions;
//        private Integer currentLongestRouteValue;
//        */
//
//        clone.status = new String(status);
//        clone.isStarted = new Boolean(isStarted);
//        clone.gameName = new String(gameName);
//        clone.playerUsernames = new ArrayList<>(playerUsernames);
//        clone.ticketCardDeck = new HashMap<>(ticketCardDeck);
//        clone.ticketCardDiscard = new HashMap<>(ticketCardDiscard);
//        clone.faceUpTrainCards = new ArrayList<>(faceUpTrainCards);
//        clone.destinationCards = new LinkedList<>(destinationCards);
//        clone.availableRoutes = new HashSet<>(availableRoutes);
//        clone.players = new ArrayList<>(players);
//        clone.chatLog = new ArrayList<>(chatLog);
//        clone.currentTurnPlayer = currentTurnPlayer;
//        clone.numPlayerActions = new Integer(numPlayerActions);
//        clone.currentLongestRouteValue = new Integer(currentLongestRouteValue);
        ObjectMapper om = new ObjectMapper();
        String info;
        Game clone = null;
        try {
            info = om.writeValueAsString(this);
            clone = om.readValue(info, Game.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return clone;
    }

    public void hideSecrets(String viewingUser) {
        for (Player p: players) {
            if (!p.getUsername().equals(viewingUser)) {
                p.hideCards();
            }
        }
    }
}
