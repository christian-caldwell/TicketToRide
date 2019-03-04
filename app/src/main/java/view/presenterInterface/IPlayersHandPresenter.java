package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.Enums;

public interface IPlayersHandPresenter {
    Integer getTrainCardAmount(Enums.Color color);
    ArrayList<DestinationCard> getDestinationCards();
}
