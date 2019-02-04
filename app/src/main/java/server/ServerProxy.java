package server;

import results.CreateGameResult;
import results.JoinGameResult;
import results.SignInResult;
import game.User;

public class ServerProxy implements IServer {

    @Override
    public JoinGameResult joinGame(String username, String gameName) {
        return null;
    }

    @Override
    public CreateGameResult createGame(String gameName, String maxPlayers, String username) {
        return null;
    }

    @Override
    public String startGame(String gameName, Integer maxPlayers) {
        return null;
    }

    @Override
    public SignInResult register(User newUser) {
        return null;
    }

    @Override
    public SignInResult login(User returningUser) {
        return null;
    }
}
