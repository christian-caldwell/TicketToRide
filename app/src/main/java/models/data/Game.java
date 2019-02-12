package models.data;

import java.util.ArrayList;

public class Game {
    public String status;
    private String gameName;
    private String hostName;
    private ArrayList<String> players = new ArrayList<>();

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public String getStatus() {
        return "";
    }

    public void addPlayer(String userName) { this.players.add(userName); }
}
