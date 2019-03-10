package view.presenter;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import client.ClientModel;
import client.ServerProxy;
import models.data.ChatMessage;
import view.presenterInterface.IChatPresenter;

public class ChatPresenter implements IChatPresenter, Observer {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private final ServerProxy serverProxy = new ServerProxy();
    ClientModel clientModel = ClientModel.create();
    private GameBoardActivity boardActivity;

    public ChatPresenter(GameBoardActivity activity) {
        boardActivity = activity;
        clientModel.setChatPresenter(this);
    }

    @Override
    public ArrayList<ChatMessage> getMessages() {
        return clientModel.getUser().getGame().getChatLog();
    }

    @Override
    public void addMessage(String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAuthorUserName(clientModel.getUser().getUsername());
        chatMessage.setMessageContents(message);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        chatMessage.setTimeStamp(sdf.format(timestamp));
        //clientModel.getUser().getGame().addChat(chatMessage);
        serverProxy.postChatMessage(clientModel.getUser().getGame().getGameName(), chatMessage);
    }

    @Override
    public String getSenderName() {
        return clientModel.getUser().getUsername();
    }

    @Override
    public void update(Observable o, Object arg) {
        new GameBoardActivity.UpdateAsyncTask().execute();

    }
}
