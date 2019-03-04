package models.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
    private String username;
    private Enum playerColor;
    private Integer score;
    private Integer trainsRemaining;
    private Set<Route> routesOwned;
    private Integer individualLongestRouteValue;
    private Boolean hasLongestRoute;
    private Map<Enums.Color, Integer> tickets;
    private Integer numTickets;
    private ArrayList<DestinationCard> destinationCardHand;
    private ArrayList<DestinationCard> newDestinationCards;

    public Player(String username, Enum playerColor) {
        this.username = username;
        this.playerColor = playerColor;
        this.score =0;
        this.trainsRemaining = 45;//TODO: i cant remember if this is the right # of trains that a player starts with
        this.routesOwned = new HashSet<>(0);
        this.individualLongestRouteValue = 0;
        this.hasLongestRoute = false;
        this.destinationCardHand = new ArrayList<>(0);
        this.newDestinationCards = new ArrayList<>(0);;
        initTickets();
    }

    public ArrayList<DestinationCard> getDestinationCardHand() {
        return destinationCardHand;
    }

    public void addToDestinationCardHand(DestinationCard destinationCard) {
        this.destinationCardHand.add(destinationCard);
    }

    public ArrayList<DestinationCard> getNewDestinationCards() {
        return newDestinationCards;
    }

    public void removeFromNewDestinationCards(DestinationCard[] returnedCards) {
        for (DestinationCard card: returnedCards) {
            this.destinationCardHand.add(card);
        }
    }

    public String getUsername() {
        return username;
    }

    public Enum getPlayerColor() {
        return playerColor;
    }

    public Integer getScore() {
        return score;
    }

    public void incrementScore(Integer numToIncrementBy) {
        this.score += numToIncrementBy;
    }
    public void decrementScore(Integer numToDecrementBy) {
        this.score -= numToDecrementBy;
    }

    public Integer getTrainsRemaining() {
        return trainsRemaining;
    }

    public void decrementTrainsRemaining(Integer numToDecrementBy) {
        this.trainsRemaining -= numToDecrementBy;
    }

    public void addRoute(Route route) {
        this.routesOwned.add(route);
    }

    public Integer getIndividualLongestRouteValue() {
        return individualLongestRouteValue;
    }

    public void incrementIndividualLongestRouteValue(Integer numToIncrementBy) {
        this.individualLongestRouteValue += numToIncrementBy;
    }

    public Boolean getHasLongestRoute() {
        return hasLongestRoute;
    }

    public void setHasLongestRoute(Boolean hasLongestRoute) {
        this.hasLongestRoute = hasLongestRoute;
    }

    private void initTickets() {
        tickets = new HashMap<>();
        for (Enums.Color c: Enums.Color.values()) {
            tickets.put(c, 0);
        }
        numTickets = 0;
    }

    public void hideCards() {
        tickets = null;
        this.destinationCardHand = null;
        this.newDestinationCards = null;
    }

    public Map<Enums.Color, Integer> getTickets() {
        return tickets;
    }

    public Integer getNumTickets() {
        return numTickets;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public Integer getNumDestinationCards() {
        return numDestinationCards;
    }

//    public Player copy() {
//        Player clone = new Player(username, playerColor);
//        clone.score = score;
//        clone.trainsRemaining = trainsRemaining;
//        clone.routesOwned = new HashSet<>(routesOwned);
//        clone.individualLongestRouteValue = individualLongestRouteValue;
//        clone.hasLongestRoute = hasLongestRoute;
//        clone.destinationCards = new ArrayList<>(destinationCards);
//        clone.numDestinationCards = numDestinationCards;
//        clone.tickets = new HashMap<>(tickets);
//        clone.numTickets = numTickets;
//        return clone;
//    }
}
