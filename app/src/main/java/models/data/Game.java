package models.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import server.ServerData;

public class Game {
    private boolean isStarted;
    private String gameName;
    private Integer numPlayerActions;
    private Integer currentLongestRouteValue;
    private Integer currentTurnPlayer;

    private ArrayList<String> playerUsernames = new ArrayList<>();

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<ChatMessage> chatLog = new ArrayList<>();
    private Map<Integer, Integer> ticketCardDeck = new HashMap<>();
    private Map<Integer, Integer> ticketCardDiscard = new HashMap<>();
    private TrainCard[] faceUpTrainCards = new TrainCard[5];
    private ArrayList<DestinationCard> destinationDeck = new ArrayList<>();
    private Set<Route> availableRoutes = new HashSet<>();

    private ServerData serverData = ServerData.getInstance();

    ////////////

    public Game(String gameName) {
        this.gameName = gameName;
        isStarted = false;
        numPlayerActions = 0;
        currentLongestRouteValue = 0;
        currentTurnPlayer = serverData.RED;
        faceUpTrainCards = new TrainCard[]{new TrainCard(0),
                                           new TrainCard(0),
                                           new TrainCard(0),
                                           new TrainCard(0),
                                           new TrainCard(0)};
    }

    public Game() {

    }


    public boolean isStarted() {
        return isStarted;
    }
    public void setStarted(boolean started) {
        isStarted = started;
    }
    //////////////////////////////////////////////////////////////////////////////

    public String getGameName() {
        return gameName;
    }
//////////////////////////////////////////////////////////////////////////////

    public ArrayList<String> getPlayerUsernames() {
        return playerUsernames;
    }
    public void addPlayerUsername(String userName) { this.playerUsernames.add(userName); }
//////////////////////////////////////////////////////////////////////////////

    public Map<Integer, Integer> getTicketCardDeck() {
        return ticketCardDeck;
    }

    public void setTicketCardDeck(Map<Integer, Integer> ticketCardDeck) {
        this.ticketCardDeck = ticketCardDeck;
    }

    public TrainCard dealTicketCard(int position) {
        TrainCard dealtCard = new TrainCard(0);
        if (position == 0) {
            Integer dealtCardColor = null;
            int remainingCardCount = 0;

            do {
                Random generator = new Random();
                dealtCardColor = generator.nextInt(9);
                dealtCard.setCardColor(dealtCardColor);
                remainingCardCount = this.ticketCardDeck.get(dealtCardColor);
            } while (remainingCardCount == 0);

            this.ticketCardDeck.put(dealtCardColor,remainingCardCount - 1);
        }
        else if (position < 6) {
            dealtCard = this.faceUpTrainCards[position - 1];
            this.faceUpTrainCards[position - 1] = dealTicketCard(0);

            int wildCardCount = 0;
            for (TrainCard card :this.faceUpTrainCards) {
                if (card.getCardColor().equals(serverData.WILD)) {
                    wildCardCount++;
                }
            }

            if (wildCardCount >= 3) {
                reshuffleTicketDecks();
            }
        }

        return dealtCard;
    }
//////////////////////////////////////////////////////////////////////////////

    public Map<Integer, Integer> getTicketCardDiscard() {
        return ticketCardDiscard;
    }
    public void setTicketCardDiscard(Map<Integer, Integer> ticketCardDiscard) {
        this.ticketCardDiscard = ticketCardDiscard;
    }
    public void reshuffleTicketDecks() {
        for (TrainCard card:this.faceUpTrainCards) {
            this.ticketCardDiscard.put(card.getCardColor(), ticketCardDeck.get(card.getCardColor()) + 1);
        }
        for (Integer color: this.ticketCardDiscard.keySet()){
            this.ticketCardDeck.put(color,this.ticketCardDeck.get(color) + this.ticketCardDiscard.get(color));
        }

        dealFaceUpTicketCards();
    }
//////////////////////////////////////////////////////////////////////////////

    public TrainCard[] getFaceUpTrainCards() {
        return faceUpTrainCards;
    }
    public void setFaceUpTrainCard(TrainCard card, int position) {
        this.faceUpTrainCards[position] = card;
    }
    public void dealFaceUpTicketCards() {
        for(int i  = 0; i < 5; i++) {
            TrainCard card = this.dealTicketCard(0);
            this.setFaceUpTrainCard(card, i);
        }
    }
//////////////////////////////////////////////////////////////////////////////

