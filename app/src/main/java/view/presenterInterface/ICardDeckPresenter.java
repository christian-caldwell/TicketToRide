package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.Enums;
import models.data.TrainCard;

public interface ICardDeckPresenter {
    void drawTrainCard(int num);
    void drawDestinationCard();
    Integer getDestinationCardsLeft();
    Integer getTrainCardsLeft();
}
