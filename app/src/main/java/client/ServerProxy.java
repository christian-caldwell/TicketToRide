package client;

import java.util.ArrayList;

import models.data.Game;
import models.data.Request;
import models.data.Result;
import models.data.User;
import server.GeneralCommand;
import server.IServer;
import server.facade.LoginFacade;
import server.facade.RegisterFacade;

public class ServerProxy implements IServer {

    ClientCommunicator client = new ClientCommunicator();
    @Override
    public Result joinGame(User username, Game gameName) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public Result createGame(String gameName, User username) {
        Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
        return null;
    }

    @Override
    public String startGame(Game gameName) {
        return null;
    }

    @Override
    public Result register(User newUser) {
        RegisterFacade temp = new RegisterFacade();

        String className = temp.getClass().toString();
        Object[] params = new Object[1];
        params[0] = newUser;

        GeneralCommand generatedCommand = createCommand(className, params);

        /*
        Class<?>[] userObjName = new Class<?>[1];
        userObjName[0] = newUser.getUsername().getClass();
        userObjName[1] = newUser.getPassword().getClass();

        Object[] objectSet = new Object[1];
        objectSet[0] = newUser.getUsername();
        objectSet[1] = newUser.getPassword();*/

        //GeneralCommand newCommand = new GeneralCommand(className,"login", userObjName, objectSet);

        ClientCommunicator communicator = new ClientCommunicator();

        //CommandResult serverResponse = communicator.send(newCommand);
        //Result result = new Result(serverResponse.getErrorInfo(), (String) serverResponse.getData(), null, serverResponse.isSuccess());

        return new Result("nothing", "token", null, true);

    }

    @Override
    public Result login(User returnUser) {

        LoginFacade temp = new LoginFacade();

        String className = temp.getClass().toString();
        Object[] params = new Object[1];
        params[0] = returnUser;

        GeneralCommand generatedCommand = createCommand(className, params);

        /*
        Class<?>[] userObjName = new Class<?>[1];
        userObjName[0] = returnUser.getUsername().getClass();
        userObjName[1] = returnUser.getPassword().getClass();

        Object[] objectSet = new Object[1];
        objectSet[0] = returnUser.getUsername();
        objectSet[1] = returnUser.getPassword();*/

        //GeneralCommand newCommand = new GeneralCommand(className,"login", userObjName, objectSet);

        ClientCommunicator communicator = new ClientCommunicator();

       //CommandResult serverResponse = communicator.send(newCommand);
       //Result result = new Result(serverResponse.getErrorInfo(), (String) serverResponse.getData(), null, serverResponse.isSuccess());

        return new Result("nothing", "token", null, true);
    }

    private GeneralCommand createCommand(String className, Object[] modelObjects) {
        String methodName = getMethodName(className);

        int decomposedrArrayLength = 0;
        for (Object currObject : modelObjects) {
            decomposedrArrayLength += getDecomposedLength(currObject);
        }

        Object[] parameterDataArray = new Object[decomposedrArrayLength];
        Class<?>[] parameterClassArray = new Class<?>[decomposedrArrayLength];

        int arrayPosition = 0;
        for (Object currentModel : modelObjects) {
            decompObjectAndAdd(currentModel, parameterClassArray, parameterDataArray, arrayPosition);
            arrayPosition++;
        }

        return new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

    }

    private String getMethodName(String className) {
        switch (className) {
            case ("LoginFacade") :
                return "login";
            case ("RegisterFacade") :
                return "register";
            case ("LobbyFacade") :
                return "";
            default:
                return "unknown target class!!!";

        }
    }

    private int getDecomposedLength(Object model) {
        if (model.getClass() == User.class) {
            return 2;
        }
        else if(model.getClass() == Game.class) {
            Game convertedModel = (Game) model;
            return 2 + convertedModel.getPlayers().size();
        }
        else {
            //if it is a primative
           return 1;  //other models and data containers can be added
        }
    }

    private void decompObjectAndAdd (Object model, Class<?>[] classArray, Object[] objectArray, int pos) {
        if (model.getClass() == User.class) {
            classArray[pos] = String.class;
            classArray[pos+1] = String.class;
            User convertedMdel = (User) model;
            objectArray[pos] = convertedMdel.getUsername();
            objectArray[pos+1] = convertedMdel.getPassword();
        }
        else if(model.getClass() == Game.class) {
            classArray[pos] = String.class;
            classArray[pos+1] = String.class;
            Game convertedMdel = (Game) model;
            objectArray[pos] = convertedMdel.getStatus();
            objectArray[pos+1] = convertedMdel.getGameName();
            ArrayList<String> list = convertedMdel.getPlayers();
            int i = 2;
            for (String player: list) {
                classArray[pos + i] = String.class;
                objectArray[pos + i] = player;
            }
        }
        else {
            // Should never reach this branch
            //other models and data containers can be added
        }
    }
}