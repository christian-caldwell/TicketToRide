package presenters;

import android.widget.Button;
import java.util.ArrayList;
import views.IGameLobbyView;

public class GameLobbyPresenter implements IGameLobbyPresenter {

    ArrayList<Integer> gameList;
    Button joinGameButton;
    Button createGameButton;
    Button leaveGameButton;
    Button logoutButton;

    IGameLobbyView view;


    @Override
    public void addPlayer(int gameId, String username) {

        view.updateRecyclerView();
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
