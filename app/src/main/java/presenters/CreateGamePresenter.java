package presenters;

import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import models.Result;
import server.ServerProxy;
import viewInterfaces.ILobbyViewActivity;

public class CreateGamePresenter implements ICreateGamePresenter, Observer {
    private ILobbyViewActivity activity;
    private ServerProxy serverProxy = new ServerProxy();

    public CreateGamePresenter(ILobbyViewActivity activity) {
        this.activity = activity;
    }


    @Override
    public void createGame(String gameName) {
        Result result = serverProxy.createGame(gameName,"???");
        if (result.getErrorMessage().equals("")) {
            activity.onGameCreated();
        }
        else {
            activity.onCreateGameFailed(result.getErrorMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

/*
Where do I get the username?

 */