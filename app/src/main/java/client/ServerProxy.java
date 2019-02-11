package client;

import java.util.ArrayList;

import models.data.Game;
import models.data.PollManagerData;
import models.data.Result;
import models.data.User;
import server.GeneralCommand;
import server.IServer;
import server.facade.GameStartFacade;
import server.facade.LobbyFacade;
import server.facade.LoginFacade;
import server.facade.RegisterFacade;
import server.PollManager;


public class ServerProxy implements IServer {

    ClientCommunicator client = new ClientCommunicator();

    /*
    @Override
    public PollManagerData userPollServer(User clientUser) {
        String className = (PollManager.class).toString();
        String methodName = "getChanges";

        Object[] params = new Object[1];
        params[0] = clientUser;

        GeneralCommand generatedCommand = createCommand(className, params, methodName);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(generatedCommand, "10.0.2.2", "8080");
        return result.getPollResult();
    }*/

    @Override
    public Result getLobbyList() {
        String className = (PollManager.class).toString();
        String methodName = "getChanges";

        GeneralCommand pollCommand = createCommand(className,null, methodName);
        ClientCommunicator communicator = new ClientCommunicator();
        return communicator.send(pollCommand, "127.0.0.1", "8080");
    }

    @Override
    public PollManagerData pollServer() {
        String className = (PollManager.class).toString();
        String methodName = "getChanges";

        GeneralCommand pollCommand = createCommand(className,null, methodName);
        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(pollCommand, "127.0.0.1", "8080");
        return result.getPollResult();
    }

    @Override
    public Result joinGame(User username, Game game) {

        String className = (LobbyFacade.class).toString();
        String methodName = "joinGame";

        Object[] params = new Object[2];
        params[0] = username;
        params[1] = game;

        GeneralCommand generatedCommand = createCommand(className, params, methodName);

        ClientCommunicator communicator = new ClientCommunicator();

        return communicator.send(generatedCommand, "10.0.2.2", "8080");
    }

    @Override
    public Result createGame(String gameName) {


        String className = LobbyFacade.class.getName();
        String methodName = "createGame";

        Object[] params = new Object[1];
        params[0] = gameName;

        GeneralCommand generatedCommand = createCommand(className, params, methodName);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(generatedCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            ClientModel.create().addLobbyGame(new Game(result.getGame()));
        }
        return result;
    }

    @Override
    public String startGame(Game game) {

        String className = (GameStartFacade.class).toString();
        String methodName = "startGame";

        Object[] params = new Object[1];
        params[0] = game;

        GeneralCommand generatedCommand = createCommand(className, params, methodName);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(generatedCommand,"127.0.0.1", "8080");

        //return new Result("nothing", "token", null, true);
        return "The game has started";
    }

    @Override
    public Result register(User newUser) {
        String className = RegisterFacade.class.getName();
        String methodName = "register";

        Object[] params = new Object[1];
        params[0] = newUser;

        GeneralCommand generatedCommand = createCommand(className, params, methodName);

        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(generatedCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            ClientModel.create().setPlayer(newUser);
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

        GeneralCommand generatedCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(generatedCommand, "10.0.2.2", "8080");
        if (result.isSuccessful()) {
            ClientModel.create().setPlayer(returnUser);
        }
        return result;
    }

    private GeneralCommand createCommand(String className, Object[] modelObjects, String methodName) {
        Object[] parameterDataArray = null;
        Class<?>[] parameterClassArray = null;

        if (modelObjects != null) {

            parameterDataArray = new Object[modelObjects.length];
            parameterClassArray = new Class<?>[modelObjects.length];

            int arrayPosition = 0;
            for (Object currentModel : modelObjects) {
                parameterClassArray[arrayPosition] = currentModel.getClass();
                parameterDataArray[arrayPosition] = currentModel;
                arrayPosition++;
            }
        }

        return new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);
    }
}