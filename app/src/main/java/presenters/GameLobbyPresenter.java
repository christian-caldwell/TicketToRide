package presenters;

import android.widget.Button;

import java.util.ArrayList;

public class GameLobbyPresenter implements IGameLobbyPresenter {

    ArrayList<Integer> gameList;
    Button joinGameButton;
    Button createGameButton;
    Button leaveGameButton;
    Button logoutButton;

    @Override
    public void addPlayer(int gameId, String username) {

    }

    @Override
    public void startGame(int gameId) {

    }

    @Override
    public ArrayList getGameList() {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void createGame() {

    }

    @Override
    public void removePlayer(int gameId, String username) {

    }
}
