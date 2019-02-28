package view.presenter;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import client.ClientModel;
import client.ServerProxy;
import models.data.ChatMessage;
import view.presenterInterface.IChatPresenter;

public class ChatPresenter implements IChatPresenter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private final ServerProxy serverProxy = new ServerProxy();
    ClientModel clientModel = ClientModel.create();

    @Override
    public ArrayList<ChatMessage> getMessages() {
        //TODO: chat log needs to be updated by the poller.
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
        clientModel.getUser().getGame().addChat(chatMessage);
        //TODO: this method needs to be added to server proxy.
        //serverProxy.addMessage(chatMessage);


    }
}
