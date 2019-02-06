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
		Result Result = serverCommunicator.register(newUser);
		return Result;
	}

	public Result login(User returnUser) {
		this.serverCommunicator = new ServerProxy();
		Result Result = serverCommunicator.login(returnUser);
		return Result;
	}

	public ArrayList<String> getGameList() {
	    return null;
	}

	public Result loginUser(User returningUser) {
		return null;
	}
}
