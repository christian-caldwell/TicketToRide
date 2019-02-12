package view.facade.client;

import java.util.ArrayList;

import client.ClientModel;
import models.data.User;
import models.data.Game;

public class ClientFacade {
	private ClientModel client;

	public ClientFacade () {
		this.client = ClientModel.create();
	}

	public void joinGame(Game game) {
		client.setActiveGame(game);
	}

	public ArrayList<Game> getGames() {
		return client.getLobbyGames();
	}
	public void setUser(User newUser) {
		client.setPlayer(newUser);
	}

	public String getHost() {
		return this.client.getPlayer().getUsername();
	}

	public User getPlayer() {
		return client.getPlayer();
	}
}
