package server;


import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import models.TTR_Constants;
import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Game;
import models.data.Player;
import models.data.PollManagerData;
import models.data.Result;
import models.data.Route;
import models.data.TrainCard;
import models.data.User;

public class ServerCommands implements IServer {
    private final int MAX_PLAYERS = 5;
    private ServerData serverData = ServerData.getInstance();
    private TTR_Constants constants = TTR_Constants.getInstance();

   // private ClientProxy clientProxy = new ClientProxy();

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
            for (User user: serverData.getUsers()){
                if (user.getUsername().equals(username)) {
                    user.setGameJoined(serverData.getGame(gameName));
                    break;
                }
            }
            result.setGame(gameName);
            result.setSuccessful(true);
            serverData.getGame(gameName).addPlayerUsername(username);
        }
        //clientProxy.updateJoinGame(serverData.getGame(gameName));
        return result;
    }

    @Override
    public Result createGame(String gameName, String username, Integer numPlayers) {
        Game game = new Game(gameName);
        for(User user: serverData.getUsers()) {
            if(user.getUsername().equals(username)) {
                user.setHost(true);
            }
        }
        Result result = serverData.setGame(game);
        //clientProxy.updateCreateGame(gameName);
        return result;

    }

    //more will be done on this later.
    @Override
    public Result startGame(String gameName) {
        //clientProxy.updateStartGame(gameName);
        Game targetGame = serverData.getGame(gameName);
        serverData.initializeGamePlayers(targetGame);
        serverData.initializeContainers(targetGame);
        serverData.dealHands(targetGame);
        targetGame.setStarted(true);
        targetGame.incrementNumPlayerActions();
        Result result = new Result();
        result.setSuccessful(true);
        return result;
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
                    if(user.isHost()) {
                        result.setHost(true);
                    }
                    if (user.getGameJoined() != null) {
                        result.setGameJoined(user.getGameJoined().getGameName());
                    }
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
    public Result returnDestinationCards(String userName, String gameName, DestinationCard[] returnedCards) {
        Result result = new Result();
        result.setSuccessful(false);
        Game targetGame = serverData.findGame(gameName);

        if (targetGame != null) {
            Player targetPlayer = targetGame.findPlayer(userName);
            if (targetPlayer != null) {
                //Confirm that the cards are in newDestinationCards in the first place?
                if (returnedCards != null) {
                    targetPlayer.removeFromNewDestinationCards(returnedCards);
                    targetGame.returnDestinationCards(returnedCards);
                }
                for (DestinationCard card : targetPlayer.getNewDestinationCards()) {
                    targetPlayer.addToDestinationCardHand(card);
                }
                targetPlayer.resetNewDestinationCards();
                targetGame.incrementNumPlayerActions();
                result.setSuccessful(true);
            }
        }
        return result;
    }

    @Override
    public Result purchaseRoute(String userName, String gameName, Route purchasedRoute, Integer numberOfWilds) {
        Result result = new Result();
        result.setSuccessful(false);
        Game targetGame = serverData.findGame(gameName);
        if (targetGame != null){
            if (targetGame.purchaseRoute(userName, purchasedRoute, numberOfWilds)){
                targetGame.incrementNumPlayerActions();
                targetGame.incrementCurrentTurnPlayer();
                result.setSuccessful(true);
            }
        }
        return result;
    }

    @Override
    public Result requestDestinationCards(String userName, String gameName) {
        Result result = new Result();
        result.setSuccessful(false);
        Game targetGame = serverData.findGame(gameName);
        if (targetGame != null){
            Player targetplayer = targetGame.getPlayer(userName);
            targetplayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
            targetplayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
            targetplayer.addToNewDestinationCardHand(targetGame.dealDestinationCard());
            targetGame.incrementNumPlayerActions();
            targetGame.incrementCurrentTurnPlayer();
            result.setSuccessful(true);
        }
        return result;
    }

    @Override
    public Result requestTicketCard(String userName, String gameName, Integer selectedCard, Boolean secondPick) {
        Result result = new Result();
        result.setSuccessful(false);
        Game targetGame = serverData.findGame(gameName);
        if (targetGame != null){
            TrainCard card = targetGame.dealTicketCard(selectedCard);
            targetGame.incrementNumPlayerActions();

            if (secondPick && (card.getCardColor().equals(constants.WILD))) {
                return result;
            }
            else if (!secondPick && (card.getCardColor().equals(constants.WILD))) {
                targetGame.incrementCurrentTurnPlayer();
            }
            else if (secondPick){
                targetGame.incrementCurrentTurnPlayer();
            }
            Player targetplayer = targetGame.getPlayer(userName);
            targetplayer.addTicketToHand(card.getCardColor());
            result.setSuccessful(true);
        }
        return result;
    }

    @Override
    public Result postChatMessage(String gameName, ChatMessage chatMessage) {
        Result result = new Result();
        chatMessage.setAuthorUserName(chatMessage.getAuthorUserName());
        chatMessage.setMessageContents(chatMessage.getMessageContents());
        chatMessage.setTimeStamp(chatMessage.getTimeStamp());
        Game targetGame = serverData.findGame(gameName);
        if (targetGame != null) {
            targetGame.addChat(chatMessage);
            result.setSuccessful(true);
            return result;
        }
        else {
            result.setSuccessful(false);
            return result;
        }
    }

    @Override
    public Result requestGame(String gameName) {
        Result result = new Result();
        result.setSuccessful(false);
        Game targetGame = serverData.getGame(gameName);
        if (targetGame != null) {
            result.setSuccessful(true);
            result.setRunningGame(targetGame);
        }
        return result;
    }

    public Result getAvailableGames(String username) {
        ServerData dataContainer = ServerData.getInstance();
        Map<String, Game> availableGames =  dataContainer.getAvailableGames();

        ArrayList<Game> games = new ArrayList<>();


        for (Map.Entry<String, Game> entry : availableGames.entrySet()) {
            if (!entry.getValue().isStarted() || entry.getValue().getPlayerUsernames().contains(username)) {
                games.add(entry.getValue());
            }
        }
        System.out.println("Current Complete Game List: " + games.toString());

        Result result = new Result();
        PollManagerData data = new PollManagerData();
        data.setGamesChanged(games);
        result.setPollResult(data);
        return result;
    }

    public Result getRunningGame(String gameName, String userName, Integer playerActions, Integer chatSize) {
        ServerData dataContainer = ServerData.getInstance();
        Game game = dataContainer.getGame(gameName);

        if (game.getNumPlayerActions().equals(playerActions) && chatSize.equals(game.getChatLog().size())) {
            game = null;
        }
        else if (!game.getPlayerUsernames().contains(userName)) {
            System.out.println("This user doesn't belong here!!!!");
            game = null;
        }

        if (game != null) {
            game = game.copy();
            game.hideSecrets(userName);
        }
        Result result = new Result();
        result.setRunningGame(game);
        return result;
    }


    //RUN GAME FACADE STUFF
//        public Result returnDestinationCards(String userName, String gameName){
//            DestinationCard[] returnedCards = null;
//            return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
//        }
/*
        public Result returnDestinationCards(String userName, String gameName, String first_location, String second_location, Integer points){
            DestinationCard[] returnedCards = new DestinationCard[1];
            DestinationCard card = TTR_Constants.getInstance().findDestinationCard(first_location, second_location);
            returnedCards[0] = card;
            return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
        }

        public Result returnDestinationCards(String userName, String gameName, String first_location_1, String second_location_1, Integer points_1, String first_location_2, String second_location_2, Integer points_2){
            DestinationCard[] returnedCards = new DestinationCard[2];
            String[] route_1 = new String[]{first_location_1, second_location_1};
            DestinationCard card_1 = TTR_Constants.getInstance().findDestinationCard(first_location_1, second_location_1);
            String[] route_2 = new String[]{first_location_2, second_location_2};
            DestinationCard card_2 = TTR_Constants.getInstance().findDestinationCard(first_location_2, second_location_2);
            returnedCards[0] = card_1;
            returnedCards[1] = card_2;
            return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
        }

        public Result postChatMessage(String userName, String gameName, String contents, String timpStamp) {
            ChatMessage message = new ChatMessage();
            message.setAuthorUserName(userName);
            message.setMessageContents(contents);
            message.setTimeStamp(timpStamp);
            return serverCommands.postChatMessage(gameName, message);
        }


        public Result purchaseRoute(String userName, String gameName, Integer points, String first_location, String second_location, Integer color, Integer wildCount) {
            Route purchasedRoute = TTR_Constants.getInstance().getRoute(first_location, second_location);
            return serverCommands.purchaseRoute(userName, gameName, purchasedRoute, wildCount);
        }
        */
}
