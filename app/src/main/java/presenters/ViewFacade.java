package presenters;

import game.User;
import server.serverProxy;

public class ViewFacade {
	private serverProxy serverCommunicator = null;
	
	public ViewFacade(ServerProxy serverComm) {
		this.serverCommunicator = serverComm;
	}
	
	public String register(User newUser) {
		String authToken = serverCommunicator.register(newUser.getUsername(),newUser.getPassword());
		return authToken;
	}
	
	public String login(User returningUser) {
		String authToken = serverCommunicator.login(returningUser.getUsername(), returningUser.password());
		return authToken;
	}
}
