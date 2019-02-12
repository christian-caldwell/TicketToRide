package server;


import java.util.UUID;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;
import java.util.ArrayList;

public class ServerCommands implements IServer {
    private final int MAX_PLAYERS = 5;
    private ServerData serverData = ServerData.getInstance();
    private ClientProxy clientProxy = new ClientProxy();

    public ServerData getServerData() {
        return serverData;
    }

    @Override
    public Result joinGame(String username, String gameName, Integer numPlayers) {
        Result result = new Result();
        if (gameName.equals("")) {
            result.setErrorMessage("Game does not exist!");
            result.setSuccessful(false);
        }
        else if (MAX_PLAYERS == numPlayers) {
            result.setErrorMessage("no more players can be added!");
            result.setSuccessful(false);
        }
        else {
            result.setGame(gameName);
            result.setSuccessful(true);
            serverData.getGame(gameName).addPlayer(username);
        }
        clientProxy.updateJoinGame(serverData.getGame(gameName));
        return result;
    }

    @Override
    public Result createGame(String gameName, String username, Integer numPlayers) {
        Game game = new Game(gameName);
        Result result = serverData.setGame(game);
        clientProxy.updateCreateGame(gameName);
        return result;

    }

    //more will be done on this later.
    @Override
    public String startGame(Game gameName) {
        clientProxy.updateStartGame(gameName.getGameName());
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

        //THIS FUNCTION NEEDS HELP!!!!
        PollManagerData tempPollData = new PollManagerData();
        return tempPollData;
    }
}
