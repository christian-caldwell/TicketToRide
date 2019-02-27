package client;

import java.util.ArrayList;

import models.data.Game;
import models.data.Result;
import models.data.User;
import server.GeneralCommand;
import server.IServer;
import server.facade.GameStartFacade;
import server.facade.LobbyFacade;
import server.facade.LoginFacade;
import server.facade.RegisterFacade;


public class ServerProxy implements IServer {

    ClientCommunicator client = new ClientCommunicator();


    @Override
    public Result joinGame(String username, String gameName, Integer numPlayers) {
        String className = LobbyFacade.class.getName();
        String methodName = "joinGame";

        Object[] parameterDataArray = new Object[3];
        Class<?>[] parameterClassArray = new Class<?>[3];

        parameterClassArray[0] = String.class;
        parameterClassArray[1] = String.class;
        parameterClassArray[2] = Integer.class;
        parameterDataArray[0] = username;
        parameterDataArray[1] = gameName;
        parameterDataArray[2] = numPlayers;

        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();

        return communicator.send(newCommand, "10.0.2.2", "8080");


        //Request request = new Request();
//        client.send("127.0.0.1", "8080", request);
    }


    @Override
    public Result createGame(String gameName, String username, Integer numPlayers) {


        String className = LobbyFacade.class.getName();
        String methodName = "createGame";

        Object[] parameterDataArray = new Object[3];
        Class<?>[] parameterClassArray = new Class<?>[3];

        parameterClassArray[0] = String.class;
        parameterClassArray[1] = String.class;
        parameterClassArray[2] = Integer.class;
        parameterDataArray[0] = gameName;
        parameterDataArray[1] = username;
        parameterDataArray[2] = numPlayers;

        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(newCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            ClientModel.create().addLobbyGamesList(new Game(result.getGame()));
        }
        return result;
    }

    @Override
    public Result startGame(String gameName) {

        String className = GameStartFacade.class.getName();
        String methodName = "startGame";

        Object[] parameterDataArray = new Object[1];
        Class<?>[] parameterClassArray = new Class<?>[1];

        parameterClassArray[0] = String.class;
        parameterDataArray[0] = gameName;

        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(newCommand,"10.0.2.2", "8080");

        //return new Result("nothing", "token", null, true);
        return result;
    }

    @Override
    public Result register(User newUser) {
        String className = RegisterFacade.class.getName();
        String methodName = "register";

        Object[] params = new Object[1];
        params[0] = newUser;

        Object[] parameterDataArray = new Object[2];
        Class<?>[] parameterClassArray = new Class<?>[2];

        parameterClassArray[0] = String.class;
        parameterClassArray[1] = String.class;
        parameterDataArray[0] = newUser.getUsername();
        parameterDataArray[1] = newUser.getPassword();

        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(newCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            ClientModel.create().setUserPlayer(newUser);
        }
        return result;
    }
    @Override
    public Result login(User returnUser) {
        String className = LoginFacade.class.getName();
        String methodName = "login";

        Object[] params = new Object[1];
        params[0] = returnUser;

        Object[] parameterDataArray = new Object[2];
        Class<?>[] parameterClassArray = new Class<?>[2];

        parameterClassArray[0] = String.class;
        parameterClassArray[1] = String.class;
        parameterDataArray[0] = returnUser.getUsername();
        parameterDataArray[1] = returnUser.getPassword();

        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(newCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            returnUser.setHost(result.isHost());
            if (result.getGameJoined() != null) {
                for (Game game : ClientModel.create().getLobbyGamesList()) {
                    if (game.getGameName().equals(result.getGameJoined())) {
                        returnUser.setGameJoined(game);
                        break;
                    }
                }
            }
            ClientModel.create().setUserPlayer(returnUser);
        }
        return result;
    }

    private GeneralCommand createCommand(String className, Object[] modelObjects, String methodName) {
        Object[] parameterDataArray = null;
        Class<?>[] parameterClassArray = null;

        if (modelObjects != null) {
            int decomposedrArrayLength = 0;
            for (Object currObject : modelObjects) {
                decomposedrArrayLength += getDecomposedLength(currObject);
            }

            parameterDataArray = new Object[decomposedrArrayLength];
            parameterClassArray = new Class<?>[decomposedrArrayLength];

            int arrayPosition = 0;
            for (Object currentModel : modelObjects) {
                decompObjectAndAdd(currentModel, parameterClassArray, parameterDataArray, arrayPosition);
                arrayPosition++;
            }
        }

        return new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

    }

    private int getDecomposedLength(Object model) {
        if (model.getClass() == User.class) {
            return 2;
        }
        else if(model.getClass() == Game.class) {
            Game convertedModel = (Game) model;
            return 2 + convertedModel.getPlayerUsernames().size();
        }
        else if (model.getClass() == String.class) {
            return 1;
        }
        else if (model instanceof Number) {
            return 1;
        }
        else if (model instanceof Boolean) {
            return 1;
        }
        else if (model instanceof Character) {
            return 1;
        }
        else {
            //Oops!!! We missed a parameter type!
           return 0;  //return 0 indicates error
        }
    }

    private void decompObjectAndAdd (Object model, Class<?>[] classArray, Object[] objectArray, int pos) {
        if (model.getClass() == User.class) {
            classArray[pos] = String.class;
            classArray[pos+1] = String.class;
            User convertedModel = (User) model;
            objectArray[pos] = convertedModel.getUsername();
            objectArray[pos+1] = convertedModel.getPassword();
        }
        else if(model.getClass() == Game.class) {
            classArray[pos] = String.class;
            classArray[pos+1] = String.class;
            Game convertedModel = (Game) model;
            objectArray[pos] = convertedModel.getStatus();
            objectArray[pos+1] = convertedModel.getGameName();
            ArrayList<String> list = convertedModel.getPlayerUsernames();
            int i = 2;
            for (String player: list) {
                classArray[pos + i] = String.class;
                objectArray[pos + i] = player;
            }
        }
        else if (model.getClass() == String.class) {
            classArray[pos] = String.class;
            String convertedModel = (String) model;
            objectArray[pos] = convertedModel;
        }
        else if (model instanceof Number) {
            classArray[pos] = null;
            int convertedModel = (int) model;
            objectArray[pos] = convertedModel;
        }
        else if (model instanceof Boolean) {
            classArray[pos] = null;
            Boolean convertedModel = (Boolean) model;
            objectArray[pos] = convertedModel;
        }
        else if (model instanceof Character) {
            classArray[pos] = null;
            char convertedModel = (char) model;
            objectArray[pos] = convertedModel;
        }
        else {
            //Oops!!! We missed a parameter type!
            //return 0 indicates error
        }
    }
}