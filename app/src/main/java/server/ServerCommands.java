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

    public ServerData getServerData() {
        return serverData;
    }

    @Override
    public Result joinGame(String user, Game game) {
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
            game.addPlayer(user);
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
    public Result register(String newUser, String password) {
        Result result = new Result();
        for (User user: serverData.getUsers()) {
            if (user.getUsername().equals(newUser)) {
                result.setErrorMessage("this user already exists...");
                result.setSuccessful(false);
                return result;
            }
        }
        result.setAuthenticationToken(UUID.randomUUID().toString().toUpperCase());
        result.setSuccessful(true);
        serverData.addUsers(new User(newUser,password));
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

    @Override
    public Result getLobbyList() {
        //THIS FUNCTION NEEDS HELP!!!!
        ArrayList<Game> tempReturnArray = new ArrayList<Game>();
        Result tempResult = new Result();
        tempResult.setLobbyList(tempReturnArray);
        return tempResult;
    }


}
