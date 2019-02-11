package view.facade.client;

import client.ClientModel;
import models.data.User;
import models.data.Game;
import java.util.ArrayList;

public class ClientFacade {
	private ClientModel client;

	public ClientFacade () {
		this.client = ClientModel.create();
	}

	public void joinGame(Game game) {
		client.setActiveGame(game);
	}

	public void setUser(User newUser) {
		client.setPlayer(newUser);
	}

	public User getPlayer() {
		return this.client.getPlayer();
	}

	public void setLobbyList(ArrayList<Game> newLobbyList) {
		this.client.setLobbyGames(newLobbyList);
	}
}
