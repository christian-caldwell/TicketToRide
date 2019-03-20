package client.PlayerStates;

import client.ClientModel;

public class YourTurnAwaitingDestinations extends PlayerState {
    private static final YourTurnAwaitingDestinations ourInstance = new YourTurnAwaitingDestinations();

    public static YourTurnAwaitingDestinations getInstance() {
        return ourInstance;
    }

    private YourTurnAwaitingDestinations() {

    }
    public void returnDestinationCards(ClientModel clientModel){};
    public void acceptPlayerAction(ClientModel clientModel){};
}
