package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;
import com.example.cs340.tickettoride.LoginViewActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import client.ServerProxy;
import models.TTR_Constants;
import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Game;
import models.data.Player;
import models.data.Route;
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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


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

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        Player player1 = game1.findPlayer(zack.getUsername());
        Player player2 = game1.findPlayer(christian.getUsername());
        Player player3 = game1.findPlayer(ben.getUsername());


        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //show return dest card, wait

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        DestinationCard[] cards1 = new DestinationCard[1];
        cards1[0] = player1.getDestinationCardHand().get(0);
        mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards1);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //show return dest cards of all other players, fast

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        DestinationCard[] cards2 = new DestinationCard[1];
        DestinationCard[] cards3 = new DestinationCard[1];
        cards2[0] = player2.getDestinationCardHand().get(0);
        cards3[0] = player3.getDestinationCardHand().get(0);
        mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards2);
        mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards3);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //draw ticket cards from deck, wait

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //draw ticket cards for other players, fast

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();

        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);

        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);


        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //draw ticket cards from face up, wait

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),1, false);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //draw ticket cards from face up for other players, fast

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),1, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),2, false);
        mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),3, false);
        mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),4, false);
        mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),5, false);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //set player red ticket color to 10
        //purchase red route
        boolean enoughTickets1 = false;
        while (!enoughTickets1) {
            mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(),0, false);
            game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
            player1 = game1.findPlayer(zack.getUsername());
            Map<Integer,Integer> tickets = player1.getTickets();
            if (tickets.get(TTR_Constants.getInstance().RED) > 6) {
                enoughTickets1 = true;
            }
        }
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player1 = game1.findPlayer(zack.getUsername());
        //FIXME: needs to use getRoute() once that function has been retrieved from github
        mServerProxy.purchaseRoute(player1.getUsername(),game1.getGameName(),/*TTR_Constants.getRoute("New Orleans", "Miami")*/ new Route(),0);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //set other player green ticket to 10
        //other player purchase green route

        boolean enoughTickets2 = false;
        while (!enoughTickets2) {
            mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(),0, false);
            game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
            player2 = game1.findPlayer(christian.getUsername());
            Map<Integer,Integer> tickets = player2.getTickets();
            if (tickets.get(TTR_Constants.getInstance().PURPLE) > 6) {
                enoughTickets2 = true;
            }
        }
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player2 = game1.findPlayer(christian.getUsername());
        //FIXME: needs to use getRoute() once that function has been retrieved from github
        mServerProxy.purchaseRoute(player2.getUsername(),game1.getGameName(),/*TTR_Constants.getRoute("Duluth", "Toronto")*/ new Route(),0);

        boolean enoughTickets3 = false;
        while (!enoughTickets3) {
            mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(),0, false);
            game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
            player3 = game1.findPlayer(ben.getUsername());
            Map<Integer,Integer> tickets = player3.getTickets();
            if (tickets.get(TTR_Constants.getInstance().GREEN) > 6) {
                enoughTickets3 = true;
            }
        }
        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player3 = game1.findPlayer(ben.getUsername());
        //FIXME: needs to use getRoute() once that function has been retrieved from github
        mServerProxy.purchaseRoute(player3.getUsername(),game1.getGameName(),/*TTR_Constants.getRoute("El Paso", "Dallas")*/ new Route(),0);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //request dest cards

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestDestinationCards(player1.getUsername(),game1.getGameName());

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //return 1 dest card

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player1 = game1.findPlayer(zack.getUsername());
        cards1[0] = player1.getDestinationCardHand().get(0);
        mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards1);
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //request dest cards

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestDestinationCards(player1.getUsername(),game1.getGameName());

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //return 2 dest cards

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player1 = game1.findPlayer(zack.getUsername());
        cards1 = new DestinationCard[2];
        cards1[0] = player1.getDestinationCardHand().get(0);
        cards1[1] = player1.getDestinationCardHand().get(1);
        mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards1);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //all other player request dest cards

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        mServerProxy.requestDestinationCards(player2.getUsername(),game1.getGameName());
        mServerProxy.requestDestinationCards(player3.getUsername(),game1.getGameName());

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //all other player return 2 dest cards

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        player2 = game1.findPlayer(christian.getUsername());
        player3 = game1.findPlayer(ben.getUsername());
        cards2 = new DestinationCard[2];
        cards2[0] = player2.getDestinationCardHand().get(0);
        cards2[1] = player2.getDestinationCardHand().get(1);
        mServerProxy.returnDestinationCards(christian.getUsername(), game1.getGameName(), cards2);
        cards3 = new DestinationCard[2];
        cards3[0] = player3.getDestinationCardHand().get(0);
        cards3[1] = player3.getDestinationCardHand().get(1);
        mServerProxy.returnDestinationCards(ben.getUsername(), game1.getGameName(), cards1);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //player 1 post chat message

        game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        ChatMessage message = createMessage("Nice Demo!!!", player1.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //post full convo

        message = createMessage("Sup", player2.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("Hey", player3.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("Hello", player1.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("I'm so cool", player2.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("Absolutely", player3.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("The coolest", player1.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("Literaly the coolest ever", player3.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);
        message = createMessage("I am so glad this is a real conversation", player2.getUsername());
        mServerProxy.postChatMessage(game1.getGameName(), message);


    }

    private ChatMessage createMessage (String contents, String userName) {
        ChatMessage message = new ChatMessage();
        message.setAuthorUserName(userName);
        message.setMessageContents(contents);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        message.setTimeStamp(sdf.format(timestamp));
        return message;
    }
}
