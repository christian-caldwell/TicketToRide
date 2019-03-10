package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import client.ClientModel;
import models.data.DestinationCard;
import models.data.Player;
import models.data.Route;
import view.presenterInterface.IPlayerInfoPresenter;

public class PlayerInfoPresenter implements IPlayerInfoPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    private GameBoardActivity boardActivity;

    public PlayerInfoPresenter(GameBoardActivity activity) {
        boardActivity = activity;
        clientModel.setmPlayerInfoPresenter(this);
    }

    @Override
    public Set<Route> getPurchasedRoutesFromPlayer(Integer playerColor) {
        //clientModel.getUser().getGame()
        return null;
    }

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
        String username = clientModel.getUser().getUsername();
        Player player = clientModel.getUser().getGame().findPlayer(username);
        for (int i = 0; i < player.getDestinationCardHand().size(); i++) {
            DestinationCard destinationCard = player.getDestinationCardHand().get(i);
            String[] location_strings = destinationCard.getLocations();
            String stringToAdd = location_strings[0] + " to " + location_strings[1] + "\npoints: " + destinationCard.getPoints();
            destinationStrings.add(stringToAdd);
        }
        return destinationStrings;
    }

    @Override
    public void update(Observable o, Object arg) {
        new GameBoardActivity.UpdateAsyncTask().execute();

    }
}
