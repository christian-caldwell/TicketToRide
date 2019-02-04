package server;


import results.createGameResult;
import results.joinGameResult;
import results.signInResult;

public class ServerFacade implements IServer {
    @Override
    public joinGameResult joinGame(String username, String gameName) {
        return null;
    }

    @Override
    public createGameResult createGame(String gameName, String maxPlayers, String username) {
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
