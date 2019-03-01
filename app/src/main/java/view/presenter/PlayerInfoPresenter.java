package view.presenter;

import java.util.ArrayList;

import client.ClientModel;
import models.data.Player;
import view.presenterInterface.IPlayerInfoPresenter;

public class PlayerInfoPresenter implements IPlayerInfoPresenter {
    ClientModel clientModel = ClientModel.create();

    @Override
    public ArrayList<Player> getPlayers() {
        return clientModel.getUser().getGame().getPlayers();
    }
    @Override
    public Integer getTrainsLeft() {
        return clientModel.getPlayer().getTrainsRemaining();
    }
}
