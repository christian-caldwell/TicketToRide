package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import client.ClientModel;
import models.TTR_Constants;
import models.data.DestinationCard;
import models.data.Player;
import models.data.Route;
import view.presenterInterface.IPlayerInfoPresenter;

public class PlayerInfoPresenter implements IPlayerInfoPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    private GameBoardActivity boardActivity;
    TTR_Constants constants = TTR_Constants.getInstance();

    public PlayerInfoPresenter(GameBoardActivity activity) {
        boardActivity = activity;
        clientModel.setmPlayerInfoPresenter(this);
    }

    @Override
    public Set<Route> getPurchasedRoutesFromPlayer(Integer playerColor) {
        return clientModel.getUser().getGame().findPlayerByColor(playerColor).getRoutesOwned();
    }

    @Override
    public Player getPlayerByOrder(int num) {
        Integer searchColor;
        switch (num) {
            case 0:
              searchColor = constants.BLACK_PLAYER;
              break;
            case 1:
                searchColor = constants.BLUE_PLAYER;
                break;
            case 2:
                searchColor = constants.RED_PLAYER;
                break;
            case 3:
                searchColor = constants.GREEN_PLAYER;
                break;
            case 4:
                searchColor = constants.YELLOW_PLAYER;
                break;
            default:
                return null;
        }
        return clientModel.getUser().getGame().findPlayerByColor(searchColor);
    }

    @Override
    public Integer getNumOfPlayers() {
        return clientModel.getUser().getGame().getPlayers().size();
    }

    @Override
    public Player getPlayer(String username) {
        return clientModel.getUser().getGame().findPlayer(username);
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
    public ArrayList<Player> getPlayers() {
        return clientModel.getUser().getGame().getPlayers();
    }

    @Override
    public ArrayList<String> getNewDestinationCardStrings() {
        ArrayList<String> destinationStrings = new ArrayList<>();
        String username = clientModel.getUser().getUsername();
        Player player = clientModel.getUser().getGame().findPlayer(username);
        for (int i = 0; i < player.getNewDestinationCards().size(); i++) {
            DestinationCard destinationCard = player.getNewDestinationCards().get(i);
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
