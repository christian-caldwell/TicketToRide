package view.facade;

import java.util.ArrayList;

import models.data.User;
import models.data.Result;
import client.ServerProxy;

public class ViewFacade {
	private ServerProxy serverCommunicator = null;
	
	public ViewFacade() {
		serverCommunicator = new ServerProxy(this);
	}
	
	public Result register(User newUser) {
		Result Result = serverCommunicator.register(newUser);
		return Result;
	}

	public Result login(User returnUser) {
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
