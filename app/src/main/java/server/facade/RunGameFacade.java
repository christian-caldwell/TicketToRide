package server.facade;

import android.util.Pair;

import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Result;
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
        Pair<String, String> route = new Pair<>(first_location, second_location);
        DestinationCard card = new DestinationCard(route, points);
        returnedCards[0] = card;
        return serverCommands.returnDestinationCards(userName, gameName, returnedCards);
    }

    public Result returnDestinationCards(String userName, String gameName, String first_location_1, String second_location_1, Integer points_1, String first_location_2, String second_location_2, Integer points_2){
        DestinationCard[] returnedCards = new DestinationCard[2];
        Pair<String, String> route_1 = new Pair<>(first_location_1, second_location_1);
        DestinationCard card_1 = new DestinationCard(route_1, points_1);
        Pair<String, String> route_2 = new Pair<>(first_location_2, second_location_2);
        DestinationCard card_2 = new DestinationCard(route_2, points_2);
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
}
