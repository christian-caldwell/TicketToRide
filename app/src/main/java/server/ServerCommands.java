package server;


import game.Game;
import results.Result;

public class ServerCommands implements IServer {
    private final int MAX_PLAYERS = 5;

    private ServerData serverData = ServerData.getInstance();


    @Override
    public Result joinGame(String username, String gameName) {
        Result result = new Result();
        Game game = ServerData.getInstance().getGame(gameName);
        if (game == null) {
            result.setErrorMessage("Game does not exist!");
        }
        else if (MAX_PLAYERS == game.getPlayers().size()) {
            result.setErrorMessage("no more players can be added!");
        }
        else {
            result.setGameName(gameName);
            result.setErrorMessage("");
            game.addPlayer(username);
        }
        return result;
    }

    @Override
    public Result createGame(String gameName, String username) {
        Game game = new Game(gameName);
        return serverData.setGame(game);
    }

    @Override
    public String startGame(String gameName, Integer maxPlayers) {
        return null;
    }

    @Override
    public Result register(String username, String password) {
        return null;
    }

    @Override
    public Result login(String username, String password) {

        return null;
    }
}
