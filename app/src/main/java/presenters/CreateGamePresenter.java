package presenters;

import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import results.createGameResult;
import server.serverProxy;

public class CreateGamePresenter implements ICreateGamePresenter, Observer {
    private ICreateGameActivity activity;
    private serverProxy serverProxy = new serverProxy();

    public CreateGamePresenter(ICreateGameActivity activity) {
        this.activity = activity;
    }


    @Override
    public void createGame(String gameName) {
        createGameResult createGameResult = serverProxy.createGame(gameName, 5, "???");
        if (createGameResult.getErrorMessage().equals("")) {
            //activity.returnToLobby();
        }
        else {
            //activity.reportError(createGameResult.getErrorMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

/*
Where do I get the username?

 */