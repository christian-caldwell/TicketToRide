package client.PlayerStates;

import client.ClientModel;
import models.data.Route;

public class PlayerState {
    public void initializeGame(ClientModel clientModel){}
    public void requestTicketCard(ClientModel clientModel, int cardNum){}
    public void requestDestinationCards(ClientModel clientModel){}
    public void returnDestinationCards(ClientModel clientModel){}
    public void postChatMessage(ClientModel clientModel){}
    public void purchaseRoute(ClientModel clientModel, Route route, int numberOfWilds){}
    public void acceptPlayerAction(ClientModel clientModel){}
    public void leaveGame(ClientModel clientModel){}
}
