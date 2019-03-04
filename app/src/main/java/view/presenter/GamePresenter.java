package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import view.presenterInterface.IGamePresenter;

public class GamePresenter implements IGamePresenter, Observer {

    private GameBoardActivity gameBoard;

    public GamePresenter(GameBoardActivity gameBoardActivity) {
        this.gameBoard = gameBoardActivity;
        ClientModel.create().setGamePresenter(this);
    }

    @Override
    public String getStatus() {
        return "Successfully logged in. models.data.Game has started.";
    }

    @Override
    public void update(Observable o, Object arg) {
        //do nothing
    }
}
