package view.presenterInterface;

import java.util.ArrayList;

import models.data.Player;

public interface IGameOverPresenter {
    public ArrayList<Player> getPlayersInWinningOrder();
    public Integer getDestinationPointsGained(Player player);
    public Integer getDestinationPointsLost(Player player);
}
