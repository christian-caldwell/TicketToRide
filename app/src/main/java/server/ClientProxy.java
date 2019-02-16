package server;

import java.util.HashMap;
import java.util.Map;

import client.IClient;
import models.data.Game;
import models.data.Request;
import models.data.User;

public class ClientProxy implements IClient {
    private static ServerCommands serverCommands = new ServerCommands();
    private static ServerData data = serverCommands.getServerData();

    @Override
    public void updateClient() {

    }

    @Override
    public void join() {

    }

    @Override
    public void create() {

    }

    @Override
    public void updateAuthToken(String newAuthToken) {

    }

    @Override
    public String passAuthToken() {

        return null;
    }

    @Override
    public void setUserValues(User newUser) {

    }

    public void updateCreateGame(String gameName) {
        //PollManager manager = PollManager.create();
       // manager.setGame(data.getGame(gameName));
    }
    public void updateJoinGame(Game game) {

       // PollManager manager = PollManager.create();
       // manager.setGame(game);
    }
    public void updateStartGame(String gameName) {

      //  PollManager manager = PollManager.create();
       // manager.setGame(data.getGame(gameName));
    }
}
