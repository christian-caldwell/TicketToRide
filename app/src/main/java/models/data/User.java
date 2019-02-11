package models.data;

import java.util.ArrayList;

public class User {
    private boolean isHost;
    private String username;
    private String pass;
    private Game activeGame;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setActiveGame(Game gamesJoined) {
        this.activeGame = gamesJoined;
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return pass;
    }

}
