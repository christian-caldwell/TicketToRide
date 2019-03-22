package view.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.Player;
import models.data.Route;
import view.presenterInterface.IGameOverPresenter;

public class GameOverPresenter implements IGameOverPresenter {

    ClientModel clientModel = ClientModel.create();
    @Override
    public ArrayList<Player> getPlayersInWinningOrder() {
        return clientModel.getUser().getGame().getPlayers();
    }

    @Override
    public Integer getDestinationPointsGained(Player player) {
        Set<Route> routes = player.getRoutesOwned();
        ArrayList<DestinationCard> destinationCards = player.getDestinationCardHand();
        for (DestinationCard destinationCard: destinationCards) {
            String location1 = destinationCard.getLocations()[0];
            String location2 = destinationCard.getLocations()[1];

        }
        return player.getDestinationCardHand().get(1).getPoints();
    }

    @Override
    public Integer getDestinationPointsLost(Player player) {
        return 3;
    }
}
