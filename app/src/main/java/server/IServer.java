package server;

import results.createGameResult;
import results.joinGameResult;
import results.signInResult;

public interface IServer {
    public joinGameResult joinGame(String username, String gameName);
    public createGameResult createGame(String gameName, String maxPlayers, String username);
    public String startGame(String gameName,  Integer maxPlayers);
    public SignInResult register(User newUser);
    public SignInResult login(User returningUser);
}
