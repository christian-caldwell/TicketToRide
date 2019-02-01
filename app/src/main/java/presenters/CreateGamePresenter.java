package presenters;

import java.util.Observable;
import java.util.Observer;

public class CreateGamePresenter implements ICreateGamePresenter, Observer {

    private ICreateGameView createGameView;

    public CreateGamePresenter(ICreateGameView createGameView) {
        this.createGameView = createGameView;
    }

    @Override
    public void createGame(String gameName) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
