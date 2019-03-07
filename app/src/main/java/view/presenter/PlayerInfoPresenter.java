package view.presenter;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.Player;
import view.presenterInterface.IPlayerInfoPresenter;

public class PlayerInfoPresenter implements IPlayerInfoPresenter, Observer {
    ClientModel clientModel = ClientModel.create();

    @Override
    public ArrayList<Player> getPlayers() {
        return clientModel.getUser().getGame().getPlayers();
    }
    @Override
    public Integer getTrainsLeft() {
        return clientModel.getPlayer().getTrainsRemaining();
    }

    @Override
    public ArrayList<String> getDestinationCardStrings() {
        ArrayList<String> destinationStrings = new ArrayList<>();
        for (int i = 0; i < clientModel.getPlayer().getDestinationCardHand().size(); i++) {
            DestinationCard destinationCard = clientModel.getPlayer().getDestinationCardHand().get(i);
            Pair locations = destinationCard.getLocations();
            String stringToAdd = locations.first + " to " + locations.second + "\npoints: " + destinationCard.getPoints();
            destinationStrings.add(stringToAdd);
        }
        return destinationStrings;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
