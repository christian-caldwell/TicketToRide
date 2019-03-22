package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.data.Game;
import models.data.Result;
import view.facade.client.out.GameStartFacadeOut;

public class NotYourTurn extends PlayerState {
    private static final NotYourTurn ourInstance = new NotYourTurn();

    public static NotYourTurn getInstance() {
        return ourInstance;
    }

    private NotYourTurn() {

    }

    public Result acceptPlayerAction(ClientModel clientModel){
        Game game = clientModel.getUser().getGame();
        Result result = new Result();
        result.setSuccessful(false);
        if (clientModel.getUser().getGame().isLastTurn()) {
            clientModel.setState(GameFinished.getInstance());
            result.setSuccessful(true);
        }
        else {
            result.setSuccessful(true);
            if (game.findPlayerByColor(game.getCurrentTurnPlayer()).getUsername().equals(clientModel.getUser().getUsername())) {
                clientModel.setState(YourTurnDefault.getInstance());
            }
        }
        return result;
    };

}
