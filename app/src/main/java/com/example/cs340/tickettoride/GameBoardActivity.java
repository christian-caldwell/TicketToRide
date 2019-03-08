package com.example.cs340.tickettoride;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import models.data.ChatMessage;
import models.data.Enums;
import view.presenter.CardDeckPresenter;
import view.presenter.ChatPresenter;
import view.presenter.PlayerInfoPresenter;
import view.presenter.PlayersHandPresenter;
import view.presenterInterface.ICardDeckPresenter;
import view.presenterInterface.IChatPresenter;
import view.presenterInterface.IPlayerInfoPresenter;
import view.presenterInterface.IPlayersHandPresenter;

public class GameBoardActivity extends AppCompatActivity {

    private static ArrayList<ChatMessage> chatMessages;
    private static IChatPresenter chatPresenter;
    private static IPlayersHandPresenter playersHandPresenter;
    private static IPlayerInfoPresenter playerInfoPresenter;
    private static ICardDeckPresenter cardDeckPresenter;
    private static RecyclerViewAdapterChat adapter;
    private static EditText inputChatEditText;
    private static Button sendMessageButton;
    private static Button mGreenTrainCard;
    private static Button mRedTrainCard;
    private static Button mPinkTrainCard;
    private static Button mYellowTrainCard;
    private static Button mWhiteTrainCard;
    private static Button mBlackTrainCard;
    private static Button mWildTrainCard;
    private static Button mBlueTrainCard;


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You committed to this game. No turning back.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_game_board);
        View decorView = getWindow().getDecorView();

        // Hide both the navigation bar and the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        chatPresenter = new ChatPresenter();
        cardDeckPresenter = new CardDeckPresenter();
        playerInfoPresenter = new PlayerInfoPresenter();
        playersHandPresenter = new PlayersHandPresenter();
        initRecyclerView();
        inputChatEditText = findViewById(R.id.input_edit_text);
        sendMessageButton = findViewById(R.id.send_message_button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            // When the sendMessage button is clicked, send the text to the chatPresenter.addMessage function
            @Override
            public void onClick(View v) {
                String newMessage = inputChatEditText.getText().toString();
                chatPresenter.addMessage(newMessage);
            }
        });
        mGreenTrainCard = findViewById(R.id.greenCard);
        mRedTrainCard = findViewById(R.id.redCard);
        mPinkTrainCard = findViewById(R.id.pinkCard);
        mYellowTrainCard = findViewById(R.id.yellowCard);
        mWhiteTrainCard = findViewById(R.id.whiteCard);
        mBlackTrainCard = findViewById(R.id.blackCard);
        mWildTrainCard = findViewById(R.id.wildCard);
        mBlueTrainCard = findViewById(R.id.blueCard);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);
        chatMessages = chatPresenter.getMessages();
        adapter = new RecyclerViewAdapterChat(chatMessages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Void updateChatList(ArrayList<ChatMessage> newChatMessages) {
        chatMessages = newChatMessages;
        return null;
    }


    public static class UpdateChatListAsyncTask extends AsyncTask<ArrayList<ChatMessage>, Void, Void> {

        //Empty constructor
        public UpdateChatListAsyncTask() {
        }

        /**
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         */

        @Override
        protected Void doInBackground(ArrayList<ChatMessage>... arrayLists) {
            return updateChatList(arrayLists[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.setListOfMessages(chatMessages);
            adapter.notifyDataSetChanged();
            chatMessages = chatPresenter.getMessages();
            mGreenTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.GREEN));
            mRedTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.RED));
            mPinkTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.PINK));
            mYellowTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.YELLOW));
            mWhiteTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.WHITE));
            mBlackTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.BLACK));
            mWildTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.WILD));
            mBlueTrainCard.setText(playersHandPresenter.getTrainCardAmount(Enums.Color.BLUE));

        }
    }
}