package models.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Game {
    public String status;
    private boolean isStarted;
    private String gameName;
    private ArrayList<String> playerUsernames = new ArrayList<>();

    //new stuff for phase 2
    private Map<Enums.Color, Integer> ticketCardDeck;
    private Map<Enums.Color, Integer> ticketCardDiscard;
    private ArrayList<TrainCard> faceUpTrainCards = new ArrayList<>(5);
    private Queue<DestinationCard> destinationCards;
    private Set<Route> availableRoutes;
    private ArrayList<Player> players;
    private ArrayList<ChatMessage> chatLog;
    private Enums.Color currentTurnPlayer;
    private Integer numPlayerActions;
    private Integer currentLongestRouteValue;
    ////////////

    public Game() {
        isStarted = false;
        initRoutes();
        initTicketContainers();
        initDestinationCards();
        initPlayers();
        initChatLog();
    }

    public Game(String gameName) {
        this.gameName = gameName;
        isStarted = false;
        initRoutes();
        initTicketContainers();
        initDestinationCards();
        initPlayers();
        initChatLog();
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

    public void addPlayer(String userName) { this.playerUsernames.add(userName); }


    private void initRoutes() {

    }
    private void initTicketContainers() {

    }
    private void initDestinationCards() {

    }
    private void initPlayers() {

    }

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

    public ArrayList<TrainCard> getFaceUpTrainCards() {
        return faceUpTrainCards;
    }

    public void setFaceUpTrainCards(ArrayList<TrainCard> faceUpTrainCards) {
        this.faceUpTrainCards = faceUpTrainCards;
    }

    public Queue<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(Queue<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
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

    public ArrayList<String> getPlayerNames() {
        return playerUsernames;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<ChatMessage> getChatLog() {
        return chatLog;
    }

    public void setChatLog(ArrayList<ChatMessage> chatLog) {
        this.chatLog = chatLog;
    }

    public Enums.Color getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public void setCurrentTurnPlayer(Enums.Color currentTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
    }

    public Integer getNumPlayerActions() {
        return numPlayerActions;
    }

    public void setNumPlayerActions(Integer numPlayerActions) {
        this.numPlayerActions = numPlayerActions;
    }

    public Integer getCurrentLongestRouteValue() {
        return currentLongestRouteValue;
    }

    public void setCurrentLongestRouteValue(Integer currentLongestRouteValue) {
        this.currentLongestRouteValue = currentLongestRouteValue;
    }

    private void initChatLog() {

    }

    public Game copy() {
        Game clone = new Game();
        /*
        public String status;
        private boolean isStarted;
        private String gameName;
        private ArrayList<String> playerUsernames = new ArrayList<>();

        //new stuff for phase 2
        private Map<Enums.Color, Integer> ticketCardDeck;
        private Map<Enums.Color, Integer> ticketCardDiscard;
        private ArrayList<TrainCard> faceUpTrainCards = new ArrayList<>(5);
        private Queue<DestinationCard> destinationCards;
        private Set<Route> availableRoutes;
        private ArrayList<Player> players;
        private ArrayList<ChatMessage> chatLog;
        private Enums.Color currentTurnPlayer;
        private Integer numPlayerActions;
        private Integer currentLongestRouteValue;
        */

        clone.status = new String(status);
        clone.isStarted = new Boolean(isStarted);
        clone.gameName = new String(gameName);
        clone.playerUsernames = new ArrayList<>(playerUsernames);
        clone.ticketCardDeck = new HashMap<>(ticketCardDeck);
        clone.ticketCardDiscard = new HashMap<>(ticketCardDiscard);
        clone.faceUpTrainCards = new ArrayList<>(faceUpTrainCards);
        clone.destinationCards = new LinkedList<>(destinationCards);
        clone.availableRoutes = new HashSet<>(availableRoutes);
        clone.players = new ArrayList<>(players);
        clone.chatLog = new ArrayList<>(chatLog);
        clone.currentTurnPlayer = currentTurnPlayer;
        clone.numPlayerActions = new Integer(numPlayerActions);
        clone.currentLongestRouteValue = new Integer(currentLongestRouteValue);

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
