package view.presenter;

import java.util.ArrayList;
import java.util.Map;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.Enums;
import view.presenterInterface.IPlayersHandPresenter;

public class PlayersHandPresenter implements IPlayersHandPresenter {
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
}
