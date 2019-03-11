package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;
import com.example.cs340.tickettoride.LoginViewActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.Poller;
import client.ServerProxy;
import models.TTR_Constants;
import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Game;
import models.data.Player;
import models.data.User;
import server.facade.RunGameFacade;
import view.facade.client.ClientFacade;
import view.facade.client.out.GameStartFacadeOut;
import view.facade.client.out.LobbyFacadeOut;
import view.facade.client.out.RegisterFacadeOut;

public class DemoPresenter {

    private LoginViewActivity loginActivity;
    private GameBoardActivity gameActivity;
    private User zack;
    private User christian;
    private User ben;
    private Game game1;
    private int demoNumber = 1;

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

    public boolean onCreate() {
        try {
            Poller.startGamePoller();
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public String gameDemo() {
        //Show init game board stuff, wait
        //show received dest cards, wait
        this.game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
        Player player1 = this.game1.findPlayer(zack.getUsername());
        Player player2 = this.game1.findPlayer(christian.getUsername());
        Player player3 = this.game1.findPlayer(ben.getUsername());
        String output =  "Demo is finished";
        System.out.println(demoNumber);
        switch (this.demoNumber) {
            case (1):
                DestinationCard[] cards1 = new DestinationCard[1];
                cards1[0] = player1.getNewDestinationCards().get(0);
                mServerProxy.returnDestinationCards(zack.getUsername(), this.game1.getGameName(), cards1);
                output = "Test Iteration " + (demoNumber + 1) + ":\n "  + player1.getUsername()
                        + "Will Reture Destination Card to Game Deck";
                break;
            case (2):
                DestinationCard[] cards2 = new DestinationCard[1];
                DestinationCard[] cards3 = new DestinationCard[1];
                cards2[0] = player2.getNewDestinationCards().get(0);
                cards3[0] = player3.getNewDestinationCards().get(0);
                mServerProxy.returnDestinationCards(christian.getUsername(), game1.getGameName(), cards2);
                mServerProxy.returnDestinationCards(ben.getUsername(), game1.getGameName(), cards3);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player2.getUsername() + " and " + player3.getUsername() + " Returning Destination Card to Game Deck";
                break;
            case (3):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Requesting Ticket Card";
                break;
            case (4):
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);

                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n Players take turns drawing tickets";
                break;
            case (5):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);

                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n Players take turns drawing tickets";
                break;
            case (6):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);

                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n Players take turns drawing tickets";
                break;
            case (7):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);

                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n Players take turns drawing tickets";
                break;
            case (8):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 1, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " draws ticket from first face-up position";
                break;
            case (9):
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 1, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 2, false);
                mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 3, false);
                mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 4, false);
                mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 5, false);
                output = "Test Iteration " + (demoNumber+1) + ":\n Each face-up card position drawn from by players";
                break;
            case (10):
//                boolean enoughTickets1 = false;
//                while (!enoughTickets1) {
//                    mServerProxy.requestTicketCard(player1.getUsername(), game1.getGameName(), 0, false);
//                    game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
//                    player1 = game1.findPlayer(zack.getUsername());
//                    Map<Integer, Integer> tickets = player1.getTickets();
//                    if (tickets.get(TTR_Constants.getInstance().RED) > 6) {
//                        enoughTickets1 = true;
//                    }
//                }
                mServerProxy.purchaseRoute(player1.getUsername(), game1.getGameName(), TTR_Constants.getInstance().getRoute("New Orleans", "Miami"), 0);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Purchases a Route, Corresponding Card Color Decremented";
                break;
            case (11):
//                boolean enoughTickets2 = false;
//                while (!enoughTickets2) {
//                    mServerProxy.requestTicketCard(player2.getUsername(), game1.getGameName(), 0, false);
//                    game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
//                    player2 = game1.findPlayer(christian.getUsername());
//                    Map<Integer, Integer> tickets = player2.getTickets();
//                    if (tickets.get(TTR_Constants.getInstance().PURPLE) > 6) {
//                        enoughTickets2 = true;
//                    }
//                }
                mServerProxy.purchaseRoute(player2.getUsername(), game1.getGameName(), TTR_Constants.getInstance().getRoute("Duluth", "Toronto"), 0);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player2.getUsername() + " Purchases a Route, Corresponding Card Color Decremented";
                break;
            case (12):
