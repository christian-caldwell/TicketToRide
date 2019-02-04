package presenters;

import java.util.ArrayList;

import models.User;
import models.Result;
import server.ServerProxy;

public class ViewFacade {
	private ServerProxy serverCommunicator = null;
	
	public ViewFacade() {
		
	}
	
	public Result register(User newUser) {
		this.serverCommunicator = new ServerProxy();
		Result Result = serverCommunicator.register(newUser.getUsername(),newUser.getPassword());
		return Result;
	}

	public Result login(User returningUser) {
		this.serverCommunicator = new ServerProxy();
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
