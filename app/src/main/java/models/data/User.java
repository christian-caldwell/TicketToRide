package models.data;

import java.util.ArrayList;

public class User {
    private String username;
    private String pass;
    private ArrayList<Game> gamesJoined;

    public ArrayList<Game> getGamesJoined() {
        return gamesJoined;
    }

    public void addGamesJoined(Game gameJoined) {
        this.gamesJoined.add(gameJoined);
    }

    public User(String username, String password) {
        this.username = username;
        this.pass = password;
        this.gamesJoined = new ArrayList<Game>();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return pass;
    }

}
