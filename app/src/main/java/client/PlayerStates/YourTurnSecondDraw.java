package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.TTR_Constants;
import models.data.Result;
import models.data.User;

public class YourTurnSecondDraw extends PlayerState {
    private static final YourTurnSecondDraw ourInstance = new YourTurnSecondDraw();

    public static YourTurnSecondDraw getInstance() {
        return ourInstance;
    }

    private YourTurnSecondDraw() {
    }
    public Result requestTicketCard(ClientModel clientModel, int cardNum){
        if(cardNum != 0) {
            if (!clientModel.getUser().getGameJoined().getFaceUpTrainCards()[cardNum-1].CardColor.equals(TTR_Constants.getInstance().WILD)) {
                ServerProxy serverProxy = new ServerProxy();
                User user = clientModel.getUser();
                clientModel.setState(NotYourTurn.getInstance());
                return serverProxy.requestTicketCard(user.getUsername(), user.getGameJoined().getGameName(), cardNum, true);
            }
        } else {
            ServerProxy serverProxy = new ServerProxy();
            User user = clientModel.getUser();
            clientModel.setState(NotYourTurn.getInstance());
            return serverProxy.requestTicketCard(user.getUsername(), user.getGameJoined().getGameName(), cardNum, true);
        }
        Result result = new Result();
        result.setErrorMessage("cannot draw a wild card.");
        result.setSuccessful(false);
        return result;
    }
    public Result acceptPlayerAction(ClientModel clientModel){
        Result result = new Result();
        if(clientModel.getUser().getGameJoined().isLastTurn()) {
            clientModel.setState(GameFinished.getInstance());
            result.setSuccessful(true);
        } else {
//            clientModel.setState(YourTurnDefault.getInstance());
            result.setSuccessful(true);
        }

        return result;
    };
}
