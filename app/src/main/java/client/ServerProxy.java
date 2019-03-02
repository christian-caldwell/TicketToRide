package client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.Game;
import models.data.Result;
import models.data.Route;
import models.data.User;
import server.GeneralCommand;
import server.IServer;
import server.PollManager;
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

    @Override
    public Result returnDestinationCards(DestinationCard[] returnedCards, String userName, String gameName) {
        return null;
    }

    @Override
    public Result purchaseRoute(Route purchasedRoute, String userName, String gameName) {
        return null;
    }

    @Override
    public Result requestDestinationCards(String userName, String gameName) {
        return null;
    }

    @Override
    public Result requestTicketCards(boolean[] faceUpCardsDrawn, String userName, String gameName) {
        return null;
    }

    public ArrayList<Game> getGames() {
        String className = PollManager.class.getName();
        String methodName = "getAvailableGames";

        Object[] parameterDataArray = new Object[0];
        Class<?>[] parameterClassArray = new Class<?>[0];


        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);


        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(newCommand);

            ClientCommunicator communicator = new ClientCommunicator();

            json = communicator.send(json);

            Result result = mapper.readValue(json, Result.class);

            return result.getPollResult().getGamesChanged();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}