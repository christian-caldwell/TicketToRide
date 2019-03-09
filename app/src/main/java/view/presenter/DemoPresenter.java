package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;
import com.example.cs340.tickettoride.LoginViewActivity;

import client.ServerProxy;
import models.data.Game;
import models.data.User;
import server.facade.RunGameFacade;
import view.facade.client.ClientFacade;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.facade.client.out.LoginFacadeOut;
import view.facade.client.out.RegisterFacadeOut;

public class DemoPresenter {

    private LoginViewActivity loginActivity;
    private GameBoardActivity gameActivity;
    private User zack;
    private User christian;
    private User ben;
    private Game game1;


    private final RegisterFacadeOut mRegisterFacadeOut = new RegisterFacadeOut();
    private final LobbyFacadeOut mLobbyFacadeOut = new LobbyFacadeOut();
    private final GameStartFacadeOut mGameStartFacadeOut = new GameStartFacadeOut();
    private final RunGameFacade mRunGameFacade = new RunGameFacade();
    private final ClientFacade mClientFacade = new ClientFacade();
    private final ServerProxy mServerProxy = new ServerProxy();

    public DemoPresenter (LoginViewActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void setGameActivity(GameBoardActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public void startDemo() {

        zack = new User("zshorts", "zackrules");
        mClientFacade.setUser(zack);
        christian = new User("crc4444", "christianrules");
        ben = new User("ben1996", "benrules");

        mRegisterFacadeOut.register(zack);
        mRegisterFacadeOut.register(christian);
        mRegisterFacadeOut.register(ben);

        game1 = new Game("game 1");
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
        //Show init game board stuff, wait

        //show received dest cards, wait

        //show return dest card, wait

        //show received dest cards of all other players, fast

        //draw ticket cards from deck, wait

        //draw ticket cards for other players, fast

        //draw ticket cards from face up, wait

        //draw ticket cards from face up for other players, fast

        //set player red ticket color to 10

        //purchase red route

        //set other player green ticket to 10

        //other player purchase green route

        //request dest cards

        //return 1 dest card

        //request dest cards

        //return 2 dest cards

    }
}
