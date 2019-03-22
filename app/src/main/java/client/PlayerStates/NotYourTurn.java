package client.PlayerStates;

import client.ClientModel;
import models.data.Route;

public class NotYourTurn extends PlayerState {
    private static final NotYourTurn ourInstance = new NotYourTurn();

    public static NotYourTurn getInstance() {
        return ourInstance;
    }

    private NotYourTurn() {
    }

    public void requestTicketCard(ClientModel clientModel){};
    public void requestDestinationCards(ClientModel clientModel){};
    public void returnDestinationCards(ClientModel clientModel){};
    public void postChatMessage(ClientModel clientModel){};
    public void purchaseRoute(ClientModel clientModel, Route route, int numberOfWilds){};
    public void acceptPlayerAction(ClientModel clientModel){};
    public void leaveGame(ClientModel clientModel){};

}
