package server;


import game.Game;
import results.createGameResult;
import results.joinGameResult;
import results.signInResult;
import server.ServerData;

public class ServerCommands implements IServer {

    private ServerData serverData = ServerData.getInstance();


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
        Game game = new Game();
        game.setGameName(gameName);
        game.setMaxPlayers(Integer.parseInt(maxPlayers));
        return serverData.setGame(game);
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
