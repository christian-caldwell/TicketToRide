package com.example.testingpurposes.presenters;

public class GamePresenter implements IGamePresenter {
    @Override
    public String getStatus() {
        return "Successfully logged in. com.example.testingpurposes.game.Game has started.";
    }
}
