package view.presenter;

import java.util.ArrayList;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.TrainCard;
import view.presenterInterface.IPlayersHandPresenter;

public class PlayersHandPresenter implements IPlayersHandPresenter {
    ClientModel clientModel = ClientModel.create();

    @Override
    public Integer getTrainCardAmount(String color) {
        Integer return_num = 0;
        ArrayList<TrainCard> trainCards = clientModel.getPlayer().getTrainCards();
        for (TrainCard trainCard: trainCards) {
            if (trainCard.getCardColor().equals(color)) {
                return_num+=1;
            }
        }
        return return_num;
    }

    @Override
    public ArrayList<DestinationCard> getDestinationCards() {
        return clientModel.getPlayer().getDestinationCards();
    }
}
