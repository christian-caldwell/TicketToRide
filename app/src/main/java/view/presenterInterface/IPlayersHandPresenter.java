package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;

public interface IPlayersHandPresenter {
    Integer getTrainCardAmount(String color);
    ArrayList<DestinationCard> getDestinationCards();
}
