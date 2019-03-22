package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;

public class YourTurnSecondDraw extends PlayerState {
    private static final YourTurnSecondDraw ourInstance = new YourTurnSecondDraw();
    ServerProxy serverProxy = new ServerProxy();
    public static YourTurnSecondDraw getInstance() {
        return ourInstance;
    }

    private YourTurnSecondDraw() {
    }

    public void requestTicketCard(ClientModel clientModel, int cardNum){
        serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), cardNum, false);
        clientModel.setState(NotYourTurn.getInstance());
    }

    public void acceptPlayerAction(ClientModel clientModel){}
    public void leaveGame(ClientModel clientModel){}
}
