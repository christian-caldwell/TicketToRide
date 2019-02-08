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
    private static PollManager pollManager = new PollManager();

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

    public void updateLogin(Request request) {
        pollManager.setUser(request.getUser());
    }
    public void updateRegister(Request request) {
        pollManager.setUser(request.getUser());
    }
    public void updateCreateGame(Request request) {
        pollManager.setGame(data.getGame(request.getGame().getGameName()));
        pollManager.setUser(request.getUser());
    }
    public void updateJoinGame(Request request) {
        pollManager.setUser(request.getUser());
        pollManager.setGame(request.getGame());
    }
    public void updateStartGame(Request request) {
        pollManager.setGame(data.getGame(request.getGame().getGameName()));
    }
}
