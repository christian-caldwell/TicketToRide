package presenters;

public class GamePresenter implements IGamePresenter {
    @Override
    public String getStatus() {
        return "Successfully logged in. Game has started.";
    }
}
