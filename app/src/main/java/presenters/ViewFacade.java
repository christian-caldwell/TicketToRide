package presenters;

import java.util.ArrayList;

import game.User;
import results.Result;
import server.serverProxy;

public class ViewFacade {
	private serverProxy serverCommunicator = null;
	
	public ViewFacade() {
		
	}
	
	public Result register(User newUser) {
		this.serverCommunicator = new serverProxy();
		Result Result = serverCommunicator.register(newUser.getUsername(),newUser.getPassword());
		return Result;
	}
	
	public Result login(User returningUser) {
		this.serverCommunicator = new serverProxy();
		Result Result = serverCommunicator.login(returningUser.getUsername(), returningUser.password());
		return Result;
	}

	public ArrayList<String> getGameList() {
	    return null;
	}

	public Result loginUser(User returningUser) {
		return null;
	}
}
