package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;
import com.example.cs340.tickettoride.LoginViewActivity;

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
        zack.setHost(true);
        zack.setGameJoined(game1);
        mLobbyFacadeOut.joinGame(game1, christian);
        mLobbyFacadeOut.joinGame(game1, ben);

        mGameStartFacadeOut.startGame(game1.getGameName());
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
