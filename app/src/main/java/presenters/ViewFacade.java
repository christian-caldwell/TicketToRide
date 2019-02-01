package presenters;

import java.util.ArrayList;

import game.User;
import results.signInResult;
import server.serverProxy;

public class ViewFacade {
	private serverProxy serverCommunicator = null;
	
	public ViewFacade() {
		
	}
	
	public signInResult register(User newUser) {
		this.serverCommunicator = new serverProxy();
		signInResult signInResult = serverCommunicator.register(newUser.getUsername(),newUser.getPassword());
		return signInResult;
	}
	
	public signInResult login(User returningUser) {
		this.serverCommunicator = new serverProxy();
		signInResult signInResult = serverCommunicator.login(returningUser.getUsername(), returningUser.password());
		return signInResult;
	}

	public ArrayList<String> getGameList() {
	    return null;
	}
}
