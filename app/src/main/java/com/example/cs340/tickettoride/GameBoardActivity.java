package com.example.cs340.tickettoride;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.Group;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import client.ClientModel;
import models.data.ChatMessage;
import view.presenter.CardDeckPresenter;
import view.presenter.ChatPresenter;
import view.presenter.PlayerInfoPresenter;
import view.presenter.PlayersHandPresenter;
import view.presenterInterface.ICardDeckPresenter;
import view.presenter.DemoPresenter;
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
    private static Button gameDemoButton;
    private DemoPresenter mDemoPresenter;
    private static Button sendMessageButton, playerInfoButton;
    private static Button mGreenTrainCard, mRedTrainCard, mPinkTrainCard, mYellowTrainCard,
            mWhiteTrainCard, mBlackTrainCard, mWildTrainCard, mBlueTrainCard, mOrangeTrainCard;
    private static Button destinationCardDeck, trainCardDeck, cardOne, cardTwo, cardThree, cardFour, cardFive;
    private static ImageView gameBoard;
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
        chatPresenter = new ChatPresenter(this);
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
        gameDemoButton = findViewById(R.id.gameDemoButon);

        ClientModel.create().setGameBoardActivity(this);

        gameDemoButton.setOnClickListener(new View.OnClickListener() {
            // When the sendMessage button is clicked, send the text to the presenter.addMessage function
            @Override
            public void onClick(View v) {
                Toast.makeText(GameBoardActivity.this, "Starting Game Demo", Toast.LENGTH_SHORT).show();

            }
        });

        //FIXME: FINISH THE LISTENER TO RETURN THE PLAYER'S ROUTE CARDS THAT HE HAS
        playerInfoButton = findViewById(R.id.get_player_info_button);
        playerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerInfoPresenter.getDestinationCardStrings();
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
        mOrangeTrainCard = findViewById(R.id.orangeCard);

        destinationCardDeck = findViewById(R.id.destination_card_deck);
        destinationCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawDestinationCard();
            }
        });

        trainCardDeck = findViewById(R.id.train_card_deck);
        trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(0);
            }
        });

        cardOne = findViewById(R.id.card_index_zero);
        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(1);
            }
        });

        cardTwo = findViewById(R.id.card_index_one);
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(2);
            }
        });

        cardThree = findViewById(R.id.card_index_two);
        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(3);
            }
        });

        cardFour = findViewById(R.id.card_index_three);
        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(4);
            }
        });

        cardFive = findViewById(R.id.card_index_four);
        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(5);
            }
        });

        gameBoard = findViewById(R.id.game_board_pic);

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

    public void change_color_portland_sanfransisco_g2(View v) {
        findViewById(R.id.portland_sanfransisco_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b1).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b2).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b3).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b4).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b5).setAlpha(1);
    }

    public void change_color_vancouver_seattle_g1(View view) {
        findViewById(R.id.vancouver_seattle_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_seattle_g1b1).setAlpha(1);
    }

    public void change_color_vancouver_seattle_g2(View view) {
        findViewById(R.id.vancouver_seattle_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_seattle_g2b1).setAlpha(1);
    }

    public void change_color_seattle_portland_g1(View view) {
        findViewById(R.id.seattle_portland_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_portland_g1b1).setAlpha(1);
    }

    public void change_color_seattle_portland_g2(View view) {
        findViewById(R.id.seattle_portland_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_portland_g2b1).setAlpha(1);
    }

    public void change_color_portland_sanfransisco_g1(View view) {
        findViewById(R.id.portland_sanfransisco_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b1).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b2).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b3).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b4).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b5).setAlpha(1);
    }

    public void change_color_sanfransisco_losangeles_g1(View view) {
        findViewById(R.id.sanfransisco_losangeles_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b1).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b2).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b3).setAlpha(1);
    }

    public void change_color_sanfransisco_losangeles_g2(View view) {
        findViewById(R.id.sanfransisco_losangeles_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b1).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b2).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b3).setAlpha(1);
    }

    public void change_color_losangeles_elpaso_g1(View view) {
        findViewById(R.id.losangeles_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b2).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b3).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b4).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b5).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b6).setAlpha(1);
    }

    public void change_color_vancouver_calgary_g1(View view) {
        findViewById(R.id.vancouver_calgary_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b1).setAlpha(1);
        findViewById(R.id.vancouver_calgary_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b2).setAlpha(1);
        findViewById(R.id.vancouver_calgary_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b3).setAlpha(1);
    }

    public void change_color_seattle_calgary_g1(View view) {
        findViewById(R.id.seattle_calgary_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b1).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b2).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b3).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b4).setAlpha(1);
    }

    public void change_color_seattle_helena_g1(View view) {
        findViewById(R.id.seattle_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b1).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b2).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b3).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b4).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b5).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b6).setAlpha(1);
    }

    public void change_color_calgary_winnipeg_g1(View view) {
        findViewById(R.id.calgary_winnipeg_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b1).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b2).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b3).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b4).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b5).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b6).setAlpha(1);
    }

    public void change_color_calgary_helena_g1(View view) {
        findViewById(R.id.calgary_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b1).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b2).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b3).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b4).setAlpha(1);
    }

    public void change_color_helena_winnipeg_g1(View view) {
        findViewById(R.id.helena_winnipeg_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b1).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b2).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b3).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b4).setAlpha(1);
    }

    public void change_color_portland_saltlakecity_g1(View view) {
        findViewById(R.id.portland_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b3).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b4).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b5).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b6).setAlpha(1);
    }

    public void change_color_sanfransisco_saltlakecity_g1(View view) {
        findViewById(R.id.sanfransisco_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b3).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b4).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b5).setAlpha(1);
    }

    public void change_color_sanfransisco_saltlakecity_g2(View view) {
        findViewById(R.id.sanfransisco_saltlakecity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b1).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b2).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b3).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b4).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b5).setAlpha(1);
    }

    public void change_color_losangeles_lasvegas_g1(View view) {
        findViewById(R.id.losangeles_lasvegas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_lasvegas_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_lasvegas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_lasvegas_g1b2).setAlpha(1);
    }

    public void change_color_lasvegas_saltlakecity_g1(View view) {
        findViewById(R.id.lasvegas_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.lasvegas_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.lasvegas_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b3).setAlpha(1);
    }

    public void change_color_losangeles_phoenix_g1(View view) {
        findViewById(R.id.losangeles_phoenix_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_phoenix_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b2).setAlpha(1);
        findViewById(R.id.losangeles_phoenix_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_elpaso_g1(View view) {
        findViewById(R.id.phoenix_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_elpaso_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_santafe_g1(View view) {
        findViewById(R.id.phoenix_santafe_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_santafe_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_santafe_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_denver_g1(View view) {
        findViewById(R.id.phoenix_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b3).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b4).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b5).setAlpha(1);
    }

    public void change_color_saltlakecity_denver_g1(View view) {
        findViewById(R.id.saltlakecity_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b1).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b2).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b3).setAlpha(1);
    }

    public void change_color_saltlakecity_denver_g2(View view) {
        findViewById(R.id.saltlakecity_denver_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b1).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b2).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b3).setAlpha(1);
    }

    public void change_color_saltlakecity_helena_g1(View view) {
        findViewById(R.id.saltlakecity_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b1).setAlpha(1);
        findViewById(R.id.saltlakecity_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b2).setAlpha(1);
        findViewById(R.id.saltlakecity_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b3).setAlpha(1);
    }

    public void change_color_helena_denver_g1(View view) {
        findViewById(R.id.helena_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b1).setAlpha(1);
        findViewById(R.id.helena_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b2).setAlpha(1);
        findViewById(R.id.helena_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b3).setAlpha(1);
        findViewById(R.id.helena_denver_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b4).setAlpha(1);
    }

    public void change_color_helena_duluth_g1(View view) {
        findViewById(R.id.helena_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b1).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b2).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b3).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b4).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b5).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b6).setAlpha(1);
    }

    public void change_color_helena_omaha_g1(View view) {
        findViewById(R.id.helena_omaha_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b1).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b2).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b3).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b4).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b5).setAlpha(1);
    }

    public void change_color_omaha_duluth_g1(View view) {
        findViewById(R.id.omaha_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_duluth_g1b1).setAlpha(1);
        findViewById(R.id.omaha_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_duluth_g1b2).setAlpha(1);
    }

    public void change_color_denver_omaha_g1(View view) {
        findViewById(R.id.denver_omaha_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b1).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b2).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b3).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b4).setAlpha(1);
    }

    public void change_color_denver_kansascity_g1(View view) {
        findViewById(R.id.denver_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b1).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b2).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b3).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b4).setAlpha(1);
    }

    public void change_color_denver_kansascity_g2(View view) {
        findViewById(R.id.denver_kansascity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b1).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b2).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b3).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b4).setAlpha(1);
    }

    public void change_color_denver_oklahomacity_g1(View view) {
        findViewById(R.id.denver_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b3).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b4).setAlpha(1);
    }

    public void change_color_denver_santafe_g1(View view) {
        findViewById(R.id.denver_santafe_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_santafe_g1b1).setAlpha(1);
        findViewById(R.id.denver_santafe_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_santafe_g1b2).setAlpha(1);
    }

    public void change_color_santafe_elpaso_g1(View view) {
        findViewById(R.id.santafe_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.santafe_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_elpaso_g1b2).setAlpha(1);
    }

    public void change_color_santafe_oklahomacity_g1(View view) {
        findViewById(R.id.santafe_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.santafe_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.santafe_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b3).setAlpha(1);
    }

    public void change_color_elpaso_oklahomacity_g1(View view) {
        findViewById(R.id.elpaso_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b4).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b5).setAlpha(1);
    }

    public void change_color_elpaso_dallas_g1(View view) {
        findViewById(R.id.elpaso_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b4).setAlpha(1);
    }

    public void change_color_elpaso_houston_g1(View view) {
        findViewById(R.id.elpaso_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b4).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b5).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b6).setAlpha(1);
    }

    public void change_color_winnipeg_duluth_g1(View view) {
        findViewById(R.id.winnipeg_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b1).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b2).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b3).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b4).setAlpha(1);
    }

    public void change_color_omaha_kansascity_g1(View view) {
        findViewById(R.id.omaha_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_kansascity_g1b1).setAlpha(1);
    }

    public void change_color_oklahomacity_kansascity_g1(View view) {
        findViewById(R.id.oklahomacity_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_kansascity_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_kansascity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_kansascity_g1b2).setAlpha(1);
    }

    public void change_color_oklahomacity_dallas_g1(View view) {
        findViewById(R.id.oklahomacity_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g1b2).setAlpha(1);
    }

    public void change_color_dallas_houston_g1(View view) {
        findViewById(R.id.dallas_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.dallas_houston_g1b1).setAlpha(1);
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
            mGreenTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(1));
            mRedTrainCard.setText(""+ playersHandPresenter.getTrainCardAmount(2));
            mPinkTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(6));
            mYellowTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(3));
            mWhiteTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(7));
            mBlackTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(8));
            mWildTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(9));
            mBlueTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(4));
            mOrangeTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(5));

        }
    }
}