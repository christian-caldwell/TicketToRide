package client.PlayerStates;

public class YourTurnDefault extends PlayerState {
    private static final YourTurnDefault ourInstance = new YourTurnDefault();

    public static YourTurnDefault getInstance() {
        return ourInstance;
    }

    private YourTurnDefault() {
    }
}
