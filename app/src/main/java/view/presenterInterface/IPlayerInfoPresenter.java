package view.presenterInterface;

import java.util.ArrayList;

import models.data.Player;

public interface IPlayerInfoPresenter {
    ArrayList<Player> getPlayers();
    Integer getTrainsLeft();
    ArrayList<String> getDestinationCardStrings();
}
