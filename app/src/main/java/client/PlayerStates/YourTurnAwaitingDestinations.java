package client.PlayerStates;

public class YourTurnAwaitingDestinations extends PlayerState {
    private static final YourTurnAwaitingDestinations ourInstance = new YourTurnAwaitingDestinations();

    public static YourTurnAwaitingDestinations getInstance() {
        return ourInstance;
    }

    private YourTurnAwaitingDestinations() {
    }
}
