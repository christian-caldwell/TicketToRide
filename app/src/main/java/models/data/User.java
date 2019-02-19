package models.data;

public class User {
    private String username;
    private String pass;
    private Boolean host;
    private Game gameJoined;


    public User(String username, String password) {
        this.username = username;
        this.pass = password;
        this.host = false;
        this.gameJoined = new Game();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return pass;
    }

    public void setGameJoined(Game gameJoined) {
        this.gameJoined = gameJoined;
    }



    public Game getGame() {
        return gameJoined;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(Boolean host) {
        this.host = host;
    }
}
