package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.TTR_Constants;
import models.data.Result;
import models.data.Route;
import models.data.User;

public class YourTurnDefault extends PlayerState {
    private static final YourTurnDefault ourInstance = new YourTurnDefault();
    ServerProxy serverProxy = new ServerProxy();
    public static YourTurnDefault getInstance() {
        return ourInstance;
    }

    private YourTurnDefault() {
    }

    public Result requestTicketCard(ClientModel clientModel, int cardNum) {
        if(cardNum != 0) {
            if(clientModel.getUser().getGameJoined().getFaceUpTrainCards()[cardNum-1].CardColor.equals(TTR_Constants.getInstance().WILD)) {
                Result result = new Result();
                result = serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGameJoined().getGameName(), cardNum, false);
                clientModel.setState(NotYourTurn.getInstance());
                return result;
            }
        }
        Result result = new Result();
        result = serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGameJoined().getGameName(), cardNum, false);
        clientModel.setState(YourTurnSecondDraw.getInstance());
        return result;
    }

    public Result requestDestinationCards(ClientModel clientModel) {
        Result result = new Result();
        result = serverProxy.requestDestinationCards(clientModel.getUser().getUsername(), clientModel.getUser().getGameJoined().getGameName());
        clientModel.setState(YourTurnAwaitingDestinations.getInstance());
        return result;
    }

    public Result purchaseRoute(ClientModel clientModel, Route route, int numberOfWilds, int colorUsed) {
        Result result = new Result();
        result = serverProxy.purchaseRoute(clientModel.getUser().getUsername(), clientModel.getUser().getGameJoined().getGameName(), route, numberOfWilds, colorUsed);
        if (result.isSuccessful()) {
            clientModel.setState(NotYourTurn.getInstance());
        }

        return result;
    }

//    public Result acceptPlayerAction(ClientModel clientModel){}
//
//    public Result leaveGame(ClientModel clientModel){}
}
