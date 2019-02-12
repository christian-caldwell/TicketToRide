package models.data;

import java.util.ArrayList;

public class User {


    private boolean isHost;
    private String username;
    private String pass;
    private Game gamesJoined;
    public User(String username, String password) {
        this.username = username;
        this.pass = password;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Game getGamesJoined() {
        return gamesJoined;
    }

    public void setGamesJoined(Game gamesJoined) {
        this.gamesJoined = gamesJoined;
    }
}
