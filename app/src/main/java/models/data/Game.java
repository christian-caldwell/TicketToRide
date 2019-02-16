package models.data;

import java.util.ArrayList;

public class Game {
    public String status;
    private boolean isVisibleInLobby;
    private String gameName;

    private ArrayList<String> players = new ArrayList<>();

    public Game() {
    }

    public boolean isVisibleInLobby() {
        return isVisibleInLobby;
    }

    public void setVisibleInLobby(boolean visibleInLobby) {
        isVisibleInLobby = visibleInLobby;
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
