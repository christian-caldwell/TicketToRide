package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.data.Result;
import models.data.Route;

public class YourTurnDefault extends PlayerState {
    private static final YourTurnDefault ourInstance = new YourTurnDefault();
    ServerProxy serverProxy = new ServerProxy();
    public static YourTurnDefault getInstance() {
        return ourInstance;
    }

    private YourTurnDefault() {
    }

    public Result requestTicketCard(ClientModel clientModel, int cardNum){
        Result result = new Result();
        result = serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), cardNum, false);
        clientModel.setState(YourTurnSecondDraw.getInstance());
        return result;
    }

    public Result requestDestinationCards(ClientModel clientModel){
        Result result = new Result();
        result = serverProxy.requestDestinationCards(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName());
        clientModel.setState(YourTurnAwaitingDestinations.getInstance());
        return result;
    }

    public Result purchaseRoute(ClientModel clientModel, Route route, int numberOfWilds){
        Result result = new Result();
        result = serverProxy.purchaseRoute(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), route, numberOfWilds);
        clientModel.setState(NotYourTurn.getInstance());
        return result;
    }

//    public Result acceptPlayerAction(ClientModel clientModel){}
//
//    public Result leaveGame(ClientModel clientModel){}
}
