package view.activityInterface;

public interface ILobbyViewActivity {
    public void onGameCreated();

    public void onCreateGameFailed(String errorMessage);
}