//                boolean enoughTickets3 = false;
//                while (!enoughTickets3) {
//                    mServerProxy.requestTicketCard(player3.getUsername(), game1.getGameName(), 0, false);
//                    game1 = mServerProxy.requestGame(game1.getGameName()).getRunningGame();
//                    player3 = game1.findPlayer(ben.getUsername());
//                    Map<Integer, Integer> tickets = player3.getTickets();
//                    if (tickets.get(TTR_Constants.getInstance().GREEN) > 6) {
//                        enoughTickets3 = true;
//                    }
//                }
                mServerProxy.purchaseRoute(player3.getUsername(), game1.getGameName(), TTR_Constants.getInstance().getRoute("El Paso", "Houston"), 0);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player3.getUsername() + " Purchases a Route, Corresponding Card Color Decremented" ;
                break;
            case (13):
                mServerProxy.requestDestinationCards(player1.getUsername(), game1.getGameName());
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Requests Dest Cards";
                break;
            case (14):
                mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), null);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Chooses to Keep All Dest Cards";
                break;
            case (15):
                mServerProxy.requestDestinationCards(player1.getUsername(), game1.getGameName());
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Requests Dest Card";
                break;
            case (16):
                cards1 = new DestinationCard[2];
                cards1[0] = player1.getDestinationCardHand().get(0);
                cards1[1] = player1.getDestinationCardHand().get(1);
                mServerProxy.returnDestinationCards(zack.getUsername(), game1.getGameName(), cards1);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Returns 2 Dest Cards";
                break;
            case (17):
                mServerProxy.requestDestinationCards(player2.getUsername(), game1.getGameName());
                mServerProxy.requestDestinationCards(player3.getUsername(), game1.getGameName());
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player2.getUsername() + " and " + player3.getUsername() + " Request Dest Cards";
                break;
            case (18):
                cards2 = new DestinationCard[2];
                cards2[0] = player2.getNewDestinationCards().get(0);
                cards2[1] = player2.getNewDestinationCards().get(1);
                mServerProxy.returnDestinationCards(christian.getUsername(), game1.getGameName(), cards2);
                cards3 = new DestinationCard[2];
                cards3[0] = player3.getNewDestinationCards().get(0);
                cards3[1] = player3.getNewDestinationCards().get(1);
                mServerProxy.returnDestinationCards(ben.getUsername(), game1.getGameName(), cards3);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player2.getUsername() + " and " + player3.getUsername() + " Return 2 Dest Cards";
                break;
            case (19):
                ChatMessage message = createMessage("Nice Demo!!!", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                output = "Test Iteration " + (demoNumber+1) + ":\n " + player1.getUsername() + " Posts Message";
                break;
            case (20):
                message = createMessage("Sup", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Hey", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Hello", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                output = "Test Iteration " + (demoNumber+1) + ":\n All Players Post Messages";
                break;
            case (21):
                message = createMessage("I don't think this conversation is long enough", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("We need to get all the way to the bottom of the screen", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("okay, lets keep talking", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Can we talk in any order", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Yep", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Did you know that if you post your password, it automatically censors it out?", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Really?", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Totally", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Watch, **********", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("See?", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Wow", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Give it a try", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Ok, hunter12", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("hahahahahahahaha", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("sucker", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Nice!", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("did it work?", player2.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("...", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("sure", player1.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                output = "Test Iteration " + (demoNumber+1) + ":\n Series of random posts";
                break;
            case (22):
                message = createMessage("Really well done", player3.getUsername());
                mServerProxy.postChatMessage(game1.getGameName(), message);
                message = createMessage("Yeah, you did a great job waiting to comment", player3.getUsername());
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
                output = "Test Iteration " + (demoNumber+1) + ":\n Series of random posts";
                break;
        }
        this.demoNumber++;
        return output;
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
