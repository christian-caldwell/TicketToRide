package game;

import java.util.ArrayList;

public class Game {
    public String status;
    private Integer maxPlayers;
    private String gameName;
    private ArrayList<User> players;

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public String getStatus() {
        return "";
    }
}
