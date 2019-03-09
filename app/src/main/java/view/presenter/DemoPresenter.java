package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;
import com.example.cs340.tickettoride.LoginViewActivity;

import client.ServerProxy;
import models.data.Game;
import models.data.User;
import view.facade.client.ClientFacade;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.facade.client.out.LoginFacadeOut;
import view.facade.client.out.RegisterFacadeOut;

public class DemoPresenter {

    private LoginViewActivity loginActivity;
    private GameBoardActivity gameActivity;

    private final LoginFacadeOut mLoginFacadeOut = new LoginFacadeOut();
    private final RegisterFacadeOut mRegisterFacadeOut = new RegisterFacadeOut();
    private final LobbyFacadeOut mLobbyFacadeOut = new LobbyFacadeOut();
    private final GameStartFacadeOut mGameStartFacadeOut = new GameStartFacadeOut();
    private ClientFacade mClientFacade = new ClientFacade();
    private ServerProxy mServerProxy = new ServerProxy();

    public DemoPresenter (LoginViewActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void setGameActivity(GameBoardActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public void startDemo() {
        User zack = new User("zshorts", "zackrules");
        mClientFacade.setUser(zack);
        User christian = new User("crc4444", "christianrules");
        User ben = new User("ben1996", "benrules");

        mRegisterFacadeOut.register(zack);
        mRegisterFacadeOut.register(christian);
        mRegisterFacadeOut.register(ben);

        Game game1 = new Game("game 1");
        mLobbyFacadeOut.createGame(game1, zack.getUsername());
        mServerProxy.requestGame(game1.getGameName());
        zack.setHost(true);
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        zack.setGameJoined(game1);
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mLobbyFacadeOut.joinGame(game1, christian);
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mLobbyFacadeOut.joinGame(game1, ben);
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mGameStartFacadeOut.startGame(game1.getGameName());
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
//
//        try{
//            Thread.sleep(3000);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
    }

    public void gameDemo() {

    }
}
