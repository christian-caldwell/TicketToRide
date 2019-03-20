package client.PlayerStates;

import client.ClientModel;
import client.ServerProxy;
import models.data.Game;
import view.facade.client.out.GameStartFacadeOut;

public class NotYourTurn extends PlayerState {
    private static final NotYourTurn ourInstance = new NotYourTurn();

    public static NotYourTurn getInstance() {
        return ourInstance;
    }

    private NotYourTurn() {

    }

    public void acceptPlayerAction(ClientModel clientModel){
        Game game = clientModel.getUser().getGame();
        if (game.findPlayerByColor(game.getCurrentTurnPlayer()).getUsername().equals(clientModel.getUser().getUsername())) {
            clientModel.setState(YourTurnDefault.getInstance());
        }
    };

}
