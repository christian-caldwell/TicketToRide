package models.data;

import java.util.ArrayList;

public class PollManagerData {
    public ArrayList<Game> gamesChanged;

    public ArrayList<Game> getGamesChanged() {
        return gamesChanged;
    }

    public void addGamesChanged(Game gamesChanged) {
        this.gamesChanged.add(gamesChanged);
    }
}
