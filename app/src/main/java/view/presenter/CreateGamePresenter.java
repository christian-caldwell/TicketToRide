package view.presenter;

import java.util.Observable;
import java.util.Observer;

import models.data.Result;
import client.ServerProxy;
import models.data.User;
import view.presenterInterface.ICreateGamePresenter;
import view.activityInterface.ILobbyViewActivity;

public class CreateGamePresenter implements ICreateGamePresenter, Observer {
    private ILobbyViewActivity activity;
    private ServerProxy serverProxy = new ServerProxy();

    public CreateGamePresenter(ILobbyViewActivity activity) {
        this.activity = activity;
    }


    @Override
    public void createGame(String gameName, User user) {
        Result result = serverProxy.createGame(gameName,user);
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