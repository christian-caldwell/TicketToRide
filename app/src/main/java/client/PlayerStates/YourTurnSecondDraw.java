package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
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
        ServerProxy serverProxy = new ServerProxy();
        Result result = new Result();
        User user = clientModel.getUser();
        result = serverProxy.requestTicketCard(user.getUsername(), user.getGame().getGameName(), cardNum, true);
        return result;
    };
    public Result acceptPlayerAction(ClientModel clientModel){
        Result result = new Result();
        if(clientModel.getUser().getGame().isLastTurn()) {
            clientModel.setState(GameFinished.getInstance());
            result.setSuccessful(true);
        }
        else {
            clientModel.setState(YourTurnDefault.getInstance());
            result.setSuccessful(true);
        }

        return result;
    };
}
