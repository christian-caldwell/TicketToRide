package client.PlayerStates;

public class GameFinished extends PlayerState{
    private static final GameFinished ourInstance = new GameFinished();

    public static GameFinished getInstance() {
        return ourInstance;
    }

    private GameFinished() {
    }
}