    public ArrayList<DestinationCard> getDestinationDeck() {
        return destinationDeck;
    }
    public void setDestinationDeck(ArrayList<DestinationCard> destinationDeck) {
        this.destinationDeck = destinationDeck;
    }
    public void returnDestinationCards (DestinationCard[] returnedCards) {
        if (returnedCards != null) {
            for(DestinationCard card: returnedCards) {
                destinationDeck.add(card);
            }
        }
    }
    public DestinationCard dealDestinationCard() {
        Collections.shuffle(this.destinationDeck);
        DestinationCard card = this.destinationDeck.get(0);
        this.destinationDeck.remove(card);
        return card;
    }
    //////////////////////////////////////////////////////////////////////////////

    public Set<Route> getAvailableRoutes() {
        return availableRoutes;
    }
    public void setAvailableRoutes(Set<Route> availableRoutes) {
        this.availableRoutes = availableRoutes;
    }
    public boolean purchaseRoute(String playerName, Route purchasedRoute, Integer numberOfWilds) {
        if (!this.availableRoutes.contains(purchasedRoute)) {
            return false;
        }

        for (Player player : this.players){
            if (player.getUsername().equals(playerName)) {
                player.decrementTrainsRemaining(purchasedRoute.getLength());
                Integer color = purchasedRoute.getCardColor();
                for (int i = 0; i < purchasedRoute.getLength() - numberOfWilds; i++) {
                    this.ticketCardDiscard.put(color,this.ticketCardDeck.get(color)+1);
                    player.removeTicketFromHand(color);
                }

                for (int i = 0; i < numberOfWilds; i++) {
                    this.ticketCardDiscard.put(serverData.WILD,this.ticketCardDeck.get(serverData.WILD)+1);
                    player.removeTicketFromHand(serverData.WILD);
                }

                player.incrementScore(purchasedRoute.getPoints());
                player.addRoute(purchasedRoute);
                this.availableRoutes.remove(purchasedRoute);
                return true;
            }
        }
        return false;
    }

//////////////////////////////////////////////////////////////////////////////

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
    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }
    public Player findPlayer(String username){
        for (Player foundPlayer: this.players) {
            if (foundPlayer.getUsername().equals(username)) {
                return foundPlayer;
            }
        }
        return null;
    }
//////////////////////////////////////////////////////////////////////////////

    public ArrayList<ChatMessage> getChatLog() {
        return chatLog;
    }
    public void addChat(ChatMessage chat) {
        this.chatLog.add(chat);
    }
//////////////////////////////////////////////////////////////////////////////

    public Integer getNumPlayerActions() {
        return numPlayerActions;
    }
    public void incrementNumPlayerActions() {
        ++numPlayerActions;
    }
//////////////////////////////////////////////////////////////////////////////

    public Integer getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }
    public void incrementCurrentTurnPlayer() {
        if (this.currentTurnPlayer.equals(serverData.RED)) {
            this.currentTurnPlayer = serverData.GREEN;
        }
        else if (this.currentTurnPlayer.equals(serverData.GREEN)) {
            if (this.players.size() > 2) {
                this.currentTurnPlayer = serverData.BLUE;
            }
            else {
                this.currentTurnPlayer = serverData.RED;
            }
        }
        else if (this.currentTurnPlayer.equals(serverData.BLUE)) {
            if (this.players.size() > 3) {
                this.currentTurnPlayer = serverData.YELLOW;
            }
            else {
                this.currentTurnPlayer = serverData.RED;
            }
        }
        else if (this.currentTurnPlayer.equals(serverData.YELLOW)) {
            if (this.players.size() > 4) {
                this.currentTurnPlayer = serverData.BLACK;
            }
            else {
                this.currentTurnPlayer = serverData.RED;
            }
        }
        else if (this.currentTurnPlayer.equals(serverData.BLACK)) {
            this.currentTurnPlayer = serverData.RED;
        }
    }
//////////////////////////////////////////////////////////////////////////////

    public Integer getCurrentLongestRouteValue() {
        return currentLongestRouteValue;
    }
    public void setCurrentLongestRouteValue(Integer currentLongestRouteValue) {
        this.currentLongestRouteValue = currentLongestRouteValue;
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
