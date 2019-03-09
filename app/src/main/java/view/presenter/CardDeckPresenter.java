package view.presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import client.ServerProxy;
import view.presenterInterface.ICardDeckPresenter;
import models.data.DestinationCard;
import models.data.TrainCard;
import view.presenterInterface.ITrainCardDeckPresenter;

public class CardDeckPresenter implements ICardDeckPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    ServerProxy serverProxy = new ServerProxy();
    Boolean secondTurn = false;

    @Override
    public void drawTrainCard(int cardNum) {

//        public Result purchaseRoute (String userName, String gameName, Route purchasedRoute)
//        public Result returnDestinationCards (String userName, String gameName, DestinationCard[] returnedCards)
        //FIXME: needs to be updated if they chose a rainbow card.
       // serverProxy.requestTicketCard(clientModel.getPlayer().getUsername(), clientModel.getUser().getGame(), cardNum, secondTurn);
        if (secondTurn) {
            secondTurn = false;
        }
        else {
            secondTurn = true;
        }
    }

    @Override
    public void drawDestinationCard() {
        serverProxy.requestDestinationCards(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName());
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
