package com.example.testingpurposes.presenters;

import com.example.testingpurposes.game.User;
import com.example.testingpurposes.server.serverProxy;

public class ViewFacade {
	private serverProxy serverCommunicator = null;
	
	public ViewFacade() {
		
	}
	
	public String register(User newUser) {
		this.serverCommunicator = new ServerProxy();
		String authToken = serverCommunicator.register(newUser.getUsername(),newUser.getPassword());
		return authToken;
	}
	
	public String login(User returningUser) {
		this.serverCommunicator = new ServerProxy();
		String authToken = serverCommunicator.login(returningUser.getUsername(), returningUser.password());
		return authToken;
	}
}
