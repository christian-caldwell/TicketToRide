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

	public Game getActiveGame() {
		return client.getActiveGame();
	}

	public ArrayList<Game> getGames() {
		return client.getLobbyGamesList();
	}
	public void setUser(User newUser) {
		client.setPlayer(newUser);
	}

	public User getPlayer() {
		return this.client.getPlayer();
	}
}
