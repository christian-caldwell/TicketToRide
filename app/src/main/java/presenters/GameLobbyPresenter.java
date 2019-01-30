package presenters;

import android.widget.Button;
import java.util.ArrayList;
import views.IGameLobbyView;
import server.serverProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter {

    ArrayList<Integer> gameList;
    Button joinGameButton;
    Button createGameButton;
    Button leaveGameButton;
    Button logoutButton;

    IGameLobbyView view;
    private void updateGameList(Map<String, Game>)

    updateGamePlayers(Game)

    @Override
    public void addPlayer(int gameId, String username) {
        serverProxy.joinGame();
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
    public void createGame() {

    }

}
