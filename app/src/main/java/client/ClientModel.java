package client;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Game;
import models.data.Player;
import models.data.User;
import view.presenter.CardDeckPresenter;
import view.presenter.ChatPresenter;
import view.presenter.DemoPresenter;
import view.presenter.GameLobbyPresenter;
import view.presenter.GamePresenter;
import view.presenter.PlayerInfoPresenter;
import view.presenter.PlayersHandPresenter;
import view.presenter.RoutePresenter;

public class ClientModel extends Observable {
    private User userPlayer;
    private Game gameActive;
    private ArrayList<Game> lobbyGameList = new ArrayList<>();
    private ArrayList<Game> newGameList = new ArrayList<>();
    private GameLobbyPresenter mGameLobbyPresenter;
    private GamePresenter mGamePresenter;
    private ChatPresenter mChatPresenter;
    private CardDeckPresenter mCardDeckPresenter;
    private PlayerInfoPresenter mPlayerInfoPresenter;
    private PlayersHandPresenter mPlayersHandPresenter;
    private RoutePresenter mRoutePresenter;

    //    TEMPORARY DEMO THINGS
    DemoPresenter demoPresenter;

    public DemoPresenter getDemoPresenter() {
        return demoPresenter;
    }

    public void setDemoPresenter(DemoPresenter demoPresenter) {
        this.demoPresenter = demoPresenter;
    }
    //new stuff for phase 2
    private Map<Integer, Integer> ticketCardHand;
    private ArrayList<DestinationCard> destinationCardHand;
    private Player player;
    private ArrayList<Object> changedObjects;
    private ArrayList<ChatMessage> chatMessages;

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void addChatMessages(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }

    public Map<Integer, Integer> getTicketCardHand() {
        return ticketCardHand;
    }

    public void setTicketCardHand(Map<Integer, Integer> ticketCardHand) {
        this.ticketCardHand = ticketCardHand;
    }
    public void incrementTicketCardHand(Integer color) {
        ticketCardHand.put(color, ticketCardHand.get(color) + 1);
    }

    public ArrayList<DestinationCard> getDestinationCardHand() {
        return destinationCardHand;
    }

    public void setDestinationCardHand(ArrayList<DestinationCard> destinationCardHand) {
        this.destinationCardHand = destinationCardHand;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Object> getChangedObjects() {
        return changedObjects;
    }

    public void setChangedObjects(ArrayList<Object> changedObjects) {
        this.changedObjects = changedObjects;
    }

/////////////

    private static ClientModel singleton;

    private ClientModel() {}

    public static ClientModel create() {
        if (singleton == null) {
            singleton = new ClientModel();
        }
        return singleton;
    }

    public User getUser() {
        return this.userPlayer;
    }

    public void setUserPlayer(User userPlayer) {
        this.userPlayer = userPlayer;
    }

    public Game getActiveGame() {
        return this.gameActive;
    }

    public void setActiveGame(Game gamePlaying) {
        if (gamePlaying.getPlayerUsernames().contains(userPlayer.getUsername())) { // change this back to checking player?
            this.gameActive = gamePlaying;
            this.userPlayer.setGameJoined(gamePlaying);
            this.player = gamePlaying.getPlayer(userPlayer.getUsername());
            this.ticketCardHand = player.getTickets();
            this.destinationCardHand = player.getDestinationCardHand();
        }
    }

    public ArrayList<Game> getLobbyGamesList() {
        return this.lobbyGameList;
    }

    public void setLobbyGamesList(ArrayList<Game> newGameList) { this.lobbyGameList = new ArrayList<>(newGameList);}

    public void addLobbyGamesList(Game game) {
        this.lobbyGameList.add(game);
    }

    public void setChangedGameList(ArrayList<Game> newGameList) { this.newGameList = new ArrayList<>(newGameList);}

    public ArrayList<Game> getChangedGameList() {
        return this.newGameList;
    }

    public void addChange(Game changedGame) {
        this.newGameList.add(changedGame);
    }

    public void clearChangeList() {
        this.newGameList.clear();
    }

    //not implemented
    private Player initGame(String userName) {
        return null;
    }
    private void initHands() {

    }



    public GameLobbyPresenter getGameLobbyPresenter() {
        return mGameLobbyPresenter;
    }

    public void setGameLobbyPresenter(GameLobbyPresenter gameLobbyPresenter) {
        mGameLobbyPresenter = gameLobbyPresenter;
    }

    public GamePresenter getGamePresenter() { return mGamePresenter; }

    public void setGamePresenter(GamePresenter gamePresenter) {
        mGamePresenter = gamePresenter;
    }

    public void setmCardDeckPresenter(CardDeckPresenter mCardDeckPresenter) {
        this.mCardDeckPresenter = mCardDeckPresenter;
    }

    public void setmPlayerInfoPresenter(PlayerInfoPresenter mPlayerInfoPresenter) {
        this.mPlayerInfoPresenter = mPlayerInfoPresenter;
    }

    public void setmPlayersHandPresenter(PlayersHandPresenter mPlayersHandPresenter) {
        this.mPlayersHandPresenter = mPlayersHandPresenter;
    }

    public ChatPresenter getmChatPresenter() { return mChatPresenter; }

    public void setChatPresenter(ChatPresenter chatPresenter) { mChatPresenter = chatPresenter; }

    public void setRoutePresenter(RoutePresenter routePresenter) { mRoutePresenter = routePresenter; }

    public RoutePresenter getmRoutePresenter() { return mRoutePresenter; }

    public void update() {
        if (mGameLobbyPresenter != null) {
            addObserver(this.mGameLobbyPresenter);

            setChanged();
            notifyObservers();
            deleteObservers();
            //clearChangeList();
        }
    }

    public void updateGame() {
        if (mGamePresenter != null) {
            addObserver(this.mGamePresenter);
        }
        if (mChatPresenter != null) {
            addObserver(this.mChatPresenter);
        }
        if (mCardDeckPresenter != null) {
            addObserver(this.mCardDeckPresenter);
        }
        if (mPlayerInfoPresenter != null) {
            addObserver(this.mPlayerInfoPresenter);
        }
        if (mPlayersHandPresenter != null) {
            addObserver(this.mPlayersHandPresenter);
        }
        if (mRoutePresenter != null) {
            addObserver(this.mRoutePresenter);
        }
        setChanged();
        notifyObservers();
        deleteObservers();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    ///Observable Functions


}
