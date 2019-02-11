package view.facade.client;

import client.ClientModel;
import models.data.User;
import models.data.Game;

public class ClientFacade {
	private ClientModel client;

	public ClientFacade () {
		this.client = ClientModel.create();
	}

	public void waitingForGame(Game game) {
		client.addWaitingGame(game);
	}

	public void startGame(Game game) {
		client.removeWaitingGame(game);
		client.addActiveGame(game);
	}

	public void setUser(User newUser) {
		client.setPlayer(newUser);
	}
}
