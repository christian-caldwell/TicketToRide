package view.presenterInterface;

import java.util.ArrayList;

import models.data.Player;

public interface IPlayerInfoPresenter {
    ArrayList<String> getNewDestinationCardStrings();
    Player getPlayerByOrder(int num);
    Integer getNumOfPlayers();
    Player getPlayer(String username);
    Integer getTrainsLeft();
    ArrayList<String> getDestinationCardStrings();
}
