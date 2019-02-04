package server;


import results.createGameResult;
import results.joinGameResult;
import results.signInResult;
import server.ServerData;

public class ServerCommands implements IServer {


    @Override
    public joinGameResult joinGame(String username, String gameName) {
        joinGameResult result = new joinGameResult();
        result.setGameName(gameName);
        result.setErrorMessage("None");
        ServerData.getInstance().getGame(gameName);
        return result;
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
    public signInResult register(String username, String password) {
        return null;
    }

    @Override
    public signInResult login(String username, String password) {

        return null;
    }
}
