package view.presenterInterface;

import android.view.View;

import java.util.ArrayList;

import models.data.DestinationCard;
import models.data.Enums;
import models.data.Player;

public interface IPlayersHandPresenter {
    Player getCurrentPlayer();
    Integer getTrainCardAmount(Integer color);
    ArrayList<DestinationCard> getDestinationCards();
}
