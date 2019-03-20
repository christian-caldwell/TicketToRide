package client.PlayerStates;

import client.ClientModel;

public class PlayerState {
    public void initializeGame(ClientModel clientModel){};
    public void requestTicketCard(ClientModel clientModel){};
    public void requestDestinationCards(ClientModel clientModel){};
    public void returnDestinationCards(ClientModel clientModel){};
    public void postChatMessage(ClientModel clientModel){};
    public void purchaseRoute(ClientModel clientModel){};
    public void acceptPlayerAction(ClientModel clientModel){};
    public void leaveGame(ClientModel clientModel){};
}
