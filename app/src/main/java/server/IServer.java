package server;
import results.*;

public interface IServer {
    public joinGameResult joinGame(String username, String gameName);
    public createGameResult createGame(String gameName, String maxPlayers, String username);
    public String startGame(String gameName,  Integer maxPlayers);
    public signInResult register(String username, String password);
    public signInResult login(String username, String password);
}
