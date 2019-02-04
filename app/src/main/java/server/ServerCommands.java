package server;


import results.CreateGameResult;
import results.JoinGameResult;
import results.SignInResult;
import server.ServerData;
import game.User;

public class ServerCommands implements IServer {


    @Override
    public JoinGameResult joinGame(String username, String gameName) {
        JoinGameResult result = new JoinGameResult();
        result.setGameName(gameName);
        result.setErrorMessage("None");
        ServerData.getInstance().getGame(gameName);
        return result;
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
