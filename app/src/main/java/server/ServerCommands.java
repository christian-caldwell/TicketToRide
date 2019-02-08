package server;


import models.data.Game;
import models.data.Result;
import models.data.User;

public class ServerCommands implements IServer {
    private final int MAX_PLAYERS = 5;
    private ServerData serverData = ServerData.getInstance();


    @Override
    public Result joinGame(String username, String gameName) {
        Result result = new Result();
        Game game = serverData.getGame(gameName);
        if (game == null) {
            result.setErrorMessage("Game does not exist!");
            result.setSuccesful(false);
        }
        else if (MAX_PLAYERS == game.getPlayers().size()) {
            result.setErrorMessage("no more players can be added!");
            result.setSuccesful(false);
        }
        else {
            result.setGameName(gameName);
            result.setErrorMessage("");
            result.setSuccesful(true);
            game.addPlayer(username);
        }
        return result;
    }

    @Override
    public Result createGame(String gameName, String username) {
        Game game = new Game(gameName);
        Result result = serverData.setGame(game);
        if (result.isSuccessful()) {
            joinGame(username, gameName);
        }
        return result;

    }

    @Override
    public String startGame(String gameName) {
        return "Success";
    }

    @Override
    public Result register(User returnUser) {
        return null;
    }

    @Override
    public Result login(User returnUser) {

        return null;
    }
}
