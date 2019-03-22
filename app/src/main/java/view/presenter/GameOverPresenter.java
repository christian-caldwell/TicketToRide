package view.presenter;

import java.util.ArrayList;

import client.ClientModel;
import models.data.Player;
import view.presenterInterface.IGameOverPresenter;

public class GameOverPresenter implements IGameOverPresenter {

    ClientModel clientModel = ClientModel.create();
    @Override
    public ArrayList<Player> getPlayersInWinningOrder() {
        return clientModel.getUser().getGame().getPlayers();
    }

    @Override
    public Integer getDestinationPointsGained(Player player) {
        return player.getDestinationCardHand().get(1).getPoints();
    }

    @Override
    public Integer getDestinationPointsLost(Player player) {
        return 3;
    }
}
