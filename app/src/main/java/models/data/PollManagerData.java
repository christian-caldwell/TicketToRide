package models.data;

import java.util.ArrayList;

public class PollManagerData {
    private ArrayList<Game> gamesChanged;
    private ArrayList<User> usersChanged;

    public ArrayList<Game> getGamesChanged() {
        return gamesChanged;
    }

    public void addGamesChanged(Game gamesChanged) {
        this.gamesChanged.add(gamesChanged);
    }

    public ArrayList<User> getUsersChanged() {
        return usersChanged;
    }

    public void addUsersChanged(User usersChanged) {
        this.usersChanged.add(usersChanged);
    }

}
