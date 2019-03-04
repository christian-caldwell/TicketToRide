package view.presenter;

import java.util.Observable;
import java.util.Observer;

import view.presenterInterface.IGamePresenter;

public class GamePresenter implements IGamePresenter, Observer {



    @Override
    public String getStatus() {
        return "Successfully logged in. models.data.Game has started.";
    }

    @Override
    public void update(Observable o, Object arg) {
        //do nothing
    }
}
