package server;

import java.util.Date;

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

    public Result returnDestinationCards(DestinationCard[] returnedCards, String userName, String gameName);
    public Result purchaseRoute(Route purchasedRoute, String userName, String gameName);
    public Result requestDestinationCards(String userName, String gameName);
    public Result requestTicketCards(boolean[] faceUpCardsDrawn, String userName, String gameName);
    public Result postChatMessage(String userName, String gameName, String message, Date timeStamp);
}
