package client;

import android.util.Log;

import java.lang.reflect.Method;

import models.command.Command;
import models.command.CommandResult;
import models.data.Request;
import models.data.Result;
import models.data.User;
import server.GeneralCommand;
import server.IServer;
import view.facade.server.LoginFacade;

public class ServerProxy implements IServer {

    ClientCommunicator client = new ClientCommunicator();
    @Override
    public Result joinGame(String username, String gameName) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public Result createGame(String gameName, String username) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public String startGame(String gameName, Integer maxPlayers) {
        return null;
    }

    @Override
    public Result register(User newUser) {
        return null;
    }

    @Override
    public Result login(User returnUser) {

        LoginFacade temp = new LoginFacade();

        String className = temp.getClass().toString();

        Class<?>[] userObjName = new Class<?>[1];
        userObjName[0] = returnUser.getUsername().getClass();
        userObjName[1] = returnUser.getPassword().getClass();

        Object[] objectSet = new Object[1];
        objectSet[0] = returnUser.getUsername();
        objectSet[1] = returnUser.getPassword();

        GeneralCommand newCommand = new GeneralCommand(className,"login", userObjName, objectSet);

        ClientCommunicator communicator = new ClientCommunicator();

       //CommandResult serverResponse = communicator.send(newCommand);
       //Result result = new Result(serverResponse.getErrorInfo(), (String) serverResponse.getData(), null, serverResponse.isSuccess());

        return new Result("nothing", "token", "game1", true);
    }
}