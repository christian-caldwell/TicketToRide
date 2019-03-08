package server.facade;

import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Result;
import models.data.Route;
import server.ServerCommands;

public class RunGameFacade {
    private ServerCommands serverCommands;

    public RunGameFacade() {
        serverCommands = new ServerCommands();
    }

    public Result startGame(String gameName) {
        return serverCommands.startGame(gameName);
    }

    public Result returnDestinationCards(String userName, String gameName){
        DestinationCard[] returnedCards = null;
        return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
    }

    public Result returnDestinationCards(String userName, String gameName, String first_location, String second_location, Integer points){
        DestinationCard[] returnedCards = new DestinationCard[1];
        String[] route = new String[]{first_location, second_location};
        DestinationCard card = new DestinationCard(route, points);
        returnedCards[0] = card;
        return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
    }

    public Result returnDestinationCards(String userName, String gameName, String first_location_1, String second_location_1, Integer points_1, String first_location_2, String second_location_2, Integer points_2){
        DestinationCard[] returnedCards = new DestinationCard[2];
        String[] route_1 = new String[]{first_location_1, second_location_1};
        DestinationCard card_1 = new DestinationCard(route_1, points_1);
        String[] route_2 = new String[]{first_location_2, second_location_2};
        DestinationCard card_2 = new DestinationCard(route_2, points_2);
        returnedCards[0] = card_1;
        returnedCards[1] = card_2;
        return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
    }

    public Result requestDestinationCards(String userName, String gameName) {
        return serverCommands.requestDestinationCards(userName, gameName);
    }

    public Result requestTicketCard(String userName, String gameName, Integer selectedCard, Boolean secondPick) {
        return serverCommands.requestTicketCard(userName, gameName, selectedCard, secondPick);
    }

    public Result purchaseRoute(String userName, String gameName, Integer points, String first_location, String second_location, Integer color, Integer wildCount) {
        //TODO replace the 'Enums.Color' with parameter Integer
        Route purchasedRoute = new Route(points, color, new String[]{first_location, second_location});
        return serverCommands.purchaseRoute(userName, gameName, purchasedRoute);
    }

    public Result postChatMessage(String userName, String gameName, String contents, String timpStamp) {
        ChatMessage message = new ChatMessage();
        message.setAuthorUserName(userName);
        message.setMessageContents(contents);
        message.setTimeStamp(timpStamp);
        return serverCommands.postChatMessage(gameName, message);
    }
}
