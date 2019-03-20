package client.PlayerStates;

public class YourTurnSecondDraw extends PlayerState {
    private static final YourTurnSecondDraw ourInstance = new YourTurnSecondDraw();

    public static YourTurnSecondDraw getInstance() {
        return ourInstance;
    }

    private YourTurnSecondDraw() {
    }
}
