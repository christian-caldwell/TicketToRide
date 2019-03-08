package models.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import models.Constants;

public class Player {
    private Boolean hasLongestRoute;

    public Player() {
    }

    private Integer score;
    private Integer trainsRemaining;
    private Integer individualLongestRouteValue;
    private Integer numTickets;
    private String username;
    private Enum playerColor;

    private Set<Route> routesOwned = new HashSet<>(0);
    private Map<Enums.Color, Integer> tickets = new HashMap<>();
    private ArrayList<DestinationCard> destinationCardHand = new ArrayList<>(0);
    private ArrayList<DestinationCard> newDestinationCards = new ArrayList<>(0);


    public Player(String username, Enum playerColor) {
        this.username = username;
        this.playerColor = playerColor;
        this.score = 0;
        this.trainsRemaining = Constants.TRAIN_STARTING_COUNT;//TODO: i cant remember if this is the right # of trains that a player starts with
        this.individualLongestRouteValue = 0;
        this.hasLongestRoute = false;
        initTickets();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<DestinationCard> getDestinationCardHand() {
        return destinationCardHand;
    }
    public void addToDestinationCardHand(DestinationCard destinationCard) {
        this.destinationCardHand.add(destinationCard);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<DestinationCard> getNewDestinationCards() {
        return newDestinationCards;
    }
    public void addToNewDestinationCardHand(DestinationCard destinationCard) {
        this.newDestinationCards.add(destinationCard);
    }
    public void removeFromNewDestinationCards(DestinationCard[] returnedCards) {
        for (DestinationCard card: returnedCards) {
            this.newDestinationCards.add(card);
        }
    }
    public void hideCards() {
        tickets = null;
        this.destinationCardHand = null;
        this.newDestinationCards = null;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Enum getPlayerColor() {
        return playerColor;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Integer getScore() {
        return score;
    }
    public void incrementScore(Integer numToIncrementBy) {
        this.score += numToIncrementBy;
    }
    public void decrementScore(Integer numToDecrementBy) {
        this.score -= numToDecrementBy;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Integer getTrainsRemaining() {
        return trainsRemaining;
    }
    public void decrementTrainsRemaining(Integer numToDecrementBy) {
        this.trainsRemaining -= numToDecrementBy;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public void addRoute(Route route) {
        this.routesOwned.add(route);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Integer getIndividualLongestRouteValue() {
        return individualLongestRouteValue;
    }
    public void incrementIndividualLongestRouteValue(Integer numToIncrementBy) {
        this.individualLongestRouteValue += numToIncrementBy;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Boolean getHasLongestRoute() {
        return hasLongestRoute;
    }
    public void setHasLongestRoute(Boolean hasLongestRoute) {
        this.hasLongestRoute = hasLongestRoute;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public Map<Enums.Color, Integer> getTickets() {
        return tickets;
    }
    public void addTicketToHand(Enums.Color color) {
        this.tickets.put(color,tickets.get(color)+1);
    }
    private void initTickets() {
        for (Enums.Color c: Enums.Color.values()) {
            tickets.put(c, 0);
        }
        numTickets = 0;
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
