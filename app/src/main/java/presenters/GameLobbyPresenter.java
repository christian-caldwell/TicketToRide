//package presenters;
//
//import android.widget.Button;
//import java.util.ArrayList;
//import java.util.Map;
//
//import server.serverProxy;
//import game.Game;
//
//public class GameLobbyPresenter implements IGameLobbyPresenter {
//
//    ArrayList<Game> gameList;
//
//
//    @Override
//    public void addPlayer(String gameId, String username) {
//        serverProxy serverP = new serverProxy();
//        serverP.joinGame(gameId, username);
//        view.updateGamePlayers(gameId);
//        view.updateRecyclerView();
//    }
//
//    @Override
//    public void startGame(String gameId) {
//
//    }
//
//    @Override
//    public ArrayList getGameList() {
//        Game game = new Game();
//        Map<String, Game> map;
//        view.updateGameList(map);
//
//        return null;
//    }
//
//
//    @Override
//    public void createGame() {
//
//    }
//
//}
