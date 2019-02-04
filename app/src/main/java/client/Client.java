package client;

import game.User;

public class Client implements IClient {
    public ClientResult send(String serverHost, String serverPort, ClientRequest request) {
        return new ClientResult();
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
