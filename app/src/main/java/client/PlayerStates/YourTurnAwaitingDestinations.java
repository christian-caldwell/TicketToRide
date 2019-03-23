package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.data.DestinationCard;
import models.data.Result;

public class YourTurnAwaitingDestinations extends PlayerState {
    private static final YourTurnAwaitingDestinations ourInstance = new YourTurnAwaitingDestinations();

    public static YourTurnAwaitingDestinations getInstance() {
        return ourInstance;
    }

    private YourTurnAwaitingDestinations() {

    }
    public Result returnDestinationCards(ClientModel clientModel, DestinationCard[] destinationCards){
        ServerProxy serverProxy = new ServerProxy();
        //FIXME: this may need to be removed depending on what is going on with the acceptPlayerAction function.
        clientModel.setState(NotYourTurn.getInstance());
        return serverProxy.returnDestinationCards(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), destinationCards);
    };
    public Result acceptPlayerAction(ClientModel clientModel){
        Result result = new Result();
        if(clientModel.getUser().getGame().isLastTurn()) {
            clientModel.setState(GameFinished.getInstance());
            result.setSuccessful(true);
        }
        else {
            clientModel.setState(NotYourTurn.getInstance());
            result.setSuccessful(true);
        }
        return result;
    };
}
