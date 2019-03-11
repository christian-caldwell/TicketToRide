package view.presenterInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

import models.data.Player;
import models.data.Route;

public interface IPlayerInfoPresenter {
    Player getCurrentTurn();
    ArrayList<Player> getPlayers();
    ArrayList<String> getNewDestinationCardStrings();
    Player getPlayerByOrder(int num);
    Integer getNumOfPlayers();
    Player getPlayer(String username);
    Integer getTrainsLeft();
    Set<Route> getPurchasedRoutesFromPlayer(Integer playerColor);
    ArrayList<String> getDestinationCardStrings();
}
