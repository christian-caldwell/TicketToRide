package client;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import models.data.ChatMessage;
import models.data.DestinationCard;
import models.data.Enums;
import models.data.Game;
import models.data.Player;
import models.data.User;
import view.presenter.GameLobbyPresenter;
import view.presenter.GamePresenter;

public class ClientModel extends Observable {
    private User userPlayer;
    private Game gameActive;
    private ArrayList<Game> lobbyGameList = new ArrayList<>();
    private ArrayList<Game> newGameList = new ArrayList<>();
    private GameLobbyPresenter mGameLobbyPresenter;
    private GamePresenter mGamePresenter;

    //new stuff for phase 2
    private Map<Enums.Color, Integer> ticketCardHand;
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

    public Map<Enums.Color, Integer> getTicketCardHand() {
        return ticketCardHand;
    }

    public void setTicketCardHand(Map<Enums.Color, Integer> ticketCardHand) {
        this.ticketCardHand = ticketCardHand;
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
        if (gamePlaying.getPlayer(player.getUsername()) != null) {
            this.gameActive = gamePlaying;
            this.userPlayer.setGameJoined(gamePlaying);
            this.player = gamePlaying.getPlayer(player.getUsername());
            this.ticketCardHand = player.getTickets();
            this.destinationCardHand = player.getDestinationCards();
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

    public void update() {
        if (mGameLobbyPresenter != null) {
            addObserver(this.mGameLobbyPresenter);
//        addObserver(new GamePresenter());
//        addObserver(new LoginPresenter());
//        addObserver(new RegisterPresenter());

            setChanged();
            notifyObservers();
            deleteObservers();
            //clearChangeList();
        }
    }

    public void updateGame() {
        if (mGamePresenter != null) {
            addObserver(this.mGamePresenter);
            setChanged();
            notifyObservers();
            deleteObservers();
        }
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
