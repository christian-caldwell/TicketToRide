package view.presenterInterface;

import java.util.ArrayList;
import java.util.Set;

import models.data.Player;
import models.data.Route;

public interface IPlayerInfoPresenter {
    ArrayList<Player> getPlayers();
    Integer getTrainsLeft();
    Set<Route> getPurchasedRoutesFromPlayer(Integer playerColor);
    ArrayList<String> getDestinationCardStrings();
}
