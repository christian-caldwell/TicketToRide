package view.presenterInterface;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.Enums;
import models.data.TrainCard;

public interface ICardDeckPresenter {
    Enums.Color drawTrainCard();
    TrainCard drawTrainCard(int cardNum);
    ArrayList<DestinationCard> drawDestinationCard();
    Integer getDestinationCardsLeft();
    Integer getTrainCardsLeft();
}
