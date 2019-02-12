package server;


import java.util.UUID;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;

public class ServerCommands implements IServer {
    private final int MAX_PLAYERS = 5;
    private ServerData serverData = ServerData.getInstance();

    public ServerData getServerData() {
        return serverData;
    }

    @Override
    public Result joinGame(User user, Game game) {
        Result result = new Result();
        if (game == null) {
            result.setErrorMessage("Game does not exist!");
            result.setSuccessful(false);
        }
        else if (MAX_PLAYERS == game.getPlayers().size()) {
            result.setErrorMessage("no more players can be added!");
            result.setSuccessful(false);
        }
        else {
            result.setGame(game.getGameName());
            result.setSuccessful(true);
            game.addPlayer(user.getUsername());
            user.setGamesJoined(game);
        }
        return result;
    }

    @Override
    public Result createGame(String gameName) {
        Game game = new Game(gameName);
        Result result = serverData.setGame(game);
        return result;

    }

    //more will be done on this later.
    @Override
    public String startGame(Game gameName) {
        return "Success";
    }

    @Override
    public Result register(User newUser) {
        Result result = new Result();
        for (User user: serverData.getUsers()) {
            if (user.getUsername().equals(newUser.getUsername())) {
                result.setErrorMessage("this user already exists...");
                result.setSuccessful(false);
                return result;
            }
        }
        result.setAuthenticationToken(UUID.randomUUID().toString().toUpperCase());
        result.setSuccessful(true);
        serverData.addUsers(new User(newUser.getUsername(),newUser.getPassword()));
        return result;
    }

    @Override
    public Result login(User returnUser) {
        Result result = new Result();
        for (User user: serverData.getUsers()) {
            if (user.getUsername().equals(returnUser.getUsername())) {
                if (user.getPassword().equals(returnUser.getPassword())) {
                    result.setAuthenticationToken(UUID.randomUUID().toString().toUpperCase());
                    result.setSuccessful(true);
                    return result;
                }
                result.setSuccessful(false);
                result.setErrorMessage("incorrect password...");
                return result;
            }
        }
        result.setErrorMessage("user does not exist...");
        result.setSuccessful(false);
        return result;
    }

    @Override
    public PollManagerData pollServer() {
        return null;
    }


}