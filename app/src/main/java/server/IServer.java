package server;

import results.CreateGameResult;
import results.JoinGameResult;
import results.SignInResult;
import game.User;

public interface IServer {
    public JoinGameResult joinGame(String username, String gameName);
    public CreateGameResult createGame(String gameName, String maxPlayers, String username);
    public String startGame(String gameName,  Integer maxPlayers);
    public SignInResult register(User newUser);
    public SignInResult login(User returningUser);
}
