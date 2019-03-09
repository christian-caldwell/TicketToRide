package com.example.cs340.tickettoride;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private static Map playerColorValues;


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
        playerColorValues = new HashMap();
        playerColorValues.put(5,R.drawable.black_background);
        playerColorValues.put(3,R.drawable.blue_background);
        playerColorValues.put(2,R.drawable.green_background);
        playerColorValues.put(1,R.drawable.red_background);
        playerColorValues.put(4,R.drawable.yellow_background);

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

    public void change_color_nashville_littlerock_g1(View view) {//3
        findViewById(R.id.nashville_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.nashville_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b2).setAlpha(1);
        findViewById(R.id.nashville_littlerock_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b3).setAlpha(1);
    }

    public void change_color_neworleans_houston_g1(View view) {//2
        findViewById(R.id.neworleans_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_houston_g1b1).setAlpha(1);
        findViewById(R.id.neworleans_houston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_houston_g1b2).setAlpha(1);
    }

    public void change_color_littlerock_neworleans_g1(View view) {//3
        findViewById(R.id.littlerock_neworleans_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b1).setAlpha(1);
        findViewById(R.id.littlerock_neworleans_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b2).setAlpha(1);
        findViewById(R.id.littlerock_neworleans_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b3).setAlpha(1);
    }

    public void change_color_dallas_houston_g2(View view) {//1
        findViewById(R.id.dallas_houston_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.dallas_houston_g2b1).setAlpha(1);

    }

    public void change_color_littlerock_dallas_g1(View view) {//2
        findViewById(R.id.littlerock_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_dallas_g1b1).setAlpha(1);
        findViewById(R.id.littlerock_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_dallas_g1b2).setAlpha(1);
    }

    public void change_color_oklahomacity_dallas_g2(View view) {//2
        findViewById(R.id.oklahomacity_dallas_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g2b1).setAlpha(1);
        findViewById(R.id.oklahomacity_dallas_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g2b2).setAlpha(1);
    }

    public void change_color_oklahomacity_littlerock_g1(View view) {//2
        findViewById(R.id.oklahomacity_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_littlerock_g1b2).setAlpha(1);
    }

    public void change_color_saintlouis_nashville_g1(View view) {//2
        findViewById(R.id.saintlouis_nashville_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_nashville_g1b1).setAlpha(1);
        findViewById(R.id.saintlouis_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_nashville_g1b2).setAlpha(1);
    }

    public void change_color_saintlouis_littlerock_g1(View view) {//2
        findViewById(R.id.saintlouis_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.saintlouis_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_littlerock_g1b2).setAlpha(1);
    }

    public void change_color_kansascity_saintlouis_g2(View view) {//2
        findViewById(R.id.kansascity_saintlouis_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g2b1).setAlpha(1);
        findViewById(R.id.kansascity_saintlouis_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g2b2).setAlpha(1);
    }

    public void change_color_kansascity_saintlouis_g1(View view) {//2
        findViewById(R.id.kansascity_saintlouis_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g1b1).setAlpha(1);
        findViewById(R.id.kansascity_saintlouis_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g1b2).setAlpha(1);
    }

    public void change_color_kansascity_oklahomacity_g2(View view) {//2
        findViewById(R.id.kansascity_oklahomacity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_oklahomacity_g2b1).setAlpha(1);
        findViewById(R.id.kansascity_oklahomacity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_oklahomacity_g2b2).setAlpha(1);
    }

    public void change_color_omaha_kansascity_g2(View view) {//1
        findViewById(R.id.omaha_kansascity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_kansascity_g2b1).setAlpha(1);
    }

    public void change_color_neworleans_miami_g1(View view) {//6
        findViewById(R.id.neworleans_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b1).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b2).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b3).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b4).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b5).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b6).setAlpha(1);
    }

    public void change_color_charleston_miami_g1(View view) {//4
        findViewById(R.id.charleston_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b1).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b2).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b3).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b4).setAlpha(1);
    }

    public void change_color_atlanta_charleston_g1(View view) {//2
        findViewById(R.id.atlanta_charleston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_charleston_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_charleston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_charleston_g1b2).setAlpha(1);
    }

    public void change_color_atlanta_miami_g1(View view) {//5
        findViewById(R.id.atlanta_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b2).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b3).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b4).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b5).setAlpha(1);

    }

    public void change_color_atlanta_neworleans_g2(View view) {//4
        findViewById(R.id.atlanta_neworleans_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b1).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b2).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b3).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b4).setAlpha(1);

    }

    public void change_color_atlanta_neworleans_g1(View view) {//4
        findViewById(R.id.atlanta_neworleans_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b2).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b3).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b4).setAlpha(1);
    }

    public void change_color_nashville_atlanta_g1(View view) {//1
        findViewById(R.id.nashville_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b1).setAlpha(1);
    }

    public void change_color_raleigh_nashville_g1(View view) {//3
        findViewById(R.id.raleigh_nashville_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b1).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b2).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b3).setAlpha(1);
    }

    public void change_color_pittsburgh_raleigh_g1(View view) {//2
        findViewById(R.id.pittsburgh_raleigh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_raleigh_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_raleigh_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_raleigh_g1b2).setAlpha(1);
    }

    public void change_color_pittsburgh_nashville_g1(View view) {//4
        findViewById(R.id.pittsburgh_raleigh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b1).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b2).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b3).setAlpha(1);
    }

    public void change_color_pittsburgh_saintlouis_g1(View view) {//5
    }

    public void change_color_pittsburgh_chicago_g2(View view) {//3
    }

    public void change_color_pittsburgh_chicago_g1(View view) {//3
    }

    public void change_color_chicago_saintlouis_g2(View view) {//2
    }

    public void change_color_chicago_saintlouis_g1(View view) {//2
    }

    public void change_color_chicago_omaha_g1(View view) {//4
    }

    public void change_color_duluth_chicago_g1(View view) {//3
    }

    public void change_color_duluth_omaha_g2(View view) {//2
    }

    public void change_color_saulstmarie_duluth_g1(View view) {//3
    }

    public void change_color_winnipeg_saulstmarie_g1(View view) {//6
    }

    public void change_color_toronto_duluth_g1(View view) {//6
    }

    public void change_color_toronto_pittsburgh_g1(View view) {//2
    }

    public void change_color_toronto_chicago_g1(View view) {//4
    }

    public void change_color_saulstmarie_toronto_g1(View view) {//2
    }

    public void change_color_montreal_saulstmarie_g1(View view) {//5
    }

    public void change_color_montreal_toronto_g1(View view) {//3
    }

    public void change_color_raleigh_atlanta_g2(View view) {//2
    }

    public void change_color_raleigh_atlanta_g1(View view) {//2
    }

    public void change_color_raleigh_charleston_g1(View view) {//2
    }

    public void change_color_washington_raleigh_g2(View view) {//2
    }

    public void change_color_washington_raleigh_g1(View view) {//2
    }

    public void change_color_pittsburgh_washington_g1(View view) {//2
    }

    public void change_color_newyork_washington_g2(View view) {//2
    }

    public void change_color_newyork_washington_g1(View view) {//2
    }

    public void change_color_newyork_pittsburgh_g2(View view) {//2
    }

    public void change_color_newyork_pittsburgh_g1(View view) {//2
    }

    public void change_color_boston_newyork_g2(View view) {//2
    }

    public void change_color_boston_newyork_g1(View view) {//2
    }

    public void change_color_montreal_boston_g2(View view) {//2
    }

    public void change_color_montreal_boston_g1(View view) {//2
    }

    public void change_color_montreal_newyork_g1(View view) {//3
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