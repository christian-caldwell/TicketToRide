package view.presenter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import models.data.DestinationCard;
import view.presenterInterface.IPlayersHandPresenter;

public class PlayersHandPresenter implements IPlayersHandPresenter, Observer {
    ClientModel clientModel = ClientModel.create();

    @Override
    public Integer getTrainCardAmount(Integer color) {

        Map<Integer, Integer> trainCards = clientModel.getPlayer().getTickets();
        return trainCards.get(color);
    }

    @Override
    public ArrayList<DestinationCard> getDestinationCards() {
        return clientModel.getPlayer().getDestinationCardHand();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
