package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import client.ServerProxy;
import models.data.Player;
import view.presenterInterface.ICardDeckPresenter;

public class CardDeckPresenter implements ICardDeckPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    ServerProxy serverProxy = new ServerProxy();
    Boolean secondTurn = false;
    private GameBoardActivity boardActivity;

    public CardDeckPresenter(GameBoardActivity activity) {
        boardActivity = activity;
        clientModel.setmCardDeckPresenter(this);
    }


    @Override
    public void drawTrainCard(int cardNum) {

//        public Result purchaseRoute (String userName, String gameName, Route purchasedRoute)
//        public Result returnDestinationCards (String userName, String gameName, DestinationCard[] returnedCards)
        //FIXME: needs to be updated if they chose a rainbow card.
        //Player player = clientModel.getUser().getGame().findPlayer(clientModel.getUser().getUsername());
        serverProxy.requestTicketCard(clientModel.getUser().getUsername(), clientModel.getUser().getGame().getGameName(), cardNum, secondTurn);
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
        new GameBoardActivity.UpdateAsyncTask().execute();
    }
}
