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
            if (!clientModel.getUser().getGame().getFaceUpTrainCards()[cardNum-1].CardColor.equals(TTR_Constants.getInstance().WILD)) {
                ServerProxy serverProxy = new ServerProxy();
                User user = clientModel.getUser();
                clientModel.setState(NotYourTurn.getInstance());
                return serverProxy.requestTicketCard(user.getUsername(), user.getGame().getGameName(), cardNum, true);
            } else {
                return null;
            }
        } else {
            ServerProxy serverProxy = new ServerProxy();
            User user = clientModel.getUser();
            clientModel.setState(NotYourTurn.getInstance());
            return serverProxy.requestTicketCard(user.getUsername(), user.getGame().getGameName(), cardNum, true);
        }

    }
    public Result acceptPlayerAction(ClientModel clientModel){
        Result result = new Result();
        if(clientModel.getUser().getGame().isLastTurn()) {
            clientModel.setState(GameFinished.getInstance());
            result.setSuccessful(true);
        } else {
//            clientModel.setState(YourTurnDefault.getInstance());
            result.setSuccessful(true);
        }

        return result;
    };
}
