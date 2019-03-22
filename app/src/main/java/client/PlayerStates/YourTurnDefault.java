package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.data.Route;

public class YourTurnDefault extends PlayerState {
    private static final YourTurnDefault ourInstance = new YourTurnDefault();
    ServerProxy serverProxy = new ServerProxy();
    public static YourTurnDefault getInstance() {
        return ourInstance;
    }

    private YourTurnDefault() {
    }

    public void requestTicketCard(ClientModel clientModel,  int cardNum){
        serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), cardNum, false);
        clientModel.setState(YourTurnSecondDraw.getInstance());
    }

    public void requestDestinationCards(ClientModel clientModel){
        serverProxy.requestDestinationCards(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName());
        clientModel.setState(YourTurnAwaitingDestinations.getInstance());
    }

    public void purchaseRoute(ClientModel clientModel, Route route, int numberOfWilds){
        serverProxy.purchaseRoute(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), route, numberOfWilds);
        clientModel.setState(NotYourTurn.getInstance());
    }

    public void acceptPlayerAction(ClientModel clientModel){}

    public void leaveGame(ClientModel clientModel){}
}
