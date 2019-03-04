package server;

import java.util.Date;

import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Result;
import models.data.User;
import models.data.Route;


public interface IServer {
    public Result register(User newUser);
    public Result login(User returnUser);
    public Result startGame(String gameName);
    public Result joinGame(String userName, String gameName, Integer numPlayers);
    public Result createGame(String gameName, String username, Integer numPlayers);

    public Result returnDestinationCards(String userName, String gameName, DestinationCard[] returnedCards);
    public Result purchaseRoute(String userName, String gameName, Route purchasedRoute);
    public Result requestDestinationCards(String userName, String gameName);
    public Result requestTicketCard(String userName, String gameName, Integer selectedCard);
    public Result postChatMessage(String gameName, ChatMessage chatMessage);
}
