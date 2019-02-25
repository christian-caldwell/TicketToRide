package models.data;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String username;
    private Enum playerColor;
    private Integer score;
    private Integer trainsRemaining;
    private Set<Route> routesOwned;
    private Integer individualLongestRouteValue;
    private Boolean hasLongestRoute;

    public Player(String username, Enum playerColor) {
        this.username = username;
        this.playerColor = playerColor;
        this.score =0;
        this.trainsRemaining = 45;//TODO: i cant remember if this is the right # of trains that a player starts with
        this.routesOwned = new HashSet<>(0);
        this.individualLongestRouteValue = 0;
        this.hasLongestRoute = false;
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
}
