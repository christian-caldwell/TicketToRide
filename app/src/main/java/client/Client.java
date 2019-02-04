package client;

import models.Request;
import models.User;
import models.Result;

public class Client implements IClient {
    public Result send(String serverHost, String serverPort, Request request) {
        return new Result();
    }
    
    @Override
    public void updateClient() {

    }

    @Override
    public void join() {

    }

    @Override
    public void create() {

    }

	@Override
	public void updateAuthToken(String newAuthToken) {
		
	}

	@Override
	public String passAuthToken() {
		
		return null;
	}
	@Override
	public void setUserValues(User newUser) {
		
	}
}
