package view.presenterInterface;

import java.util.ArrayList;

import models.data.Player;

public interface IGameOverPresenter {
    public ArrayList<Player> getPlayersInWinningOrder();
    public Integer getDestinationPointsGained();
    public Integer getDestinationPoinntsLost();
}
