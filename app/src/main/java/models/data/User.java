package models.data;

import java.util.ArrayList;

public class User {


    private boolean isHost;
    private String username;
    private String password;
    private Game gamesJoined;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Game getGamesJoined() {
        return gamesJoined;
    }

    public void setGamesJoined(Game gamesJoined) {
        this.gamesJoined = gamesJoined;
    }
}
