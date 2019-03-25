package view.presenter;


import android.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import client.ClientModel;
import models.TTR_Constants;
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


    // first object in pair are the points gained from destination cards second is points lost from destination cards.
    @Override
    public Pair<Integer, Integer> getDestinationPoints(Player player) {
        Integer pointsGained = 0;
        Integer pointsLost = 0;
        for (DestinationCard destinationCard: player.getDestinationCardHand()) {
            String location1 = destinationCard.getLocations()[0];
            String location2 = destinationCard.getLocations()[1];
            if (TTR_Constants.getInstance().graph.pathExists(location1,location2,player)) {
                pointsGained += destinationCard.getPoints();
            }
            else {
                pointsLost += destinationCard.getPoints();
            }
        }
        return new Pair<>(pointsGained,pointsLost);
    }
}
