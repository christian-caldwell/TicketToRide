package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;

public interface IPlayersHandPresenter {
    Integer getTrainCardAmount(Integer color);
    ArrayList<DestinationCard> getDestinationCards();
}
