package client.PlayerStates;

import client.ClientModel;
import view.facade.client.out.GameStartFacadeOut;

public class NotInGame extends PlayerState {
    private static final NotInGame ourInstance = new NotInGame();

    public static NotInGame getInstance() {
        return ourInstance;
    }

    private NotInGame() {
    }

    public void initializeGame(ClientModel clientModel){
        if(clientModel.getUser().isHost()) {
            GameStartFacadeOut gameStartFacadeOut = new GameStartFacadeOut();
            gameStartFacadeOut.startGame(clientModel.getUser().getGame().getGameName());
        }
        clientModel.setState(NotYourTurn.getInstance());
    };
    public void requestTicketCard(ClientModel clientModel){};
    public void requestDestinationCards(ClientModel clientModel){};
    public void returnDestinationCards(ClientModel clientModel){};
    public void postChatMessage(ClientModel clientModel){};
    public void purchaseRoute(ClientModel clientModel){};
    public void acceptPlayerAction(ClientModel clientModel){};
    public void leaveGame(ClientModel clientModel){};
}
