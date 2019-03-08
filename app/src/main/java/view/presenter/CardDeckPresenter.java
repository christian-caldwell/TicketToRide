package view.presenter;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.Enums;
import models.data.TrainCard;
import view.presenterInterface.ITrainCardDeckPresenter;

public class CardDeckPresenter implements ITrainCardDeckPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    @Override
    public Enums.Color drawTrainCard() {
        ArrayList<Enums.Color> valuesList = new ArrayList<Enums.Color>(clientModel.getUser().getGame().getTicketCardDeck().keySet());
        int randomIndex = new Random().nextInt(valuesList.size());
        Enums.Color randomValue = valuesList.get(randomIndex);
        clientModel.incrementTicketCardHand(randomValue);
        return randomValue;
    }

    @Override
    public TrainCard drawTrainCard(int cardNum) {
        //TODO make sure that this line is correct, currently not sure what this is doing and why
        //return clientModel.getUser().getGame().getFaceUpTrainCards().get(cardNum);
        return null;
    }


    @Override
    public ArrayList<DestinationCard> drawDestinationCard() {
        ArrayList<DestinationCard> cardsToReturn = new ArrayList<>();
        for (int i = 0; i < 3; i++)  {
            //TODO make sure that this line is correct, currently not sure what this is doing and why
            //cardsToReturn.add(clientModel.getUser().getGame().getDestinationCards().remove());
        }
        return cardsToReturn;
    }

    @Override
    public Integer getDestinationCardsLeft() {
        return clientModel.getUser().getGame().getDestinationDeck().size();
    }

    @Override
    public Integer getTrainCardsLeft() {
        return clientModel.getUser().getGame().getTicketCardDeck().values().size();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
