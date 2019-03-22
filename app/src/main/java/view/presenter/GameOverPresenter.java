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
    public Integer getDestinationPointsGained() {
        return 1;
    }

    @Override
    public Integer getDestinationPoinntsLost() {
        return 3;
    }
}
