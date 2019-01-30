package presenters;

import java.util.Observable;
import java.util.Observer;

public class GamePresenter implements IGamePresenter, Observer {
    @Override
    public String getStatus() {
        return "Successfully logged in. game.Game has started.";
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
