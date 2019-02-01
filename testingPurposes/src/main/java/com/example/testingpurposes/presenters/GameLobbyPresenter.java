package com.example.testingpurposes.presenters;

import java.util.ArrayList;
import java.util.Map;

import com.example.testingpurposes.server.serverProxy;
import com.example.testingpurposes.game.Game;

public class GameLobbyPresenter implements IGameLobbyPresenter {

    ArrayList<Game> gameList;


    @Override
    public void addPlayer(String gameId, String username) {
        serverProxy serverP = new serverProxy();
        serverP.joinGame(gameId, username);
        view.updateGamePlayers(gameId);
        view.updateRecyclerView();
    }

    @Override
    public void startGame(String gameId) {

    }

    @Override
    public ArrayList getGameList() {
        Game game = new Game();
        Map<String, Game> map;
        view.updateGameList(map);

        return null;
    }


    @Override
    public void createGame() {

    }

}
