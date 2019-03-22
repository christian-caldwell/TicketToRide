package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import client.ClientModel;
import client.ServerProxy;
import models.TTR_Constants;
import models.data.DestinationCard;
import models.data.Player;
import models.data.Route;
import models.data.Result;
import view.presenterInterface.IPlayerInfoPresenter;

public class PlayerInfoPresenter implements IPlayerInfoPresenter, Observer {
    ClientModel clientModel = ClientModel.create();
    ServerProxy serverProxy = new ServerProxy();
    private GameBoardActivity boardActivity;
    TTR_Constants constants = TTR_Constants.getInstance();
    private ArrayList<String> listOfDestinationCardsToDiscard = new ArrayList<>();

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
    public Player getCurrentTurn() {
        return clientModel.getUser().getGame().findPlayerByColor(clientModel.getUser().getGame().getCurrentTurnPlayer());
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
    public Result returnDestinationCards() {
        switch (listOfDestinationCardsToDiscard.size()) {
            case 0:
                listOfDestinationCardsToDiscard.clear();
                return ClientModel.create().returnDestinationCards(null);
            case 1:
                DestinationCard[] destinationCards1 = new DestinationCard[1];
                for (int i = 0; i < listOfDestinationCardsToDiscard.size(); i++) {
                    String first = listOfDestinationCardsToDiscard.get(i).split(" to")[0];
                    String second = listOfDestinationCardsToDiscard.get(i).split("\n")[0].split("to ")[1];
                    destinationCards1[i] = constants.findDestinationCard(first, second);
                }
                listOfDestinationCardsToDiscard.clear();
                return ClientModel.create().returnDestinationCards(destinationCards1);

            case 2:
                DestinationCard[] destinationCards2 = new DestinationCard[2];
                for (int i = 0; i < listOfDestinationCardsToDiscard.size(); i++) {
                    String first = listOfDestinationCardsToDiscard.get(i).split(" to")[0];
                    String second = listOfDestinationCardsToDiscard.get(i).split("\n")[0].split("to ")[1];
                    destinationCards2[i] = constants.findDestinationCard(first, second);
                }
                listOfDestinationCardsToDiscard.clear();
                return ClientModel.create().returnDestinationCards(destinationCards2);
        }

        listOfDestinationCardsToDiscard.clear();
        return ClientModel.create().returnDestinationCards(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        new GameBoardActivity.UpdateAsyncTask(boardActivity).execute();

    }

    //TODO: KEEP TRACK OF TURNS IN CLIENT MODEL AND CHECK WHAT TURN IT IS
    public boolean addToListOfDestinationCardsToDiscard(String destinationCard) {
        // If this is the first turn,
        if (listOfDestinationCardsToDiscard.size() >= 1)
            return false;
        // else if it's the other turns then do something else
        //else if { (listOfDestinationCardsToDiscard.size() >= 1) }

        else {
            listOfDestinationCardsToDiscard.add(destinationCard);
            return true;
        }
    }

    public ArrayList<String> getListOfDestinationCardsToDiscard() {
        return listOfDestinationCardsToDiscard;
    }
}
