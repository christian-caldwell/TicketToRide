package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.TrainCard;

public interface ICardDeckPresenter {
    Integer getTrainCardAtPosition(int num);
    void drawTrainCard(int num);
    void drawDestinationCard();
    Integer getDestinationCardsLeft();
    Integer getTrainCardsLeft();
}
